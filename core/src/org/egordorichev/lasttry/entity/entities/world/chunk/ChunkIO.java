package org.egordorichev.lasttry.entity.entities.world.chunk;

import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.core.io.IO;
import org.egordorichev.lasttry.util.log.Log;

public class ChunkIO extends IO<Chunk> {
	/**
	 * Saves entity
	 *
	 * @param chunk Entity to save
	 */
	public static void write(Chunk chunk) {
		try {
			FileWriter writer = new FileWriter("");
		} catch (Exception exception) {
			Log.error("Failed to save chunk " + chunk.getX() + ":" + chunk.getY());
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
		// TODO

		return new Chunk(x, y);
	}
}