package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class FireBlossom extends Plant {
	public FireBlossom() {
		super(ItemID.fireBlossom, "Fire Blossom", Assets.getTexture(Textures.fireBlossomIcon), Assets.getTexture(Textures.fireBlossom));
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = Globals.getWorld().getBlockID(x, y + 1);

		if (id == ItemID.ashBlock) {
			return true;
		}

		return false;
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.getHour() >= 4 && Globals.environment.time.getHour() <= 7
			&& !Globals.environment.isRaining();
	}
}