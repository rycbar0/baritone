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

package fi.dy.masa.litematica.materials;

import fi.dy.masa.malilib.util.data.ItemType;
import net.minecraft.item.ItemStack;

public class MaterialListEntry {
    private final ItemType item;
    private final long countTotal;
    private final long countMissing;
    private final long countMismatched;
    private long countAvailable;

    public MaterialListEntry(ItemStack stack, long countTotal, long countMissing, long countMismatched, long countAvailable)
    {
        this.item = new ItemType(stack, false, false);
        this.countTotal = countTotal;
        this.countMissing = countMissing;
        this.countMismatched = countMismatched;
        this.countAvailable = countAvailable;
    }

    public ItemType getItemType() {
        return this.item;
    }

    public ItemStack getStack() {
        return this.item.getStack();
    }

    /**
     * Returns the total number of required items of this type in the counted area.
     * @return
     */
    public long getTotalCount()
    {
        return this.countTotal;
    }

    /**
     * Returns the number of items still missing (or having the wrong block state)
     * in the counted area for this item type.
     * @return
     */
    public long getMissingCount()
    {
        return this.countMissing;
    }

    public long getCountMismatched()
    {
        return this.countMismatched;
    }

    public long getAvailableCount()
    {
        return this.countAvailable;
    }

    public void setCountAvailable(long countAvailable)
    {
        this.countAvailable = countAvailable;
    }
}
