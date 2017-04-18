package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.world.components.WorldChunksComponent;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;
import org.egordorichev.lasttry.world.components.WorldRenderComponent;

public class World {
	public enum Size {
		/** 4200x1200 blocks */
		SMALL("Small"),

		/** 6400x1800 blocks */
		MEDIUM("Medium"),

		/** 8400x2400 blocks */
		LARGE("Large");

		private String name;

		Size(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public static final int UPDATE_DELAY = 20;

	public WorldFlagsComponent flags;
	public WorldChunksComponent chunks;
	public WorldRenderComponent renderer;
	private short width;
	private short height;

	public World(short width, short height, int flags) {
		this.chunks = new WorldChunksComponent(this);
		this.renderer = new WorldRenderComponent(this);
		this.flags = new WorldFlagsComponent(this, flags);

		this.width = width;
		this.height = height;
	}

	public void render() {
		this.renderer.render();
	}

	public void update() {
		// TODO
	}

	public short getWidth() {
		return this.width;
	}

	public short getHeight() {
		return this.height;
	}
}