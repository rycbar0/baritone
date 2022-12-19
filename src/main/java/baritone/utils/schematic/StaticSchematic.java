/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils.schematic;

import baritone.Baritone;
import baritone.api.schematic.AbstractSchematic;
import baritone.api.schematic.IStaticSchematic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.Vec3i;

import java.util.List;

/**
 * Default implementation of {@link IStaticSchematic}
 *
 * @author Brady
 * @since 12/23/2019
 */
public class StaticSchematic extends AbstractSchematic implements IStaticSchematic {

    protected IBlockState[][][] states;

    @Override
    public IBlockState desiredState(int x, int y, int z, IBlockState current, List<IBlockState> approxPlaceable) {
        return this.states[x][z][y];
    }

    @Override
    public IBlockState getDirect(int x, int y, int z) {
        return this.states[x][z][y];
    }

    @Override
    public IBlockState[] getColumn(int x, int z) {
        return this.states[x][z];
    }

    public void applyMirrorRotation() {
        Mirror mirror = Mirror.valueOf(Baritone.settings().buildApplyMirror.value.toUpperCase());
        Rotation rotation = Rotation.valueOf(Baritone.settings().buildApplyRotation.value.toUpperCase());
        IBlockState[][][] temp;
        if (rotation.ordinal()%2 == 1) {
            temp = new IBlockState[z][x][y];
        } else {
            temp = new IBlockState[x][z][y];
        }

        for (int yCounter = 0; yCounter < y; yCounter++) {
            for (int zCounter = 0; zCounter < z; zCounter++) {
                for (int xCounter = 0; xCounter < x; xCounter++) {

                    Vec3i newPos = getNewPosition(xCounter, yCounter, zCounter, mirror, rotation);

                    IBlockState newState = null;
                    try {
                        newState = states[xCounter][zCounter][yCounter].withMirror(mirror).withRotation(rotation);
                    } catch (NullPointerException e) {

                    }
                    temp[newPos.getX()][newPos.getZ()][newPos.getY()] = newState;
                }
            }
        }
        x = temp.length;
        z = temp[0].length;
        this.states = temp;
    }

    private Vec3i getNewPosition(int oldX, int oldY, int oldZ, Mirror mirror, Rotation rotation) {
        int newX = oldX;
        int newZ = oldZ;

        if (mirror == Mirror.LEFT_RIGHT) {
            newZ = (z - 1) - oldZ;
        } else if (mirror == Mirror.FRONT_BACK) {
            newX = (x - 1) - oldX;
        }

        for (int turns = 0; turns < rotation.ordinal(); turns++) {
            oldX = newX;
            oldZ = newZ;
            if ((turns % 2) == 0) {
                newX = (x - 1) - (x - z) - oldZ;
                newZ = oldX;
            } else {
                newX = (z - 1) - (z - x) - oldZ;
                newZ = oldX;
            }
        }

        return new Vec3i(newX, oldY, newZ);
    }
}
