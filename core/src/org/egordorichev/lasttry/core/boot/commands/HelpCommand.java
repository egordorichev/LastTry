package org.egordorichev.lasttry.core.boot.commands;

import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.Command;
import org.egordorichev.lasttry.util.log.Log;

public class HelpCommand extends Command {
	public HelpCommand() {
		super("help", "h");
	}

	/**
	 * The actual command
	 *
	 * @param args Command args
	 * @return True, if there was a error
	 */
	@Override
	public boolean call(String[] args) {
		Log.info("LastTry " + Version.STRING);
		Log.info("For more help, please, visit https://github.com/LastTryR/LastTry");
		return false;
	}
}