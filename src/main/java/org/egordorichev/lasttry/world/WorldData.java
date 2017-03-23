package org.egordorichev.lasttry.world;

/**
 * Stores all data about the blocks and walls in the world
 */
public class WorldData {
	/** Block ID's */
	public short[] blocks;

	/** Blocks health, also used for storing data for plants and other */
	public byte[] blocksHealth;

	/** Wall ID's */
	public short[] walls;

	/** Walls health */
	public byte[] wallsHealth;

	public WorldData(int size) {
		this.blocks = new short[size];
		this.blocksHealth = new byte[size];
		this.walls = new short[size];
		this.wallsHealth = new byte[size];
	}
}