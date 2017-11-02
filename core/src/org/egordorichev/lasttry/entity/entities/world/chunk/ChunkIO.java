package org.egordorichev.lasttry.entity.entities.world.chunk;

import org.egordorichev.lasttry.core.io.IO;

public class ChunkIO extends IO<Chunk> {
	/**
	 * Saves entity
	 *
	 * @param entity Entity to save
	 */
	public static void write(Chunk entity) {
		// TODO
	}

	/**
	 * Loads entity with coords
	 * (used for chunks)
	 *
	 * @param x Entity X
	 * @param y Entity Y
	 *
	 * @return Loaded entity
	 */
	public static Chunk load(short x, short y) {
		// TODO

		return new EmptyChunk(x, y);
	}
}