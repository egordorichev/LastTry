package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class FireBlossom extends Plant {
	public FireBlossom(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y + 1);
		return id.equals("lt:ash");

	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.getHour() >= 4 && Globals.environment.time.getHour() <= 7
			&& !Globals.environment.isRaining();
	}
}