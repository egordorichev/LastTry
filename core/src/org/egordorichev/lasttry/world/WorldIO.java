package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;
import org.egordorichev.lasttry.world.generator.WorldGenerator;

import java.io.File;
import java.io.IOException;

public class WorldIO {
	public static final int VERSION = 0;

	public static void load(String name) {
		String fileName = getSaveName(name);
		File file = new File(fileName);

		if (!file.exists()) {
			Log.error("Could not find save file for the world " + name);
			LastTry.abort();
		}

		Log.debug("Loading world " + name + "...");

		try {
			FileReader stream = new FileReader(fileName);

			int version = stream.readInt32();

			if (version > VERSION) {
				Log.error("Trying to load unknown world.");
				LastTry.abort();
			} else if (version < VERSION) {
				Log.error("Trying to load an old world.");
				LastTry.abort();
			}

			World.Size size = World.Size.values()[stream.readByte()];
			int flags = 0;

			if (stream.readBoolean()) {
				flags |= WorldFlagsComponent.HARDMODE;
			}

			if (stream.readBoolean()) {
				flags |= WorldFlagsComponent.EXPERT;
			}

			if (stream.readBoolean()) {
				flags |= WorldFlagsComponent.CRIMSON;
			}

			stream.close();
			Globals.world = new World(name, size, flags);
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		Log.debug("Done loading world " + name + "!");
	}

	public static World generate(String name, World.Size size, int flags) {
		File dir = new File("worlds/");

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File("worlds/" + name + "/");

		if (!file.exists()) {
			file.mkdir();
		}

		return new WorldGenerator(name, size, flags).generate();
		// return new World(name, size, flags);
	}

	public static void save() {
		String fileName = getSaveName(Globals.world.getName());
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException exception) {
				Log.error("Failed to create save file for the world " + Globals.world.getName() + "!");
				LastTry.abort();
			}
		}

		Log.debug("Saving world " + Globals.world.getName() + "...");

		try {
			FileWriter stream = new FileWriter(fileName);
			stream.writeInt32(VERSION);

			switch (Globals.world.getSize()) {
				case SMALL: stream.writeByte((byte) 0); break;
				case MEDIUM: stream.writeByte((byte) 1); break;
				case LARGE: stream.writeByte((byte) 2); break;
			}

			stream.writeBoolean(Globals.world.flags.isHardmode());
			stream.writeBoolean(Globals.world.flags.isExpertMode());
			stream.writeBoolean(Globals.world.flags.evilIsCrimson());

			stream.close();

			Globals.world.chunks.save();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		Log.debug("Done saving world " + Globals.world.getName() + "!");
	}

	public static boolean saveExists(String name) {
		File file = new File(getSaveName(name));
		return file.exists();
	}

	private static String getSaveName(String name) {
		return "worlds/" + name + ".wld";
	}
}