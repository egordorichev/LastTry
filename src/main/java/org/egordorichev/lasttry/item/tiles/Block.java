package org.egordorichev.lasttry.item.tiles;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Block extends Item {
	public static final int TEX_SIZE = 16;
	protected boolean solid;
	protected Image tiles;

	public Block(int id, String name, boolean solid, Image texture, Image tiles) {
		super(id, name, texture);

		this.tiles = tiles;
		this.solid = solid;
	}

	public void renderBlock(TileData data, int x, int y) {
		boolean t = LastTry.world.getBlockID(x, y - 1) == this.id;
		boolean r = LastTry.world.getBlockID(x + 1, y) == this.id;
		boolean b = LastTry.world.getBlockID(x, y + 1) == this.id;
		boolean l = LastTry.world.getBlockID(x - 1, y) == this.id;

		this.tiles.getSubImage(this.calculateBinary(t, r, b, l) * Block.TEX_SIZE, data.variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
	}

	public static int calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
		int result = 0;

		if (top == true) {
			result += 1;
		}

		if (right == true) {
			result += 2;
		}

		if (bottom == true) {
			result += 4;
		}

		if (left == true) {
			result += 8;
		}

		return result;
	}

	@Override
	public boolean use() {
		int x = LastTry.world.getMouseXInWorld() / Block.TEX_SIZE;
		int y = LastTry.world.getMouseYInWorld() / Block.TEX_SIZE;

		if (LastTry.world.getBlockID(x, y) == ItemID.none) {
			Rectangle rectangle = LastTry.player.getHitbox();

			if (!rectangle.intersects(new Rectangle(x * TEX_SIZE, y * TEX_SIZE, TEX_SIZE, TEX_SIZE))) {
				LastTry.world.setBlock(this, x, y);

				return true;
			}
		}

		return false;
	}

	public boolean isSolid() {
		return this.solid;
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
