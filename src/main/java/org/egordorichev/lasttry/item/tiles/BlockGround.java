package org.egordorichev.lasttry.item.tiles;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class BlockGround extends Block {
	public BlockGround(int id, String name, Image texture, Image tiles) {
		super(id, name, true, texture, tiles);
	}

	@Override
	public void renderBlock(TileData data, int x, int y) {
		Block t = LastTry.world.getBlock(x, y - 1);
		Block r = LastTry.world.getBlock(x + 1, y);
		Block b = LastTry.world.getBlock(x, y + 1);
		Block l = LastTry.world.getBlock(x - 1, y);

		int binary = this.calculateBinary((t instanceof BlockGround), (r instanceof BlockGround), (b instanceof BlockGround), (l instanceof BlockGround));

		if(binary == 15) {
			/*boolean tr = LastTry.world.getBlock(x + 1, y - 1) instanceof BlockGround;
			boolean br = LastTry.world.getBlock(x + 1, y + 1) instanceof BlockGround;
			boolean bl = LastTry.world.getBlock(x - 1, y + 1) instanceof BlockGround;
			boolean tl = LastTry.world.getBlock(x - 1, y - 1) instanceof BlockGround;

			int corner = this.calculateBinary(tr, br, bl, tl);
			*/

			int corner = 15; // TODO
			
			this.tiles.getSubImage(corner * Block.TEX_SIZE, 48 + data.variant * Block.TEX_SIZE,
				Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		} else {
			this.tiles.getSubImage(binary * Block.TEX_SIZE, data.variant * Block.TEX_SIZE,
				Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		}
	}
}