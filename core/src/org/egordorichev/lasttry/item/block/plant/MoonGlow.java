package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;

public class MoonGlow extends Plant {
	public MoonGlow() {
		super("lt:moon_glow");
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y - 1);
		return id.equals("lt:mud");
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.isNight();
	}
}