package org.egordorichev.lasttry.world;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.generator.WorldGenerator;

public class WorldProvider {
	/** Current supported world version */
	public static short CURRENT_VERSION = 5;

	/**
	 * Returns all worlds in the "worlds/" directory
	 * @return all worlds in the "worlds/" directory
	 */
	public static WorldInfo[] getWorlds() {
		File folder = new File("worlds/");
		File[] files = folder.listFiles();

		List<WorldInfo> worlds = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				worlds.add(getWorldInfo("worlds/" + files[i].getName()));
			}
		}

		return worlds.toArray(new WorldInfo[0]);
	}

	/**
	 * Returns world info from given file name
	 * @param fileName world file
	 * @return world info
	 */
	public static WorldInfo getWorldInfo(String fileName) {
		String worldName = fileName.replace("worlds/", "").replace(".wld", "");
		WorldSize size = WorldSize.SMALL;
		int version = -1;
		int flags = 0;

		try {
			FileReader stream = new FileReader(fileName);

			version = stream.readInt32();

			if (stream.readBoolean()) {
				flags |= World.HARDMODE;
			}

			if (stream.readBoolean()) {
				flags |= World.EXPERT;
			}

			if (stream.readBoolean()) {
				flags |= World.CRIMSON;
			}

			LastTry.environment.time.setHour(stream.readByte());
			LastTry.environment.time.setMinute(stream.readByte());

			short width = stream.readInt16();
			short height = stream.readInt16();

			if (width == 500 && height == 500) {
				size = WorldSize.DEV;
			} else if (width == 4200 && height == 1200) {
				size = WorldSize.SMALL;
			} else if (width == 6400 && height == 1800) {
				size = WorldSize.MEDIUM;
			} else if (width == 8400 && height == 2400) {
				size = WorldSize.LARGE;
			} else {
				size = WorldSize.CUSTOM;
			}

			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
			return null;
		}

		return new WorldInfo(worldName, size, version, flags);
	}

	/**
	 * Generates new world with the given name, width, and height.
	 * @param name name of the world.
	 * @param width width of the world.
	 * @param height height of the workd.
	 * @param flags world flags
	 * @return new world.
	 */
	public static World generate(String name, short width, short height, int flags) {
		LastTry.log.info("Generating world...");

		Biome.preload();

		WorldGenerator generator = new WorldGenerator();
		World world = new World(width, height, flags, generator.generate(width, height));

		LastTry.log("Finished generating!");

		return world;
	}

	/**
	 * Saves the given world.
	 * @param world world to save.
	 */
	public static void save(String name, World world) {
		LastTry.log("Saving world...");

		try {
			FileWriter stream = new FileWriter(getFilePath(name));

			stream.writeInt32(CURRENT_VERSION);
			stream.writeBoolean(world.isHardmode());
			stream.writeBoolean(world.isExpertMode());
			stream.writeBoolean(world.evilIsCrimson());

			stream.writeByte(LastTry.environment.time.getHour());
			stream.writeByte(LastTry.environment.time.getMinute());

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
		LastTry.log("Loading world...");

		try (FileReader stream = new FileReader(getFilePath(worldName))){
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
				flags |= World.CRIMSON;
			}

			LastTry.environment.time.setHour(stream.readByte());
			LastTry.environment.time.setMinute(stream.readByte());

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
	 * Get the file path of a world by the given name.
	 * 
	 * @param worldName
	 *            World name.
	 * @return Path to world file.
	 */
	public static String getFilePath(String worldName) {
		return "worlds/" + worldName + ".wld";
	}
}