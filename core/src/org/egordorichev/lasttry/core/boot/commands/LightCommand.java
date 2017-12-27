package org.egordorichev.lasttry.core.boot.commands;

import org.egordorichev.lasttry.core.Debug;
import org.egordorichev.lasttry.core.boot.Command;
import org.egordorichev.lasttry.util.log.Log;

public class LightCommand extends Command{
	
	public LightCommand() {
		super("light", "l");
	}
	
	/**
	 * The actual command
	 *
	 * @param args Command args
	 * @return True, if there was a error
	 */
	@Override
	public boolean call(String[] args) {
		Debug.lightEnabled = true;

		return false;
	}
}
