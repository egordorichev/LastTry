package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.world.components.WorldRenderComponent;

public class World {
	public WorldChunksComponent chunks;
	public WorldRenderComponent renderer;
	public short width;
	public short height;

	public World(short width, short height) {
		this.width = width;
		this.height = height;
		this.chunks = WorldChunksComponent(this);
		this.renderer = WorldRenderComponent(this);
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