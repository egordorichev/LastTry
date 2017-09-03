package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;

public class EndlessBucket extends Tool {
	public EndlessBucket(String id) {
		super(id);

		this.useDelayMax = 1;
		this.autoSwing = true;
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		if (Globals.getWorld().blocks.get(x, y) == null) {
			byte hp = Globals.getWorld().blocks.getHP(x, y);
			hp = BlockHelper.empty.setLiquidLevel(hp, (byte) 15);
			Globals.getWorld().blocks.setHP(hp, x, y);
		}

		return false;
	}
}