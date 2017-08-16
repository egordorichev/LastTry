package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldBlocksComponent extends WorldComponent {
	public WorldBlocksComponent(World world) {
		super(world);
	}

	// In grid points
	public Block get(int x, int y) {
		return (Block) Item.fromID(this.getID(x, y));
	}

	public byte getLight(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return 0;
		}

		return chunk.getLight(x, y);
	}

	public void setLight(int x, int y, byte light) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return;
		}

		chunk.setLight(light, x, y);
	}

	public String getID(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return null;
		}

		return chunk.getBlock(x, y);
	}

	public void set(String id, int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return;
		}

		chunk.setBlock(id, x, y);
		this.updateNeighbors((short) x, (short) y);
	}

	public byte getHP(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return Block.MAX_HP;
		}

		return chunk.getBlockHP(x, y);
	}

	public void setHP(byte hp, int x, int y, boolean die) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return;
		}

		chunk.setBlockHP(hp, x, y, die);

		if (die) {
			this.updateNeighbors((short) x, (short) y);
		}
	}

	public void setHP(byte hp, int x, int y) {
		this.setHP(hp, x, y, false);
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

	private void updateNeighbors(short x, short y) {
		for (short by = (short) (y - 1); by < y + 2; by++) {
			for (short bx = (short) (x - 1); bx < x + 2; bx++) {
				Block block = this.get(bx, by);

				if (block != null) {
					block.onNeighborChange(bx, by, x, y);
				}
			}
		}
	}
}