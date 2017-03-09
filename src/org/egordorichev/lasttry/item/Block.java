package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Block extends Item {
	public static final Block dirt = new Block(1, "Dirt block", Assets.dirtTileTexture);
	public static int size = 16;

	protected Image tiles;

	public Block(int id, String name, Image tiles) {
		super(id, name, Item.Type.BLOCK);

		this.tiles = tiles;
	}

	public void renderBlock(TileData data, int x, int y) {
		if(this.id != 0 && this.tiles != null) {
			boolean t = LastTry.world.getBlockId(x, y - 1) == this.id;
			boolean r = LastTry.world.getBlockId(x + 1, y) == this.id;
			boolean b = LastTry.world.getBlockId(x, y + 1) == this.id;
			boolean l = LastTry.world.getBlockId(x - 1, y) == this.id;

			this.tiles.getSubImage(this.calculateBinary(t, r, b, l) * size, data.variant * size, size, size).draw(x * size, y * size);
		}
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