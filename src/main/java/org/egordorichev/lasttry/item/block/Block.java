package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Rectangle;
import org.newdawn.slick.Image;

public class Block extends Item {
	public static final int TEX_SIZE = 16;
	protected boolean solid;
	/**
	 * The tool type to use for the block.
	 */
	protected EffectiveToolType type;
	/**
	 * The block spite-sheet.
	 */
	protected Image tiles;

	public Block(short id, String name, boolean solid, EffectiveToolType type, Image texture, Image tiles) {
		super(id, name, texture);
		this.type = type;
		this.tiles = tiles;
		this.solid = solid;
	}

	/**
	 * Renders the block at the given coordinates.
	 * 
	 * @param x
	 *            X-position in the world.
	 * @param y
	 *            Y-position in the world.
	 */
	public void renderBlock(int x, int y) {
		boolean t = LastTry.world.getBlockID(x, y - 1) == this.id;
		boolean r = LastTry.world.getBlockID(x + 1, y) == this.id;
		boolean b = LastTry.world.getBlockID(x, y + 1) == this.id;
		boolean l = LastTry.world.getBlockID(x - 1, y) == this.id;

		// TODO: FIXME: replace with var
		short variant = 1;
		byte binary = Block.calculateBinary(t, r, b, l);

		if (binary == 15) {
			this.tiles.getSubImage(15 * Block.TEX_SIZE, 48 + variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE)
					.draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		} else {
			this.tiles.getSubImage(binary * Block.TEX_SIZE, variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE)
					.draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		}
	}

	/**
	 * Calculates a number based on the edges that have blocks of the same type.
	 * 
	 * @param top
	 *            Top edge matches current type.
	 * @param right
	 *            Right edge matches current type.
	 * @param bottom
	 *            Bottom edge matches current type.
	 * @param left
	 *            Left edge matches current type.
	 * @return
	 */
	public static byte calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
		byte result = 0;
		if (top)
			result += 1;
		if (right)
			result += 2;
		if (bottom)
			result += 4;
		if (left)
			result += 8;
		return result;
	}

	/**
	 * Attempts to place the block in the world at the player's cursor.
	 */
	@Override
	public boolean use() {
		// Get world position to place block at
		int x = LastTry.getMouseXInWorld() / Block.TEX_SIZE;
		int y = LastTry.getMouseYInWorld() / Block.TEX_SIZE;
		// TODO: Distance checks from cursor coordinates to player coordinates

		// Check if the block can be placed.
		if (LastTry.world.canPlaceInWorld(x, y, this)) {
			// Check if the block intersects the player's hitbox
			// TODO: Check othter entities in the world
			Rectangle rectangle = LastTry.player.getHitbox();
			if (rectangle.intersects(new Rectangle(x * TEX_SIZE, y * TEX_SIZE, TEX_SIZE, TEX_SIZE))) {
				return false;
			}
			LastTry.world.setBlock(this.id, x, y);
			return true;
		}
		return false;
	}

	/**
	 * Returns the the tool type that is most effective against the block.
	 * 
	 * @return Effective tool type.
	 */
	public EffectiveToolType getEffectiveToolType() {
		// TODO: Make it so that drills and pickaxes are treated the same
		// Also similiar check for hamaxes.
		return this.type;
	}

	/**
	 * Returns the solidity of the block.
	 * 
	 * @return true if the block is solid.
	 */
	public boolean isSolid() {
		return this.solid;
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
