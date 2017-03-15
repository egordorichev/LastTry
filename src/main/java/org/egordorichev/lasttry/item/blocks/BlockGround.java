package org.egordorichev.lasttry.item.blocks;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class BlockGround extends Block {
	public BlockGround(int id, String name, boolean solid, Image tiles) {
		super(id, name, solid, tiles);
	}

	@Override
	public void renderBlock(TileData data, int x, int y) {
		boolean t = LastTry.world.getBlock(x, y - 1) instanceof BlockGround;
		boolean r = LastTry.world.getBlock(x + 1, y) instanceof BlockGround;
		boolean b = LastTry.world.getBlock(x, y + 1) instanceof BlockGround;
		boolean l = LastTry.world.getBlock(x - 1, y) instanceof BlockGround;

		this.texture.getSubImage(this.calculateBinary(t, r, b, l) * Block.TEX_SIZE, data.variant * Block.TEX_SIZE,
				Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
	}
}
