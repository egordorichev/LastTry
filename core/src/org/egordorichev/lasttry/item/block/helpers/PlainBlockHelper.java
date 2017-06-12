package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class PlainBlockHelper extends BlockHelper {
	public static boolean slopeIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 5);
	}

	public byte getVariant(byte data) {
		if (slopeIsPresent(data)) {
			return 0;
		}

		return ByteHelper.getSum(data, (byte) 2, (byte) 3);
	}

	public byte getSlope(byte data) {
		if (!slopeIsPresent(data)) {
			return 0;
		}

		return ByteHelper.getSum(data, (byte) 2, (byte) 3);
	}
}