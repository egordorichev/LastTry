package org.egordorichev.lasttry.core.boot.commands;

import org.egordorichev.lasttry.core.boot.Command;
import org.egordorichev.lasttry.entity.asset.AssetPacker;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Packs all textures to the texture atlas
 */
public class PackTexturesCommand extends Command {
	public PackTexturesCommand() {
		super("pack", "pt");
	}

	/**
	 * The actual command
	 *
	 * @param args Command args
	 * @return True, if there was a error
	 */
	@Override
	public boolean call(String[] args) {
		Log.debug("Packing textures...");
		AssetPacker.pack();
		Log.debug("Done");
		return false;
	}
}
