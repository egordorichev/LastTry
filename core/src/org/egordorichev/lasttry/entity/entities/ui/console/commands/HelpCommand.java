package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommandsComponent;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;

import java.util.ArrayList;
import java.util.Objects;

public class HelpCommand extends ConsoleCommand {
	public HelpCommand() {
		super("help", "Shows hint dialog");
	}

	@Override
	public void run(UiConsole console, String[] args) {
		ConsoleCommandsComponent commands = console.getComponent(ConsoleCommandsComponent.class);
		ArrayList<ConsoleCommand> list;

		if (args.length == 0) {
			list = commands.commands;
		} else {
			list = new ArrayList<>();

			for (ConsoleCommand command : commands.commands) {
				for (int i = 0; i < args.length; i++) {
					if (args[i] != null && Objects.equals(args[i], command.getName())) {
						list.add(command);
						args[i] = null;
					}
				}
			}
		}

		if (list.size() == 0) {
			console.print("Sorry, unknown topic");
		} else {
			for (ConsoleCommand command : list) {
				console.print(command.getName() + ": " + command.getDescription());
			}
		}
	}
}