package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Wall extends Item {
	public static final Wall dirt = new Wall(2, "Dirt wall", Assets.dirtWallTexture);
	protected Image texture;

	public Wall(int id, String name, Image texture) {
		super(id, name, Item.Type.WALL);

		this.texture = texture;
	}

	public void renderWall(TileData data, int x, int y) {
		boolean t = LastTry.world.getWallId(x, y - 1) == this.id;
		boolean r = LastTry.world.getWallId(x + 1, y) == this.id;
		boolean b = LastTry.world.getWallId(x, y + 1) == this.id;
		boolean l = LastTry.world.getWallId(x - 1, y) == this.id;

		this.texture.getSubImage(this.calculateBinary(t, r, b, l) * Block.size, data.variant * Block.size, Block.size, Block.size).draw(x * Block.size, y * Block.size);
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

	public static Wall getForBlockId(int id) {
		switch(id) {
			case 0: return null;
			case 1: return Wall.dirt;
			default: return null;
		}
	}
}