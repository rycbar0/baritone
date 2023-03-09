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

import baritone.Baritone;
import baritone.api.schematic.IStaticSchematic;
import fi.dy.masa.litematica.Litematica;
import fi.dy.masa.litematica.Reference;
import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacement;
import fi.dy.masa.malilib.util.position.IntBoundingBox;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Helper class that provides access or processes data related to Litmatica schematics.
 *
 * @author rycbar
 * @since 28.09.2022
 */
public final class LitematicaHelper {

    /**
     * @return if Litmatica is installed.
     */
    public static boolean isLitematicaPresent() {
        try {
            Class.forName(Litematica.class.getName());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError ex) {
            return false;
        }
    }

    /**
     * @return if the installed version of litematica is compatible
     */
    public static boolean LitematicaVersionChecker() {
        String[] version = Reference.MOD_VERSION.split("\\.");
        return Integer.getInteger(version[1]) >= 31;
    }

    /**
     * @return List of Names of SchematicPlacements.
     */
    public static List<String> getSchematicPlacementNames() {
        List<String> SchematicPlacementNames = new ArrayList<>();
        for (SchematicPlacement placement : DataManager.getSchematicPlacementManager().getAllSchematicPlacements()) {
            SchematicPlacementNames.add(placement.getName());
        }
        return SchematicPlacementNames;
    }

    /**
     * @param name Name of the desired SchematicPlacement.
     * @return SchematicPlacement with matching name. If a no match is found returns null.
     * @throws IllegalArgumentException If the provided String isn't a valid name.
     */
     public static SchematicPlacement getPlacement(String name) throws IllegalArgumentException {

        for (SchematicPlacement placement: DataManager.getSchematicPlacementManager().getAllSchematicPlacements()) {
            if (Objects.equals(placement.getName(), name)) {
                return placement;
            }
        }
        throw new IllegalArgumentException("Name does not correspond to a existing schematic placement.");
    }

    /**
     * Checks the settings if min or max coordinates are used by baritone and returns the origin accordingly.
     *
     * @param placementName The name of the placement of which the origin coordinates are required.
     * @return Vec3i With the desired origin coordinates.
     */
    public static Vec3i getOrigin(String placementName) {
        IntBoundingBox box = LitematicaHelper.getPlacement(placementName).getEnclosingBox();
        int x = Baritone.settings().schematicOrientationX.value ? box.getMaxX() : box.getMinX();
        int y = Baritone.settings().schematicOrientationY.value ? box.getMaxY() : box.getMinY();
        int z = Baritone.settings().schematicOrientationZ.value ? box.getMaxZ() : box.getMinZ();
        return new Vec3i(x, y, z);
    }

    /**
     * Leave all hope behind. Here will be dragons.
     *
     * @param placementName input.
     * @return IDK what this will return.
     */
    public static IStaticSchematic getSchematic(String placementName) {
        return new LitematicaAdapter(LitematicaHelper.getPlacement(placementName));
    }
}