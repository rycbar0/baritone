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

package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.exception.CommandException;
import baritone.utils.schematic.litematica.LitematicaHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LitematicaCommand extends Command {

    public LitematicaCommand(IBaritone baritone) {
        super(baritone, "litematica");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        try {
            if(LitematicaHelper.isLitematicaPresent()) {
                if (!LitematicaHelper.LitematicaVersionChecker()) {
                    logDirect("This implementation requires the rewrite version of Litematica. (version 0.31.0+)");
                } else {
                    List<String> placementNames = LitematicaHelper.getSchematicPlacementNames();
                    if (placementNames.isEmpty()) {
                        logDirect("You need to make a Schematic Placement first.");
                    } else if (args.hasAny()) {
                        //maybe we could allow for multiple placements to be built at the same time but that is low priority for me atm.
                        args.requireMax(1);
                        if (placementNames.contains(args.peekString())) {
                            baritone.getBuilderProcess().buildOpenLitematic(args.getString());
                        } else {
                            logDirect("Pleas provide a valid name for a schematic placement.");
                        }
                    } else {
                        baritone.getBuilderProcess().buildOpenLitematic(placementNames.get(0));
                    }
                }
            } else {
                logDirect("You cant use this command without the litematica mod, baka.");
            }
        } catch (Exception e) {
            logDirect("You should not see this. Something went wrong :/");
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return LitematicaHelper.getSchematicPlacementNames().stream();
    }

    @Override
    public String getShortDesc() {
        return "Builds the loaded schematic";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList(
                "Build a schematic currently open in Litematica.",
                "",
                "Usage:",
                "> litematica",
                "> litematica <placement name>"
        );
    }
}