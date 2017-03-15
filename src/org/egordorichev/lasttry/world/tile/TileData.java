package org.egordorichev.lasttry.world.tile;

import org.egordorichev.lasttry.item.blocks.Block;
import org.egordorichev.lasttry.item.blocks.Wall;

import java.util.Random;

public class TileData {
	// TODO: Should this be static?
	// Do different tiles have different hit-point values?
	public static final byte maxHp = 10;
	/**
	 * Block contained in the tile.
	 */
	public Block block;
	/**
	 * Wall contained in the tile.
	 */
	public Wall wall;
	/**
	 * Contained block's hit-points.
	 */
	public byte blockHp;
	/**
	 * Contained wall's hit-points.
	 */
	public byte wallHp;
	/**
	 * TODO
	 */
	public byte data;
	/**
	 * Texture variant. The variant is associated with the row of the textures
	 * used by the block and wall's respective images.
	 */
	public byte variant;

	public TileData(Block block, Wall wall) {
		this.block = block;
		this.wall = wall;
		this.blockHp = maxHp;
		this.wallHp = maxHp;
		this.data = 0;
		this.variant = (byte) new Random().nextInt(3);
	}

	/**
	 * Render the walls and blocks stored in the tile.
	 * 
	 * @param x
	 *            x-position to render at.
	 * @param y
	 *            y-position to render at.
	 */
	public void render(int x, int y) {
		if (wall != null) {
			wall.renderWall(this, x, y);
		}
		if (block != null) {
			block.renderBlock(this, x, y);
		}
	}
}