package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class WaterLeaf extends Plant {
	public WaterLeaf() {
		super(ItemID.waterLeaf, "Water Leaf", Assets.getTexture(Textures.waterLeafIcon), Assets.getTexture(Textures.waterLeaf));
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = Globals.getWorld().getBlockHP(x, y - 1);

		if (id != ItemID.sandBlock) {
			return false;
		}

		return true;
	}

	@Override
	public void updateBlock(int x, int y) {
		int hp = getGrowLevel(x, y);

		if (hp >= Plant.GROW_THRESHOLD) {
			if (Globals.environment.isRaining()) {
				setGrowLevel((byte) (Plant.GROW_THRESHOLD + 1), x, y);
			} else {
				setGrowLevel((byte) (Plant.GROW_THRESHOLD), x, y);
			}
		} else {
            setGrowLevel((byte) (hp + 1), x, y);
		}
	}
}