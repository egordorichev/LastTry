package org.egordorichev.lasttry.world.chunk;

public class ChunkData {
	public String[] blocks;
	public byte[] blocksHealth;
	public String[] walls;
	public byte[] wallsHealth;
	public byte[] light;

	public ChunkData() {
		this.light = new byte[Chunk.TOTAL_SIZE];
		this.blocks = new String[Chunk.TOTAL_SIZE];
		this.blocksHealth = new byte[Chunk.TOTAL_SIZE];
		this.walls = new String[Chunk.TOTAL_SIZE];
		this.wallsHealth = new byte[Chunk.TOTAL_SIZE];
	}
}