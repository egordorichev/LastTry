package org.egordorichev.lasttry.item.blocks;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class BlockGround extends Block {
	public BlockGround(int id, String name, boolean solid, Image texture, Image tiles) {
		super(id, name, solid, texture, tiles);
	}

	@Override
	public void renderBlock(TileData data, int x, int y) {
		boolean t = LastTry.world.getBlock(x, y - 1) instanceof BlockGround;
		boolean r = LastTry.world.getBlock(x + 1, y) instanceof BlockGround;
		boolean b = LastTry.world.getBlock(x, y + 1) instanceof BlockGround;
		boolean l = LastTry.world.getBlock(x - 1, y) instanceof BlockGround;

		int binary = this.calculateBinary(t, r, b, l);

		if(binary == 15) {
			boolean tr = LastTry.world.getBlock(x + 1, y - 1) instanceof BlockGround;
			boolean br = LastTry.world.getBlock(x + 1, y + 1) instanceof BlockGround;
			boolean bl = LastTry.world.getBlock(x - 1, y + 1) instanceof BlockGround;
			boolean tl = LastTry.world.getBlock(x - 1, y - 1) instanceof BlockGround;

			int corner = this.calculateBinary(tr, br, bl, tl);

			this.texture.getSubImage(corner * Block.TEX_SIZE, 48 + data.variant * Block.TEX_SIZE,
					Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		} else {
			this.texture.getSubImage(binary * Block.TEX_SIZE, data.variant * Block.TEX_SIZE,
				Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		}
	}
}