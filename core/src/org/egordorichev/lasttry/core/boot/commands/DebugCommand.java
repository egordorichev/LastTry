package org.egordorichev.lasttry.core.boot.commands;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.core.boot.Command;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Enables debug mode
 */
public class DebugCommand extends Command {
	public DebugCommand() {
		super("debug", "d");
	}

	/**
	 * The actual command
	 *
	 * @param args Command args
	 * @return True, if there was a error
	 */
	@Override
	public boolean call(String[] args) {
		Globals.enableDebug();
		Log.debug("Debug mode is enabled");

		return false;
	}
}