package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;

public class DayBloom extends Plant {

	public DayBloom(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y - 1);
		return id.equals("lt:dirt");
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.isDay() && LastTry.random.nextInt(10) != 0;
	}
}