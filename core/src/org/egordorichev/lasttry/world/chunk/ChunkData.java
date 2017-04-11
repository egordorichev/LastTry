package org.egordorichev.lasttry.world.chunk;

/**
 * Stores all data about the blocks and walls in the chunk
 */
public class ChunkData {
	/**
	 * Block ID's
	 */
	public short[] blocks;

	/**
	 * Blocks health, also used for storing data for plants and other
	 */
	public byte[] blocksHealth;

	/**
	 * Wall ID's
	 */
	public short[] walls;

	/**
	 * Walls health
	 */
	public byte[] wallsHealth;

	public ChunkData() {
		this.blocks = new short[Chunk.SIZE];
		this.blocksHealth = new byte[Chunk.SIZE];
		this.walls = new short[Chunk.SIZE];
		this.wallsHealth = new byte[Chunk.SIZE];
	}
}