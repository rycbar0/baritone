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
import fi.dy.masa.litematica.schematic.ISchematicRegion;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacement;
import fi.dy.masa.malilib.util.data.EnabledCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3i;

import java.util.List;

public class LitematicaAdapter implements IStaticSchematic {
    private final SchematicPlacement placement;

    public LitematicaAdapter(SchematicPlacement placement) {
        this.placement = placement;
    }

    @Override
    public IBlockState desiredState(int x, int y, int z, IBlockState current, List<IBlockState> approxPlaceable) {
        boolean orCombinator = false;
        for (String key : placement.getSchematic().getRegionNames()) {
            orCombinator = orCombinator || placement.getSubRegionBox(key, EnabledCondition.ANY).asIntBoundingBox().contains(new Vec3i(x, y, z));
        }
        if (orCombinator) {
            return getDirect(x,y,z);
        } else {
            return current;
        }
    }

    @Override
    public int widthX() {
        return placement.getEnclosingBox().getMaxX() - placement.getEnclosingBox().getMinX() + 1;
    }

    @Override
    public int heightY() {
        return placement.getEnclosingBox().getMaxY() - placement.getEnclosingBox().getMinY() + 1;
    }

    @Override
    public int lengthZ() {
        return placement.getEnclosingBox().getMaxZ() - placement.getEnclosingBox().getMinZ() + 1;
    }

    @Override
    public IBlockState getDirect(int x, int y, int z) {
        IBlockState blockState = Blocks.AIR.getDefaultState();
        for (ISchematicRegion schematicRegion : placement.getSchematic().getRegions().values()) {
            if (schematicRegion != null) {
                IBlockState temp = schematicRegion.getBlockStateContainer().getBlockState(x, y, z);
                blockState = temp == Blocks.AIR.getDefaultState() ? blockState : temp;
            }
        }
        return blockState;
    }
}
