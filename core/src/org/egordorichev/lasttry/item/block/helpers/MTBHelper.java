package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class MTBHelper extends BlockHelper {
	@Override
	public byte getHP(byte data) {
		return 1;
	}

	public byte getX(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 3);
	}

	public byte getY(byte data) {
		return ByteHelper.getSum(data, (byte) 4, (byte) 6);
	}

	public byte setX(byte data, byte x) {
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(x, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(x, (byte) 1));
		data = ByteHelper.setBit(data, (byte) 2, ByteHelper.bitIsSet(x, (byte) 2));
		data = ByteHelper.setBit(data, (byte) 3, ByteHelper.bitIsSet(x, (byte) 3));

		return data;
	}

	public byte setY(byte data, byte y) {
		data = ByteHelper.setBit(data, (byte) 4, ByteHelper.bitIsSet(y, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 5, ByteHelper.bitIsSet(y, (byte) 1));
		data = ByteHelper.setBit(data, (byte) 6, ByteHelper.bitIsSet(y, (byte) 2));

		return data;
	}
}