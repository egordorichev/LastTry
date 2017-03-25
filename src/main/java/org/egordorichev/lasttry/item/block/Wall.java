package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.newdawn.slick.Image;

public class Wall extends Item {
	protected Image tiles;

	public Wall(short id, String name, Image texture, Image tiles) {
		super(id, name, texture);

		this.tiles = tiles;
	}

	/**
	 * Renders the wall at the given coordinates.
	 * 
	 * @param x
	 *            X-position in the world.
	 * @param y
	 *            Y-position in the world.
	 */
	public void renderWall(int x, int y) {
		boolean t = LastTry.world.getWallID(x, y - 1) == this.id;
		boolean r = LastTry.world.getWallID(x + 1, y) == this.id;
		boolean b = LastTry.world.getWallID(x, y + 1) == this.id;
		boolean l = LastTry.world.getWallID(x - 1, y) == this.id;

		// TODO: FIXME: replace with variable
		int variant = 1; 

		this.tiles.getSubImage(Block.calculateBinary(t, r, b, l) * Block.TEX_SIZE, variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}

	public static Wall getForBlockID(int id) {
		switch(id) {
			case ItemID.none: default: return null;
			case ItemID.dirtBlock: return (Wall) Item.dirtWall;
		}
	}
}