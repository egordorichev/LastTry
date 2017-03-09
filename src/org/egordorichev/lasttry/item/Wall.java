package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Wall extends Item {
	public static final Wall dirt = new Wall(2, "Dirt wall", Assets.dirtWallTexture);

	protected Image tiles;

	public Wall(int id, String name, Image tiles) {
		super(id, name, Item.Type.WALL);

		this.tiles = tiles;
	}

	public void renderWall(TileData data, int x, int y) {
		if(this.id != 0 && this.tiles != null) {
			boolean t = LastTry.world.getWallId(x, y - 1) == this.id;
			boolean r = LastTry.world.getWallId(x + 1, y) == this.id;
			boolean b = LastTry.world.getWallId(x, y + 1) == this.id;
			boolean l = LastTry.world.getWallId(x - 1, y) == this.id;

			this.tiles.getSubImage(this.calculateBinary(t, r, b, l) * Block.size, data.variant * Block.size, Block.size, Block.size).draw(x * Block.size, y * Block.size);		}
	}

	public static void preload() {

	}

	private int calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
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

	// TODO: use and stuff
}