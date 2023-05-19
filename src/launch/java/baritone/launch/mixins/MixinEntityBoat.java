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

package baritone.launch.mixins;

import baritone.api.BaritoneAPI;
import baritone.api.event.events.RiddenBoatEvent;
import baritone.pathing.movement.MovementHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.play.client.CPacketVehicleMove;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

// Boat Support
@Mixin(EntityBoat.class)
public class MixinEntityBoat {
    float storedYaw = -999;
    boolean wasCancelled = false;

    @Inject(method = "applyYawToEntity", at = @At("HEAD"), cancellable = true)
    public void applyYawToEntity(Entity entityToUpdate, CallbackInfo ci) {
        EntityBoat entityBoat = (EntityBoat) (Object) this;

        RiddenBoatEvent.Steer event = new RiddenBoatEvent.Steer(entityBoat);

        BaritoneAPI.getProvider()
                .getPrimaryBaritone()
                .getGameEventHandler()
                .onBoatRidden(event);

        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "controlBoat", at = @At("HEAD"))
    public void controlBoatHead(CallbackInfo ci) {
        EntityBoat entityBoat = (EntityBoat) (Object) this;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null && player.getRidingEntity() instanceof EntityBoat) {
            RiddenBoatEvent.Direction event = new RiddenBoatEvent.Direction(entityBoat);

            BaritoneAPI.getProvider()
                    .getPrimaryBaritone()
                    .getGameEventHandler()
                    .onBoatRidden(event);

            if (event.isCancelled()) {
                storedYaw = entityBoat.rotationYaw;

                float desiredYaw = event.getDesiredYaw(); /*MovementHelper.interpolateBoatYaw(
                        entityBoat,
                        event.getDesiredYaw(),
                        10f
                )*/;

                entityBoat.rotationYaw = desiredYaw;

                if (!Minecraft.getMinecraft().isSingleplayer()) {
                    CPacketVehicleMove packet = new CPacketVehicleMove(entityBoat);
                    ((CPacketVehicleMoveAccessor)packet).setYaw(desiredYaw);
                    Objects.requireNonNull(Minecraft.getMinecraft().getConnection()).sendPacket(packet);
                }

                wasCancelled = true;
            }
        }
    }

    @Inject(method = "controlBoat", at = @At("TAIL"))
    public void controlBoatTail(CallbackInfo ci) {
        EntityBoat entityBoat = (EntityBoat) (Object) this;
        if (wasCancelled) {
            entityBoat.rotationYaw = storedYaw;
            wasCancelled = false;
        }
    }
}

