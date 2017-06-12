package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.BlockGround;

public class Grass extends BlockGround {
	public Grass(String id) {
		super(id);
	}

	@Override
	public void updateBlock(int x, int y) {
		// TODO: spread it
	}

	private void spread(int x, int y) {
		Block block = (Block) Item.fromID(Globals.getWorld().blocks.getID(x, y));

		if (block != null && this.canBeGrownAt(block.getID())) {
			Globals.getWorld().blocks.set(this.id, x, y);
		}
	}

	public boolean canBeGrownAt(String id) {
		return id.equals("lt:grass_block");
	}
}