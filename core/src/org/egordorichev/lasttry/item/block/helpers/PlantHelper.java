package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.item.block.plant.Plant;
import org.egordorichev.lasttry.util.ByteHelper;

public class PlantHelper extends BlockHelper {
	@Override
	public byte getHP(byte data) {
		return 1;
	}

	public byte getGrowLevel(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 4);
	}

	public byte setGrowLevel(byte data, byte level) {
		for (int i = 0; i <= 4; i++) {
			data = ByteHelper.setBit(data, (byte) i, ByteHelper.bitIsSet(level, (byte) i));
		}
		return data;
	}

	public boolean isBlooming(byte data) {
		return this.getGrowLevel(data) > Plant.GROW_THRESHOLD;
	}

	public boolean hasGrown(byte data) {
		return this.getGrowLevel(data) >= Plant.GROW_THRESHOLD;
	}
}