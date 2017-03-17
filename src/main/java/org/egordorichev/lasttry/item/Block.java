package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
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
		boolean t = LastTry.world.getBlockId(x, y - 1) == this.id;
		boolean r = LastTry.world.getBlockId(x + 1, y) == this.id;
		boolean b = LastTry.world.getBlockId(x, y + 1) == this.id;
		boolean l = LastTry.world.getBlockId(x - 1, y) == this.id;

		this.tiles.getSubImage(this.calculateBinary(t, r, b, l) * Block.TEX_SIZE, data.variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
	}

	protected int calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
		int result = 0;

		if(top == true) {
			result += 1;
		}

		if(right == true) {
			result += 2;
		}

		if(bottom == true) {
			result += 4;
		}

		if(left == true) {
			result += 8;
		}

		return result;
	}

	public boolean isSolid() {
		return this.solid;
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
