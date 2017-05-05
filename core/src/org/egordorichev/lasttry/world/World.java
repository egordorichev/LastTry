package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.world.components.WorldBlocksComponent;
import org.egordorichev.lasttry.world.components.WorldChunksComponent;
import org.egordorichev.lasttry.world.components.WorldFlagsComponent;
import org.egordorichev.lasttry.world.components.WorldWallsComponent;

public class World {
	public static final int UPDATE_DELAY = 20;

	public WorldFlagsComponent flags;
	public WorldChunksComponent chunks;
	public WorldBlocksComponent blocks;
	public WorldWallsComponent walls;
	private Size size;
	private String name;

	public World(String name, Size size, int flags) {
		this.size = size;
		this.name = name;

		this.chunks = new WorldChunksComponent(this);
		this.flags = new WorldFlagsComponent(this, flags);
		this.blocks = new WorldBlocksComponent(this);
		this.walls = new WorldWallsComponent(this);
	}

	public void render() {
		this.chunks.render();
	}

	public void update() {
		this.chunks.update();
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

	public short getMaxChunks() { return this.size.getMaxChunks(); }

	// GridPoints
	public boolean isInside(int x, int y) {
		return (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight());
	}

	public boolean isColliding(Rectangle bounds) {
		Rectangle gridBounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		gridBounds.x /= Block.SIZE;
		gridBounds.y /= Block.SIZE;
		gridBounds.width /= Block.SIZE;
		gridBounds.height /= Block.SIZE;

		for (int y = (int) gridBounds.y - 1; y < gridBounds.y + gridBounds.height + 1; y++) {
			for (int x = (int) gridBounds.x - 1; x < gridBounds.x + gridBounds.width + 1; x++) {
				if (!this.isInside(x, y)) {
					return true;
				}

				Block block = (Block) Item.fromID(this.blocks.getID(x, y));

				if (block == null || !block.isSolid()) {
					continue;
				}

				Rectangle blockRect = new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE);

				if (blockRect.intersects(bounds)) {
					return true;
				}
			}
		}

		return false;
	}

	public enum Size {
		SMALL("Small", 4096, 1024, 64),
		MEDIUM("Medium", 6144, 2048, 192),
		LARGE("Large", 8192, 2304, 288);

		private String name;
		private short width;
		private short height;
		private short maxChunks;

		Size(String name, int width, int height, int maxChunks) {
			this.name = name;
			this.width = (short) width;
			this.height = (short) height;
			this.maxChunks = (short) maxChunks;
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

		public short getMaxChunks() { return this.maxChunks; }
	}
}