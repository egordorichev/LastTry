package org.egordorichev.lasttry.ui.chat.command;

import java.util.Comparator;

/**
 * Simple Command Comparator for sorting commands by the category and names.
 */
public class CommandSorter implements Comparator<Command> {
	@Override
	public int compare(Command c1, Command c2) {
		// Sort by group
		int type = c1.getCategory().compareTo(c2.getCategory());
		// Sort alphabetically if groups match
		if (type == 0) {
			return c1.getHandle().compareTo(c2.getHandle());
		}
		return type;
	}
}
