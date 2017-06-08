package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;

public class BlinkRoot extends Plant {
	public BlinkRoot(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y - 1);
		return !(!id.equals("lt:dirt") && !id.equals("lt:mud"));
	}

	@Override
	protected boolean canBloom() {
		return LastTry.random.nextInt(3) == 0;
	}
}