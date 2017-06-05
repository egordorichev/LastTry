package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class SilverThorn extends Plant {
	public SilverThorn(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		String id = Globals.getWorld().blocks.getID(x, y - 1);
		return id.equals("lt:snow") || id.equals("lt:ice");
	}
}