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

package baritone.api.process;

import baritone.api.BaritoneAPI;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.*;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * This Process controls the Production of Items.
 */
public interface ICraftingProcess extends IBaritoneProcess {

    /**
     * Executes the crafting of the requested recipes such that we obtain the requested amount of output.
     * It is possible to mix recipes with different out puts, but it may lead to strange results.
     * @param recipes List of recipes that should be used.
     * @param amount how many result items are wanted.
     */
    void craft(List<CraftingRecipe> recipes, int amount);

    /**
     * @param item that should be crafted.
     * @param allCraftingRecipes if all crafting recipes should be returned or only the one that can be crafted
     * @return List of recipes that result in the provided Item.
     */
    List<CraftingRecipe> getCraftingRecipes(Item item, boolean allCraftingRecipes);

    /**
     * @param recipes that should be crafted.
     * @param amount how much output is wanted.
     * @return Can we craft this the requested amount of output from the provided recipes?
     */
    boolean canCraft(List<CraftingRecipe> recipes, int amount);

    /**
     * @param recipe the recipe we want to craft.
     * @return Do we need a crafting table or can we craft it in our inventory?
     */
    boolean canCraftInInventory(CraftingRecipe recipe);
/*
    void smithing(SmithingRecipe recipe);
    void stoneCutting(StonecutterRecipe recipe, int amount);
    boolean forge(Slot primaryItem, Slot secondaryItem);
    void grind(Slot item);
    void smelt(SmeltingRecipe recipe, int amount);
    List<SmeltingRecipe> getSmeltingRecipe(Item item, boolean allSmeltingRecipes);
    void brew(Potion potion, int amount);
    void trade();/**/

    /* todo
     * crafting
     * smithing
     * forge (anvil)
     * grind
     * smelting
     * brewing
     * weaving
     * trading
     * enchanting
     *  */
}
