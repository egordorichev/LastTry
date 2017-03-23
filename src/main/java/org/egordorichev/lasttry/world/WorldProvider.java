package org.egordorichev.lasttry.world;

import java.io.File;
import java.io.IOException;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.generator.WorldGenerator;

public class WorldProvider {
	/** Supported world version */
	public static short CURRENT_VERSION = 3;

	/**
	 * Generates new world with the given name, width, and height.
	 * 
	 * @param name name of the world.
	 * @param width width of the world.
	 * @param height height of the workd.
	 * @param flags world flags
	 * @return new world.
	 */
	public static World generate(String name, short width, short height, int flags) {
		LastTry.log.info("Generating...");

		Biome.preload();

		WorldGenerator generator = new WorldGenerator();
		World world = new World(width, height, flags, generator.generate(width, height));
		// world.addBiomeChecker(); : TODO

		LastTry.log("Finished generating!");

		return world;
	}

	/**
	 * Saves the given world.
	 * @param world world to save.
	 */
	public static void save(String name, World world) {
		LastTry.log("Saving...");

		try {
			FileWriter stream = new FileWriter(getFilePath(name));

			stream.writeInt32(CURRENT_VERSION);
			stream.writeBoolean(world.isHardmode());
			stream.writeBoolean(world.isExpertMode());
			stream.writeBoolean(world.isHardcore());
			stream.writeBoolean(world.evilIsCrimson());

			short width = world.getWidth();
			short height = world.getHeight();

			stream.writeInt16(width);
			stream.writeInt16(height);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					stream.writeInt16(world.getBlockID(x, y));
					stream.writeByte(world.getBlockHp(x, y));
					stream.writeInt16(world.getWallID(x, y));
					stream.writeByte(world.getWallHp(x, y));
				}
			}

			stream.writeBoolean(true);
			stream.close();

			LastTry.log("Done saving!");
		} catch (IOException exception) {
			LastTry.handleException(exception);
			System.exit(0);
		}
	}

	/**
	 * Load a world by the given name. Returns null if the world cannot be
	 * found.
	 * 
	 * @param worldName
	 *            World to load.
	 * @return World by name.
	 */
	public static World load(String worldName) {
		LastTry.log("Loading...");

		try {
			FileReader stream = new FileReader(getFilePath(worldName));

			int version = stream.readInt32();

			if (version > CURRENT_VERSION) {
				throw new RuntimeException("Unsupported world version: " + version);
			} else if (version < CURRENT_VERSION) {
				LastTry.log.warn("Trying to load old version world");
			}

			int flags = 0;

			if (stream.readBoolean()) {
				flags |= World.HARDMODE;
			}

			if (stream.readBoolean()) {
				flags |= World.EXPERT;
			}

			if (stream.readBoolean()) {
				flags |= World.HARDMODE;
			}

			if (stream.readBoolean()) {
				flags |= World.CRIMSON;
			}

			short width = stream.readInt16();
			short height = stream.readInt16();

			WorldData data = new WorldData(width * height);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					data.blocks[x + y * width] = stream.readInt16();
					data.blocksHealth[x + y * width] = stream.readByte();
					data.walls[x + y * width] = stream.readInt16();
					data.wallsHealth[x + y * width] = stream.readByte();
				}
			}

			if (!stream.readBoolean()) {
				throw new RuntimeException("Verification failed");
			}

			stream.close();
			Biome.preload();

			World world = new World(width, height, flags, data);
			LastTry.log("Done loading!");

			return world;
		} catch (IOException exception) {
			// world.save();

			LastTry.handleException(exception);
			System.exit(0);
		}

		return null;
	}

	/**
	 * Checks if a world by the given name exists and if it is valid to load.
	 * 
	 * @param worldName
	 *            World to load.
	 * @return
	 */
	public static boolean exists(String worldName) {
		File worldFile = new File("assets/worlds/" + worldName + ".wld");
		if (!worldFile.exists()) {
			return false;
		}

		// TODO: Simple check to see if it exists
		// TODO: Check if exists but is incompatible (Check world version but
		// nothing else)

		if (worldFile.length() == 0) {
			return false;
		}

		return true;
	}

	/**
	 * Get the file path of a world by the given name.
	 * 
	 * @param worldName
	 *            World name.
	 * @return Path to world file.
	 */
	private static String getFilePath(String worldName) {
		return "assets/worlds/" + worldName + ".wld";
	}

}
