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

package fi.dy.masa.malilib.util.position;

import net.minecraft.util.math.Vec3i;

public class IntBoundingBox {

    public int getMinX()
    {
        return 0;
    }

    public int getMinY()
    {
        return 0;
    }

    public int getMinZ()
    {
        return 0;
    }

    public int getMaxX()
    {
        return 0;
    }

    public int getMaxY()
    {
        return 0;
    }

    public int getMaxZ()
    {
        return 0;
    }

    public boolean contains(Vec3i pos)
    {
        return false;
    }
}
