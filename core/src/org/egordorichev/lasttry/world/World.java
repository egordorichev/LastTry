package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.world.components.WorldChunksComponent;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;
import org.egordorichev.lasttry.world.components.WorldRenderComponent;

public class World {
	public enum Size {
		SMALL("Small", 4096, 1024),
		MEDIUM("Medium", 6144, 2048),
		LARGE("Large", 8192, 2304);

		private String name;
		private short width;
		private short height;

		Size(String name, int width, int height) {
			this.name = name;
			this.width = (short) width;
			this.height = (short) height;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public short getWidth() {
			return this.width;
		}

		public short getHeight() {
			return this.height;
		}
	}

	public static final int UPDATE_DELAY = 20;

	public WorldFlagsComponent flags;
	public WorldChunksComponent chunks;
	public WorldRenderComponent renderer;
	private Size size;
	private String name;

	public World(String name, Size size, int flags) {
		this.chunks = new WorldChunksComponent(this);
		this.renderer = new WorldRenderComponent(this);
		this.flags = new WorldFlagsComponent(this, flags);

		this.size = size;
		this.name = name;
	}

	public void render() {
		this.renderer.render();
	}

	public void update() {
		// TODO
	}

	public short getWidth() {
		return this.size.getWidth();
	}

	public short getHeight() {
		return this.size.getHeight();
	}

	public Size getSize() {
		return this.size;
	}

	public String getName() {
		return this.name;
	}
}