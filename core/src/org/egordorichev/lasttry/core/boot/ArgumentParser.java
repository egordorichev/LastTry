package org.egordorichev.lasttry.core.boot;

import org.egordorichev.lasttry.core.boot.commands.*;

import java.util.ArrayList;

/**
 * Handles arguments
 */
public class ArgumentParser {
	/**
	 * Command line arguments
	 */
	private String[] args;
	/**
	 * Commands list
	 */
	private static ArrayList<Command> commands = new ArrayList<Command>();

	static {
		commands.add(new HelpCommand());
		commands.add(new DebugCommand());
	}

	public ArgumentParser(String[] args) {
		this.args = args;
	}

	public void parse() throws RuntimeException {
		for (int i = 0; i < this.args.length; i++) {
			String arg = this.args[i];
			boolean found = false;

			for (Command command : commands) {
				String name = command.getName();
				String shortName = command.getShortName();

				if (arg.equals("--" + name)	|| arg.equals("-" + shortName)) {
					command.call(this.args);
					found = true;
					break;
				}
			}

			if (!found) {
				throw new RuntimeException("Argument " + arg + " is unknown");
			}
		}
	}
}