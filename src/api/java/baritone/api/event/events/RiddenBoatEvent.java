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

package baritone.api.event.events;

import baritone.api.event.events.type.Cancellable;
import net.minecraft.entity.item.EntityBoat;

// Boat Support
public abstract class RiddenBoatEvent extends Cancellable {
    private final EntityBoat boat;
    private float desiredYaw;

    public RiddenBoatEvent(EntityBoat boat) {
        this.boat = boat;
    }

    public EntityBoat getBoat() {
        return this.boat;
    }

    public void setDesiredYaw(float desiredYaw) {
        this.desiredYaw = desiredYaw;
    }

    public float getDesiredYaw() {
        return desiredYaw;
    }

    public static class Direction extends RiddenBoatEvent {
        public Direction(EntityBoat boat) {
            super(boat);
        }
    }

    public static class Steer extends RiddenBoatEvent {
        public Steer(EntityBoat boat) {
            super(boat);
        }
    }
}

