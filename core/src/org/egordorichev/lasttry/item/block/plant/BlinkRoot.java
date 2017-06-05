package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class BlinkRoot extends Plant {
	public BlinkRoot() {
		super(ItemID.blinkRoot, "Blink Root", Assets.getTexture(Textures.blinkRootIcon), Assets.getTexture(Textures.blinkRoot));
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = Globals.getWorld().getBlockID(x, y - 1);

		if (id != ItemID.dirtBlock && id != ItemID.mudBlock) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean canBloom() {
		return LastTry.random.nextInt(3) == 0;
	}
}