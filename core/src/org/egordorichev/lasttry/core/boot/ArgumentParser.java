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
	private static String[] args;
	/**
	 * Commands list
	 */
	private static ArrayList<Command> commands = new ArrayList<Command>();

	static {
		commands.add(new HelpCommand());
		commands.add(new DebugCommand());
		commands.add(new PackTexturesCommand());
	}

	/**
	 * Sets args
	 *
	 * @param args New args
	 */
	public static void setArgs(String[] args) {
		ArgumentParser.args = args;
	}

	/**
	 * Parses arguments
	 *
	 * @throws RuntimeException If something went wrong
	 */
	public static void parse() throws RuntimeException {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			boolean found = false;

			for (Command command : commands) {
				String name = command.getName();
				String shortName = command.getShortName();

				if (arg.equals("--" + name)	|| arg.equals("-" + shortName)) {
					command.call(args);
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