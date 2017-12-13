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
	 * Saves chunk
	 *
	 * @param folder Chunk folder
	 * @param chunk Chunk to save
	 */
	public static void write(String folder, Chunk chunk) {
		short x = chunk.getX();
		short y = chunk.getY();

		Log.debug("Saving chunk " + x + ":" + y);

		try {
			FileWriter writer = new FileWriter(folder + "/" + x + ":" + y + ".cnk"); // TODO: world folders

			writer.writeByte(VERSION); // Chunk file version

			for (short by = 0; by < Chunk.SIZE; by++) {
				for (short bx = 0; bx < Chunk.SIZE; bx++) {
					writer.writeString(chunk.getBlock(bx, by));
					writer.writeString(chunk.getWall(bx, by));
					writer.writeInt32(chunk.getData(bx, by));
				}
			}

			writer.close();
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to save chunk " + x + ":" + y);
		}
	}

	/**
	 * Loads chunk with coords
	 * (used for chunks)
	 *
	 * @param folder Chunk folder
	 * @param x Chunk X
	 * @param y Chunk Y
	 *
	 * @return Loaded chunk
	 */
	public static Chunk load(String folder, short x, short y) {
		Log.debug("Loading chunk " + x + ":" + y);

		try {
			FileReader reader = new FileReader( folder + "/" + x + ":" + y + ".cnk"); // TODO: world folders
			Chunk chunk = new Chunk(x, y);

			byte version = reader.readByte();

			if (version != VERSION) {
				Log.error("Chunk version " + version + " is not supported");
				return null;
			}

			for (short by = 0; by < Chunk.SIZE; by++) {
				for (short bx = 0; bx < Chunk.SIZE; bx++) {
					chunk.setBlock(reader.readString(), bx, by);
					chunk.setWall(reader.readString(), bx, by);
					chunk.setData(reader.readInt32(), bx, by);
				}
			}

			reader.close();

			return chunk;
		} catch (FileNotFoundException exception) {
			Log.debug("Chunk " + x + ":" + y + " is not found");
			return new Chunk(x, y);
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load chunk " + x + ":" + y);
		}

		return new Chunk(x, y);
	}
}