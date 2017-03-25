package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.Wall;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Util;

public class World {
	/** Shows, that world has crimson, not corruption biome */
	public static final int CRIMSON = 1;

	/** Shows, that world has entred hardmode */
	public static final int HARDMODE = 2;

	/** Shows, that world is in expert mode */
	public static final int EXPERT = 3;

	/** Shows, that world is in hardcode mode */
	public static final int HARDCORE = 4;

	/** How often the whole world is updated */
	public static final int UPDATE_DELAY = 5;

	/** Data about all of the blocks and walls in the world */
	private WorldData data;

	/** World width in blocks */
	private short width;

	/** World height in blocks */
	private short height;

	/** World flags */
	private int flags;

	public World(short width, short height, int flags, WorldData data) {
		this.width = width;
		this.height = height;
		this.flags = flags;
		this.data = data;

		Util.runInThread(new Callable() {
			@Override
			public void call() {
				updateWorld();
			}
		}, UPDATE_DELAY);
	}

	/**
	 * Renders all blocks on screen
	 */
	public void render() {
		int windowWidth = LastTry.getWindowWidth();
		int windowHeight = LastTry.getWindowHeight();
		int tww = windowWidth / Block.TEX_SIZE;
		int twh = windowHeight / Block.TEX_SIZE;
		int tcx = (int) LastTry.camera.getX() / Block.TEX_SIZE;
		int tcy = (int) LastTry.camera.getY() / Block.TEX_SIZE;

		int minY = Math.max(0, tcy - 2);
		int maxY = Math.min(this.height - 1, tcy + twh + 2);
		int minX = Math.max(0, tcx - 2);
		int maxX = Math.min(this.width - 1, tcx + tww + 2);

		// Iterate coordinates, exclude ones not visible to the camera
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				Wall wall = (Wall) Item.fromID(this.getWallID(x, y));
				Block block = (Block) Item.fromID(this.getBlockID(x, y));

				if (wall != null) {
					wall.renderWall(x, y);
				}

				if (block != null) {
					block.updateBlockStyle(x, y);
					block.renderBlock(x, y);
				}
			}
		}
	}

	/**
	 * Updates the whole world. Runs once in UPDATE_DELAY seconds
	 */
	private void updateWorld() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Block block = (Block) Item.fromID(this.getBlockID(x, y));

				if (block != null) {
					block.updateBlock(x, y);
				}
			}
		}
	}

	/**
	 * Checks if a block can be placed in the world at the given coordinates.
	 *
	 * @param block
	 *            Block to be placed.
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 * @return If the block can be placed.
	 */
	public boolean canPlaceInWorld(Block block, int x, int y) {
		/*
		 * TODO: Some blocks should be able to be placed in different situations
		 * For example, you can place dirt on a shrub (Shrub is destroyed)
		 *
		 * Maybe we should move this to the block class?
		 */

		return this.getBlockID(x, y) == ItemID.none;
	}

	/**
	 * Sets the block in the world at the given position based on the given ID.
	 * 
	 * @param id
	 *            Block ID
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 */
	public void setBlock(short id, int x, int y) {
		this.data.blocks[x + y * this.width] = id;
	}

	/**
	 * Sets the wall in the world at the given position based on the given ID.
	 * 
	 * @param id
	 *            Wall ID
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 */
	public void setWall(short id, int x, int y) {
		this.data.walls[x + y * this.width] = id;
	}

	/**
	 * Returns the ID of the block at the given position.
	 * 
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 * @return Block ID
	 */
	public short getBlockID(int x, int y) {
		return this.data.blocks[x + y * this.width];
	}

	/**
	 * Returns hit-points of the block at the given position.
	 * 
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 * @return Block hit-points
	 */
	public byte getBlockHp(int x, int y) {
		return this.data.blocksHealth[x + y * this.width];
	}

	/**
	 * Returns the ID of the wall at given position
	 * 
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 * @return Wall ID
	 */
	public short getWallID(int x, int y) {
		return this.data.walls[x + y * this.width];
	}

	/**
	 * Returns the hit-points of the wall at given position.
	 * 
	 * @param x
	 *            World x-position.
	 * @param y
	 *            World y-position.
	 * @return Wall hp
	 */
	public byte getWallHp(int x, int y) {
		return this.data.wallsHealth[x + y * this.width];
	}

	/**
	 * Returns true if the world's evil type is corruption.
	 * 
	 * @return true if world's evil type is corruption.
	 */
	public boolean evilIsCorruption() {
		return !this.evilIsCrimson();
	}

	/**
	 * Returns true if the world's evil type is crimson.
	 * 
	 * @return true, if world's evil type is crimson.
	 */
	public boolean evilIsCrimson() {
		return (this.flags & CRIMSON) == CRIMSON;
	}

	/**
	 * Returns true if the world is in expert mode.
	 * 
	 * @return true if the world is in expert mode.
	 */
	public boolean isExpertMode() {
		return (this.flags & EXPERT) == EXPERT;
	}

	/**
	 * Returns true if the world is in hardcore mode.
	 * 
	 * @return true if the world is in hardcore mode.
	 */
	public boolean isHardcore() {
		return (this.flags & HARDCORE) == HARDCORE;
	}

	/**
	 * Returns true if the world is in hardmode.
	 * 
	 * @return true, if the world is in hardmode.
	 */
	public boolean isHardmode() {
		return (this.flags & HARDMODE) == HARDCORE;
	}

	/**
	 * Returns the world's width.
	 * 
	 * @return World width in blocks
	 */
	public short getWidth() {
		return this.width;
	}

	/**
	 * Check if the given bounds collide with blocks in the world
	 *
	 * @param bounds
	 *            Bounds to check collision for.
	 * @return If bounds collide with world's blocks.
	 */
	public boolean isColliding(Rectangle bounds) {
		// Create the bounds and fit them to the world's grid.
		Rectangle gridBounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		gridBounds.x /= Block.TEX_SIZE;
		gridBounds.y /= Block.TEX_SIZE;
		gridBounds.width /= Block.TEX_SIZE;
		gridBounds.height /= Block.TEX_SIZE;

		// Iterate blocks and check for conditions
		for (int y = (int) gridBounds.y - 1; y < gridBounds.y + gridBounds.height + 1; y++) {
			for (int x = (int) gridBounds.x - 1; x < gridBounds.x + gridBounds.width + 1; x++) {
				// Prevent going out-of-bounds.
				if (!this.isInside(x, y)) {
					return true;
				}

				// Skip null/non-solid blocks
				Block block = (Block) Item.fromID(this.getBlockID(x, y));
				if (block == null || !block.isSolid()) {
					continue;
				}

				// Check if the parameter bounds intersect with the block at the
				// iterated coordiantes.
				Rectangle blockRect = new Rectangle(x * Block.TEX_SIZE, y * Block.TEX_SIZE, Block.TEX_SIZE,
						Block.TEX_SIZE);
				if (blockRect.intersects(bounds)) {
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Check if the given position resides within the world's bounds
	 *
	 * @param x
	 *            X-position to check.
	 * @param y
	 *            Y-position to check.
	 * @return Position is inside world.
	 */
	public boolean isInside(int x, int y) {
		return (x >= 0 && x < this.width && y >= 0 && y < this.height);
	}

	/**
	 * @return world height in blocks
	 */
	public short getHeight() {
		return this.height;
	}
}