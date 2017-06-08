package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.util.ByteHelper;

public class BlockGround extends Block {
	public BlockGround(String id) {
		super(id);
	}

	@Override
	public byte calculateBinary(int x, int y) {
		boolean t = Globals.getWorld().blocks.get(x, y + 1) instanceof BlockGround;
		boolean r = Globals.getWorld().blocks.get(x + 1, y) instanceof BlockGround;
		boolean b = Globals.getWorld().blocks.get(x, y - 1) instanceof BlockGround;
		boolean l = Globals.getWorld().blocks.get(x - 1, y) instanceof BlockGround;

		return ByteHelper.create(t, r, b, l, false, false, false , false);
	}
}