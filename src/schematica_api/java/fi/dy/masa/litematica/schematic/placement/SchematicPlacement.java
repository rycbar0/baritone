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

package fi.dy.masa.litematica.schematic.placement;

import com.google.common.collect.ImmutableMap;
import fi.dy.masa.litematica.Litematica;
import fi.dy.masa.litematica.materials.MaterialListBase;
import fi.dy.masa.litematica.schematic.ISchematic;
import fi.dy.masa.litematica.schematic.LitematicaSchematic;
import fi.dy.masa.litematica.selection.SelectionBox;
import fi.dy.masa.malilib.util.data.EnabledCondition;
import fi.dy.masa.malilib.util.position.IntBoundingBox;

import java.util.ArrayList;
import java.util.List;

public class SchematicPlacement extends BasePlacement {

    public IntBoundingBox getEnclosingBox() {
        return null;
    }

    //todo use ISchematic as return type
    public LitematicaSchematic getSchematic() {
        return null;
    }

    public ImmutableMap<String, SelectionBox> getSubRegionBoxes(EnabledCondition condition) {
        return null;
    }

    public SelectionBox getSubRegionBox(String regionName, EnabledCondition condition) {
        return null;
    }

    public MaterialListBase getMaterialList() {
        return null;
    }

    public List<SubRegionPlacement> getAllSubRegions() {
        return null;
    }

    public List<SubRegionPlacement> getEnabledSubRegions() {
        return null;
    }

}
