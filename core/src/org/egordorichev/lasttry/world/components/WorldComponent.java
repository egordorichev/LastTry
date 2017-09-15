package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.WorldProvderImpl;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldComponent implements Component {
	protected WorldProvderImpl world;

	public WorldComponent(WorldProvderImpl world) {
		this.world = world;
	}
	
	protected Chunk getChunk(int x, int y) {
		if (!this.world.isInside(x, y)) {
			return null;
		}
		Chunk chunk = this.world.chunks.getFor(x, y);
		if (chunk == null) {
			Globals.getWorld().chunks.load(x / Chunk.SIZE, y / Chunk.SIZE);
			return null;
		}
		return chunk;
	}
}