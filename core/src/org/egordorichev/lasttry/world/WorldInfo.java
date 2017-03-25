package org.egordorichev.lasttry.world;

public class WorldInfo {
	/** World name */
	private String name;

	/** World size */
	private WorldSize size;

	/** World version */
	private int version;

	/** World stats */
	private int flags;

	public WorldInfo(String name, WorldSize size, int version, int flags) {
		this.name = name;
		this.size = size;
		this.version = version;
		this.flags = flags;
	}

	/**
	 * Returns world name
	 * @return world name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns world size
	 * @return world size
	 */
	public WorldSize getSize() {
		return this.size;
	}

	/**
	 * Returns world provider version
	 * @return world provider version
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * Returns world flags
	 * @return world flags
	 */
	public int getFlags() {
		return this.flags;
	}
}