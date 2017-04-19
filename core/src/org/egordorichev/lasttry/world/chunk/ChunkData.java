package org.egordorichev.lasttry.world.chunk;

public class ChunkData {
	public short[] blocks;
	public byte[] blocksHealth;
	public short[] walls;
	public byte[] wallsHealth;

	public ChunkData() {
		this.blocks = new short[Chunk.TOTAL_SIZE];
		this.blocksHealth = new byte[Chunk.TOTAL_SIZE];
		this.walls = new short[Chunk.TOTAL_SIZE];
		this.wallsHealth = new byte[Chunk.TOTAL_SIZE];
	}
}