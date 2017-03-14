package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.blocks.BlockGround;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Block extends Item {
	public static final int GRASS_BLOCK = 3;
	public static final int DIRT_BLOCK = 1;
	public static final Block DIRT = new BlockGround(Block.DIRT_BLOCK, "Dirt block", true, Assets.dirtTileTexture);
	public static final Block GRASS = new BlockGround(Block.GRASS_BLOCK, "Grass block", true, Assets.grassTileTexture);
	public static final int TEX_SIZE = 16;

	protected boolean solid;
	

	public Block(int id, String name, boolean solid, Image tiles) {
		super(id, name, Item.Type.BLOCK);

		this.texture = tiles;
		this.solid = solid;
	}

	public void renderBlock(TileData data, int x, int y) {
		boolean t = LastTry.world.getBlockId(x, y - 1) == this.id;
		boolean r = LastTry.world.getBlockId(x + 1, y) == this.id;
		boolean b = LastTry.world.getBlockId(x, y + 1) == this.id;
		boolean l = LastTry.world.getBlockId(x - 1, y) == this.id;

		this.texture.getSubImage(this.calculateBinary(t, r, b, l) * Block.TEX_SIZE, data.variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
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
	
	public static void preload() { }

}