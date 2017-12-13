package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;
import org.egordorichev.lasttry.entity.entities.world.ClockComponent;
import org.egordorichev.lasttry.entity.entities.world.World;

/**
 * Sets time to day
 */
public class NightCommand extends ConsoleCommand {
	public NightCommand() {
		super("night", "Sets time to night");
	}

	@Override
	public void run(UiConsole console, String[] args) {
		ClockComponent clock = World.instance.getComponent(ClockComponent.class);

		clock.hour = 22;
		clock.minute = 0;
	}
}