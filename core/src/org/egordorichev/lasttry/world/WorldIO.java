package org.egordorichev.lasttry.world;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Files;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;
import org.egordorichev.lasttry.world.generator.WorldGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class WorldIO {
	private static final Logger logger = LoggerFactory.getLogger(WorldIO.class);
	public static final byte VERSION = 4;

	public static void load(String name) {
		String fileName = Files.getWorldSave(name);
		File file = new File(fileName);

		if (!file.exists()) {
			logger.error("Could not find save file for the world " + name);
			LastTry.abort();
		}

		logger.debug("Loading world " + name + "...");

		try {
			FileReader stream = new FileReader(fileName);

			int version = stream.readByte();
			int seed = stream.readInt32();

			if (version > VERSION) {
				logger.error("Trying to load unknown world.");
				LastTry.abort();
			} else if (version < VERSION) {
				logger.error("Trying to load an old world.");
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

			int spawnX = stream.readInt32();
			int spawnY = stream.readInt32();

			Globals.environment.time.setHour(stream.readByte());
			Globals.environment.time.setMinute(stream.readByte());

			stream.close();
			World world = new World(name, size, flags, seed);
			world.setSpawnPoint(new Vector2(spawnX, spawnY));
			Globals.setWorld(world);
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		logger.debug("Done loading world " + name + "!");
	}

	public static World generate(String name, World.Size size, int flags, int seed) {
		File dir = new File(Files.getWorldsDir());

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(Files.getWorldDir(name));

		if (!file.exists()) {
			file.mkdir();
		}

		return new WorldGenerator(name, size, flags, seed).generate();
	}

	public static void save() {
		World world = Globals.getWorld();

		String fileName = Files.getWorldSave(world.getName());
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				logger.error("Failed to create save file for the world " + world.getName() + "!");
				LastTry.abort();
			}
		}

		logger.debug("Saving world " + world.getName() + "...");

		try {
			FileWriter stream = new FileWriter(fileName);
			stream.writeByte(VERSION);
			stream.writeInt32(world.getSeed());

			switch (world.getSize()) {
			case SMALL:
				stream.writeByte((byte) 0);
				break;
			case MEDIUM:
				stream.writeByte((byte) 1);
				break;
			case LARGE:
				stream.writeByte((byte) 2);
				break;
			}

			stream.writeBoolean(world.flags.isHardmode());
			stream.writeBoolean(world.flags.isExpertMode());
			stream.writeBoolean(world.flags.evilIsCrimson());

			stream.writeInt32((int) world.getSpawnPoint().x);
			stream.writeInt32((int) world.getSpawnPoint().y);

			stream.writeByte(Globals.environment.time.getHour());
			stream.writeByte(Globals.environment.time.getMinute());

			stream.close();

			world.chunks.save();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		logger.debug("Done saving world " + world.getName() + "!");
	}

	public static boolean saveExists(String name) {
		return new File(Files.getWorldSave(name)).exists();
	}
}