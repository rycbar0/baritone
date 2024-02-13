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

package baritone.utils.schematic.litematica;

import baritone.api.schematic.IStaticSchematic;
import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.selection.Box;
import fi.dy.masa.litematica.world.SchematicWorldHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public final class LitematicaAdapter implements IStaticSchematic {
    @Override
    public BlockState desiredState(int x, int y, int z, BlockState current, List<BlockState> approxPlaceable) {
        BlockState fromSchematic = getDirect(x, y, z);
        if (fromSchematic == null) {
            return current;
        } else {
            return fromSchematic;
        }
    }

    @Override
    public int widthX() { //todo double check this math is correct
        Box enclosingBox = DataManager.getSchematicPlacementManager().getSelectedSchematicPlacement().getEclosingBox();
        int x1 = enclosingBox.getPos1().getX();
        int x2 = enclosingBox.getPos2().getX();
        if (x1 < x2) {
            return x2 - x1;
        } else if (x1 > x2) {
            return x1 - x2;
        } else {
            return 1;
        }
    }

    @Override
    public int heightY() { //todo double check this math is correct
        Box enclosingBox = DataManager.getSchematicPlacementManager().getSelectedSchematicPlacement().getEclosingBox();
        int y1 = enclosingBox.getPos1().getY();
        int y2 = enclosingBox.getPos2().getY();
        if (y1 < y2) {
            return y2 - y1;
        } else if (y1 > y2) {
            return y1 - y2;
        } else {
            return 1;
        }
    }

    @Override
    public int lengthZ() { //todo double check this math is correct
        Box enclosingBox = DataManager.getSchematicPlacementManager().getSelectedSchematicPlacement().getEclosingBox();
        int z1 = enclosingBox.getPos1().getZ();
        int z2 = enclosingBox.getPos2().getZ();
        if (z1 < z2) {
            return z2 - z1;
        } else if (z1 > z2) {
            return z1 - z2;
        } else {
            return 1;
        }
    }

    @Override
    public BlockState getDirect(int x, int y, int z) {
        BlockPos blockPos = new BlockPos(x, y, z);
        return SchematicWorldHandler.getSchematicWorld().getWorldChunk(blockPos).getBlockState(blockPos);
    }

    private boolean isPartOfSchematic(int x, int y, int z) { //todo is inside a subregion
        return true;
    }
}
