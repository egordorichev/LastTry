package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class MoonGlow extends Plant {
	public MoonGlow() {
		super(ItemID.moonGlow, "Moon Glow", Assets.getTexture(Textures.moonGlowIcon), Assets.getTexture(Textures.moonGlow));
	}

	@Override
	public void updateBlock(int x, int y) {
		int hp = getGrowLevel(x, y);

		if (hp >= Plant.GROW_THRESHOLD) {
			if (Globals.environment.time.isNight()) {
				setGrowLevel((byte) (Plant.GROW_THRESHOLD + 1), x, y);
			} else {
				setGrowLevel((byte) (Plant.GROW_THRESHOLD), x, y);
			}
		} else {
			setGrowLevel((byte) (hp + 1), x, y);
		}
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
}