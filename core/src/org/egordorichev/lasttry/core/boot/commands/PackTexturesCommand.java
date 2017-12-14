package org.egordorichev.lasttry.core.boot.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.egordorichev.lasttry.core.boot.Command;
import org.egordorichev.lasttry.entity.asset.AssetPacker;
import org.egordorichev.lasttry.util.log.Log;

import java.util.Objects;

/**
 * Packs all textures to the texture atlas
 */
public class PackTexturesCommand extends Command {
	/**
	 * Set to true, to update textures every run
	 */
	private static boolean FORCE = false;

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
		String modified = "0";

		FileHandle lastModified = Gdx.files.local(".modified");

		if (lastModified.exists()) {
			modified = lastModified.readString();
		}

		FileHandle handle = Gdx.files.internal("textures");
		String newModified = handle.lastModified() + "";

		Log.debug("Last modified textures folder: " + newModified + ", current state: " + modified);

		if (!Objects.equals(newModified, modified) || PackTexturesCommand.FORCE) {
			Log.debug("Packing textures...");
			AssetPacker.pack();
			Log.debug("Done");

			lastModified.writeString(newModified, false);
		}

		return false;
	}
}
