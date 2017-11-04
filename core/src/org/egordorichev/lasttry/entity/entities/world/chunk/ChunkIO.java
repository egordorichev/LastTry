package org.egordorichev.lasttry.entity.entities.world.chunk;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.core.io.IO;
import org.egordorichev.lasttry.util.log.Log;

import java.io.FileNotFoundException;

public class ChunkIO extends IO<Chunk> {
	/**
	 * Current save format version
	 */
	public static byte VERSION = 0;

	/**
	 * Saves entity
	 *
	 * @param chunk Entity to save
	 */
	public static void write(Chunk chunk) {
		short x = chunk.getX();
		short y = chunk.getY();

		Log.debug("Saving chunk " + x + ":" + y);

		try {
			FileWriter writer = new FileWriter("chunks/" + x + ":" + y + ".cnk"); // TODO: world folders

			writer.writeByte(VERSION); // Chunk file version

			for (short by = 0; y < Chunk.SIZE; y++) {
				for (short bx = 0; x < Chunk.SIZE; x++) {
					writer.writeString(chunk.getBlock(bx, by));
					writer.writeString(chunk.getWall(bx, by));
					writer.writeInt32(chunk.getData(bx, by));
				}
			}

			writer.close();
		} catch (Exception exception) {
			Log.error("Failed to save chunk " + x + ":" + y);
		}
	}

	/**
	 * Loads chunk with coords
	 * (used for chunks)
	 *
	 * @param x Chunk X
	 * @param y Chunk Y
	 *
	 * @return Loaded chunk
	 */
	public static Chunk load(short x, short y) {
		Log.debug("Loading chunk " + x + ":" + y);

		try {
			FileReader reader = new FileReader("chunks/" + x + ":" + y + ".cnk"); // TODO: world folders
			Chunk chunk = new Chunk(x, y);

			byte version = reader.readByte();

			if (version != VERSION) {
				Log.error("Chunk version " + version + " is not supported");
				return null;
			}

			for (short by = 0; y < Chunk.SIZE; y++) {
				for (short bx = 0; x < Chunk.SIZE; x++) {
					String block = reader.readString();
					String wall = reader.readString();

					chunk.setBlock(block, bx, by);
					chunk.setWall(wall, bx, by);
					chunk.setData(reader.readInt16(), bx, by);
				}
			}

			return chunk;
		} catch (FileNotFoundException exception) {
			Chunk chunk = new Chunk(x, y);

			write(chunk);

			return chunk; // TODO: generate it
		} catch (Exception exception) {
			Log.error("Failed to load chunk " + x + ":" + y);
		}

		return null;
	}
}