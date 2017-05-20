package org.egordorichev.lasttry.util;

public class ByteHelper {
	public static boolean bitIsSet(byte data, byte bit) {
		return getBitValue(data, bit) != 0;
	}

	public static int getBitValue(byte data, byte bit) {
		return (data & (1 << bit));
	}

	public static byte setBit(byte data, byte bit, boolean value) {
		if (value) {
			data = (byte) (data | (1 << bit));
		} else {
			data = (byte) (data & ~(1 << bit));
		}

		return data;
	}

	public static byte create(boolean b1, boolean b2, boolean b3, boolean b4,
		boolean b5, boolean b6, boolean b7, boolean b8) {

		byte data = 0;

		data = setBit(data, (byte) 0, b1);
		data = setBit(data, (byte) 1, b2);
		data = setBit(data, (byte) 2, b3);
		data = setBit(data, (byte) 3, b4);
		data = setBit(data, (byte) 4, b5);
		data = setBit(data, (byte) 5, b6);
		data = setBit(data, (byte) 6, b7);
		data = setBit(data, (byte) 7, b8);

		return data;
	}
}