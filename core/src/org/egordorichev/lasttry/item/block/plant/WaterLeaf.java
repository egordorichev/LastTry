package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;

public class WaterLeaf extends Plant {
	public WaterLeaf(String id) {
		super(id, "lt:sand");
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.isRaining();
	}
}