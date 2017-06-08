package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;

public class WaterLeaf extends Plant {
	public WaterLeaf(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y - 1);
		return !id.equals("lt:sand");
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.isRaining();
	}
}