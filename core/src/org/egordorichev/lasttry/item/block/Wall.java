package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;

public class Wall extends Item {
	protected Texture tiles;

	public Wall(short id, String name, Texture texture, Texture tiles) {
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
		int binary = Block.calculateBinary(t, r, b, l);

		LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE,
			(LastTry.world.getHeight() - y - 1) * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE,
			Block.TEX_SIZE * (binary), variant * Block.TEX_SIZE, Block.TEX_SIZE,
			Block.TEX_SIZE, false, false);
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