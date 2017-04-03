package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class DayBloom extends Plant {
	public DayBloom() {
		super(ItemID.dayBloom, "Day Bloom", Textures.dayBloomIcon, Textures.dayBloom);
	}

	@Override
	public void updateBlock(int x, int y) {
		int hp = LastTry.world.getBlockHp(x, y);

		if (hp >= Plant.GROW_THRESHOLD) {
			if (LastTry.environment.time.isDay() && LastTry.random.nextInt(10) != 0) {
				LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
			} else {
				LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD), x, y);
			}
		} else {
			LastTry.world.setBlockHP((byte) (hp + 1), x, y);
		}
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = LastTry.world.getBlockID(x, y + 1);

		if (id != ItemID.grassBlock) {
			return false;
		}

		return true;
	}
}