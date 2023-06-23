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

package baritone.process;

import baritone.Baritone;
import baritone.api.process.ISmeltingProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.utils.BaritoneProcessHelper;
import baritone.utils.type.BurnTime;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class SmeltingProcess extends BaritoneProcessHelper implements ISmeltingProcess {
    private Item item;
    private int amount;
    int tickCounter;
    public SmeltingProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        return item != null && amount >= 1;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        if (tickCounter < 200) {
            tickCounter++;
        } else {
            onLostControl();
        }
        return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
    }

    @Override
    public void onLostControl() {
        amount = 0;
        item = null;
        tickCounter = 0;
        baritone.getInputOverrideHandler().clearAllKeys();
    }

    @Override
    public String displayName0() {
        return "Smelting "+item.getTranslationKey()+" remaining: "+amount;
    }

    @Override
    public void smelt(Item item, int amount) {
        tickCounter = 0;
        this.item = item;
        this.amount = amount;
    }
}
