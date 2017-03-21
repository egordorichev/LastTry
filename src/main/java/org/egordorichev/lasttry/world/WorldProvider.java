package org.egordorichev.lasttry.world;

import java.io.File;
import java.io.IOException;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.tiles.Block;
import org.egordorichev.lasttry.item.tiles.Wall;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.World.EvilType;
import org.egordorichev.lasttry.world.gen.WorldGenerator;
import org.egordorichev.lasttry.world.tile.TileData;

public class WorldProvider {

	/**
	 * Generate a world with the given name, width, and height.
	 * 
	 * @param name
	 *            Name of the world.
	 * @param width
	 *            Width of the world.
	 * @param height
	 *            Height of the workd.
	 * @return New world.
	 */
	public static World generate(String name, int width, int height) {
		LastTry.log.info("Generating...");

		// Create 2D array of tile ID's.
		// Then populate the array.

		WorldGenerator generator = new WorldGenerator();
		int tileIDs[][] = generator.generate(width, height);

		// Convert the 2D tile ID array to a 1D array of TileData

		int totalSize = width * height;
		TileData[] data = new TileData[totalSize];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int id = tileIDs[x][y];
				data[x + y * width] = new TileData((Block) Item.fromID(id), Wall.getForBlockID(id));
			}
		}

		World world = new World(name, width, height, data);
		world.addBiomeChecker();
		LastTry.log("Finished generating!");

		return world;
	}

	/**
	 * Save the given world.
	 * 
	 * @param world
	 *            World to save.
	 */
	public static void save(World world) {
		LastTry.log("Saving...");

		try(FileWriter stream = new FileWriter(getFilePath(world.getName()))) {
			int width = world.getWidth();
			int height = world.getHeight();

			// Header
			stream.writeInt32(world.getVersion());

			// Basic world info:
			// name, width/height & evil type, etc.

			stream.writeString(world.getName());
			stream.writeInt32(width);
			stream.writeInt32(height);
			stream.writeBoolean(world.isHardmode());
			stream.writeBoolean(world.isExpert());
			stream.writeBoolean((world.getEvilType() == EvilType.CRIMSON) ? true : false);

			// Tile data

			int totalSize = width * height;

			for (int i = 0; i < totalSize; i++) {
				TileData data = world.getTileData(i);

				int blockId = 0;
				int wallId = 0;

				if (data.block != null) {
					blockId = data.block.getId();
				}

				if (data.wall != null) {
					wallId = data.wall.getId();
				}

				stream.writeInt32(blockId);
				stream.writeInt32(wallId);
				// TODO: RLE
			}

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

		try (FileReader stream = new FileReader(getFilePath(worldName))) {
			// Version header, check if compatible

			int version = stream.readInt32();

			if (version > World.CURRENT_VERSION) {
				throw new RuntimeException("Unsupported version");
			} else if (version < World.CURRENT_VERSION) {
				LastTry.log.warn("Warning: Attempting to load outdated world format.");
			}

			// Basic world info:
			// name, width/height & evil type, etc.

			worldName = stream.readString();
			int width = stream.readInt32();
			int height = stream.readInt32();
			boolean hardmode = stream.readBoolean();
			boolean expert = stream.readBoolean();

			EvilType evilType = (stream.readBoolean()) ? EvilType.CRIMSON : EvilType.CORRUPTION;

			// Tile data

			int totalSize = width * height;
			TileData[] tiles = new TileData[totalSize];

			for (int i = 0; i < totalSize; i++) {
				tiles[i] = new TileData((Block) Item.fromID(stream.readInt32()), (Wall) Item.fromID(stream.readInt32()));
				// TODO: RLE
			}

			LastTry.log("Done loading!");

			World world = new World(worldName, width, height, evilType, tiles);

			world.setHardmode(hardmode);
			world.setExpert(expert);
			world.addBiomeChecker();

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
