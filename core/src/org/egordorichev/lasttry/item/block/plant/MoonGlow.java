package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class MoonGlow extends Plant {
	public MoonGlow() {
		super(ItemID.moonGlow, "Moon Glow", Assets.getTexture(Textures.moonGlowIcon), Assets.getTexture(Textures.moonGlow));
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = Globals.getWorld().getBlockHP(x, y - 1);

		if (id != ItemID.jungleGrassBlock) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.time.isNight();
	}
}