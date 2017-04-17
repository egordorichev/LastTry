package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldChunksComponent extends WorldComponent {
	private Chunk[] chunk;
	private int size;

	public WorldChunksComponent(World world) {
		super(world);

		this.size = world.getWidth() * world.getHeight();
		this.chunk = new Chunk[this.size];
	}

	public Chunk get(int x, int y) {
		int index = x + y * this.world.getWidth();

		if (index >= this.size) {
			return null;
		}

		return this.chunk[index];
	}
}