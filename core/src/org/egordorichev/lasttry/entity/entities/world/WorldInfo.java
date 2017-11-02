package org.egordorichev.lasttry.entity.entities.world;

/**
 * Stores some info about the world
 */
public class WorldInfo {
	/**
	 * World width in chunks
	 */
	private short width;
	/**
	 * World height in chunk
	 */
	private short height;

	public WorldInfo(int width, int height) {
		this.width = (short) width;
		this.height = (short) height;
	}

	/**
	 * @return World width in chunks
	 */
	public short getWidth() {
		return this.width;
	}

	/**
	 * @return World height in chunks
	 */
	public short getHeight() {
		return this.height;
	}
}