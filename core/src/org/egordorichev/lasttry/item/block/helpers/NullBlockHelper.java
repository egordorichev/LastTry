package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class NullBlockHelper extends BlockHelper {
    @Override public byte getHP(byte data) {
		return 0;
	}

	public byte getLiquidType(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 1);
	}

	public byte setLiquidType(byte data, byte liquidType) {
		// Liquid type determined by hp.
		return setHP(data,liquidType);
	}

	public byte getLiquidLevel(byte data) {
		return ByteHelper.getSum(data, (byte) 2, (byte) 6);
	}

	public byte setLiquidLevel(byte data, byte level) {
		for (int i = 0; i <= 4; i++) {
			data = ByteHelper.setBit(data, (byte) i, ByteHelper.bitIsSet(level, (byte) i));
		}
		return data;
	}

	public boolean liquidStartsFromBottom(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 7);
	}

	public byte setLiquidStartsFromBottom(byte data, boolean starts) {
		return ByteHelper.setBit(data, (byte) 7, starts);
	}
}