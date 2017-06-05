package org.egordorichev.lasttry.item.block.helpers;

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
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(level, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(level, (byte) 1));
		data = ByteHelper.setBit(data, (byte) 2, ByteHelper.bitIsSet(level, (byte) 2));
		data = ByteHelper.setBit(data, (byte) 3, ByteHelper.bitIsSet(level, (byte) 3));
		data = ByteHelper.setBit(data, (byte) 4, ByteHelper.bitIsSet(level, (byte) 4));

		return data;
	}
}