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

package baritone.pathing.movement.moves;

import baritone.api.utils.BetterBlockPos;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.utils.pathing.MutableMoveResult;

/**
 * Interface representing a movement in a 3D space.
 * This can be used to represent player movements in a game world,
 * with methods to calculate, apply and get the cost of the move.
 *
 * @author RemainingToast
 */
public interface IMoves {

    /**
     * Calculates the move based on the provided context and source position.
     *
     * @param context The context in which the move is being calculated. This could
     *                contain information about the game world or the player's state.
     * @param src     The position from which the move is being made.
     * @return        A Movement object representing the calculated move.
     */
    Movement apply0(CalculationContext context, BetterBlockPos src);

    /**
     * Applies the move to the given coordinates in the game world.
     *
     * @param context The context in which the move is being applied. This could
     *                contain information about the game world or the player's state.
     * @param x       The x-coordinate of the position to which the move is being applied.
     * @param y       The y-coordinate of the position to which the move is being applied.
     * @param z       The z-coordinate of the position to which the move is being applied.
     * @param result  A MutableMoveResult object that will contain the result of the move.
     */
    void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result);

    /**
     * Calculates the cost of the move.
     *
     * @param context The context in which the cost is being calculated. This could
     *                contain information about the game world or the player's state.
     * @param x       The x-coordinate of the position to which the move is being applied.
     * @param y       The y-coordinate of the position to which the move is being applied.
     * @param z       The z-coordinate of the position to which the move is being applied.
     * @return        The cost of the move.
     */
    double cost(CalculationContext context, int x, int y, int z);

    /**
     * Checks whether the move is dynamic in the XZ plane.
     *
     * @return true if the move is dynamic in the XZ plane, false otherwise.
     */
    boolean isDynamicXZ();

    /**
     * Checks whether the move is dynamic in the Y direction.
     *
     * @return true if the move is dynamic in the Y direction, false otherwise.
     */
    boolean isDynamicY();

    /**
     * Returns the X offset of the move.
     *
     * @return The X offset of the move.
     */
    int getXOffset();

    /**
     * Returns the Y offset of the move.
     *
     * @return The Y offset of the move.
     */
    int getYOffset();

    /**
     * Returns the Z offset of the move.
     *
     * @return The Z offset of the move.
     */
    int getZOffset();
}