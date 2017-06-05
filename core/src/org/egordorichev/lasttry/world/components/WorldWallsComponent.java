package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldWallsComponent extends WorldComponent {
	public WorldWallsComponent(World world) {
		super(world);
	}

	public Wall get(int x, int y) {
		return (Wall) Item.fromID(this.getID(x, y));
	}

	public String getID(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return "";
		}

		return chunk.getWall(x, y);
	}

	public void set(String id, int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return ;
		}

		chunk.setWall(id, x, y);
	}

	public byte getHP(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return Block.MAX_HP;
		}

		return chunk.getWallHP(x, y);
	}

	public void setHP(byte hp, int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return;
		}


		chunk.setWallHP(hp, x, y);
	}

	private Chunk getChunk(int x, int y) {
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