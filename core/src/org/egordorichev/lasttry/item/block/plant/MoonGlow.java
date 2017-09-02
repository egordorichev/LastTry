package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;

public class MoonGlow extends Plant {
	public MoonGlow() {
		super("lt:moon_glow", "lt:mud");
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.isNight();
	}
}