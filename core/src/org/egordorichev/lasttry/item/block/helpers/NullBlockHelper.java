package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class NullBlockHelper extends BlockHelper {
	@Override
	public byte getHP(byte data) {
		return 0;
	}

	public byte getLiquidType(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 1);
	}

	public byte setLiquidType(byte data, byte liquidType) {
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(liquidType, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(liquidType, (byte) 1));

		return data;
	}

	public byte getLiquidLevel(byte data) {
		return ByteHelper.getSum(data, (byte) 2, (byte) 6);
	}

	public byte setLiquidLevel(byte data, byte level) {
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(level, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(level, (byte) 1));
		data = ByteHelper.setBit(data, (byte) 2, ByteHelper.bitIsSet(level, (byte) 2));
		data = ByteHelper.setBit(data, (byte) 3, ByteHelper.bitIsSet(level, (byte) 3));
		data = ByteHelper.setBit(data, (byte) 4, ByteHelper.bitIsSet(level, (byte) 4));

		return data;
	}

	public boolean liquidStartsFromBottom(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 7);
	}

	public byte setLiquidStartsFromBottom(byte data, boolean starts) {
		return ByteHelper.setBit(data, (byte) 7, starts);
	}
}