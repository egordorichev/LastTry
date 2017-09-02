package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;

public class DayBloom extends Plant {
	public DayBloom(String id) {
		super(id);
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.isDay() && LastTry.random.nextInt(10) != 0;
	}
}