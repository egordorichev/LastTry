package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class BlockHelper {
	public byte getHP(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 1);
	}

	public boolean isActivated(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 70);
	}
}
