package org.egordorichev.lasttry.ui.chat.command;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command storage and execution handler.
 */
public class CommandHandler {
	/**
	 * Command sorter that organizes commands first by their type <i>(dictated
	 * by their declaration index in the type enum)</i> then by their name.
	 */
	private final CommandSorter sorter = new CommandSorter();
	/**
	 * Storage for commands:
	 *
	 * Format &lt;cmd, instance&gt;
	 */
	private final Map<String, Command> commands = new HashMap<>();

	/**
	 * Register command by it's cmd handle.
	 *
	 * @param command
	 *            Command to register.
	 */
	public void register(Command command) {
		commands.put(command.getHandle(), command);
	}

	/**
	 * Runs a command with the given args if a command by the given handle
	 * exists. If a command is not found the method returns false. Otherwise the
	 * method will return true.
	 *
	 * @param handle
	 *            Command handle <i>(The short-hand alias)</i>
	 * @param args
	 *            Arguments for command execution.
	 * @return False if command could not be found. True otherwise.
	 */
	public boolean runCommand(String handle, String[] args) {
		if (!hasCommand(handle)) {
			return false;
		}

		// Retrieve command, run with arguments if any are given.
		// Return true signifying successful execution.
		Command com = getCommand(handle);

		if ((com.getCategory() == CMDCategory.DEBUG && LastTry.release)
				|| (com.getCategory() == CMDCategory.ADMININSTRATION && LastTry.release)) { // todo:
																							// admins

			Globals.chat.print("You are not allowed to use this command");
			return false;
		}

		try {
			if (args == null) {
				com.onRun();
			} else {
				com.onRun(args);
			}
		} catch (Exception e) {
			com.onFail(e);
		}

		return true;
	}

	/**
	 * Parses a command handle <i>(and optionally some arguments)</i> and
	 * executes it. If a valid command cannot be found the method returns false.
	 * A successful execution will return true.
	 *
	 * @param in
	 *            Handle of the command to run.
	 * @return True if run was successful. False otherwise.
	 */
	public boolean runInput(String in) {
		if (in.contains(" ")) {
			String[] split = split(in);
			String handle = split[0];
			String[] args = Arrays.copyOfRange(split, 1, split.length);
			return runCommand(handle, args);
		}
		return runCommand(in, null);
	}

	/**
	 * Returns a command my the given command handle.
	 *
	 * @param cmdHandle
	 *            Handle to search with.
	 * @return Command
	 */
	public Command getCommand(String cmdHandle) {
		return commands.get(cmdHandle);
	}

	/**
	 * Returns true if the given command handle is registered to a Command.
	 *
	 * @param cmdHandle
	 *            Handle to search with.
	 * @return boolean
	 */
	public boolean hasCommand(String cmdHandle) {
		return commands.containsKey(cmdHandle);
	}

	/**
	 * Return a collection of available commands.
	 *
	 * @return Collection &lt;Command&gt;
	 */
	public Collection<Command> getCommands() {
		return commands.values();
	}

	/**
	 * Returns the command sorter. See the field {@link #sorter} for more
	 * details.
	 *
	 * @return CommandSorter
	 */
	public CommandSorter getSorter() {
		return sorter;
	}

	/**
	 * Splits the string but treats quoted text as single words.
	 *
	 * @param text
	 *            Text to split into words.
	 * @return Array of words.
	 */
	private static final String[] split(String text) {
		List<String> list = new ArrayList<>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(text);

		while (m.find()) {
			list.add(m.group(1));
		}

		return list.toArray(new String[list.size()]);
	}
}