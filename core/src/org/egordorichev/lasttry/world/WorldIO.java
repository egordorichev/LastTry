package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;

import java.io.File;
import java.io.IOException;

public class WorldIO {
	public static final int VERSION = 0;

	public static World load(String name) {
		String fileName = getSaveName(name);
		File file = new File(fileName);

		if (!file.exists()) {
			Log.error("Could not find save file for the world " + name);
			LastTry.abort();
		}

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
			return new World(name, size, flags);
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		return null;
	}

	public static void generate(String name, short width, short height, int flags) {

	}

	public static void save() {
		String fileName = getSaveName(LastTry.world.getName());
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException exception) {
				Log.error("Failed to create save file for the world " + LastTry.world.getName() + "!");
				LastTry.abort();
			}
		}

		try {
			FileWriter stream = new FileWriter(fileName);

			stream.writeInt32(VERSION);

			switch (LastTry.world.getSize()) {
				case SMALL: stream.writeByte((byte) 0); break;
				case MEDIUM: stream.writeByte((byte) 1); break;
				case LARGE: stream.writeByte((byte) 2); break;
			}

			stream.writeBoolean(LastTry.world.flags.isHardmode());
			stream.writeBoolean(LastTry.world.flags.isExpertMode());
			stream.writeBoolean(LastTry.world.flags.evilIsCrimson());

			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}
	}

	private static String getSaveName(String name) {
		return "worlds/" + name + ".wld";
	}
}