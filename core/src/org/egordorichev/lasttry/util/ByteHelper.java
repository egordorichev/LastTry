package org.egordorichev.lasttry.util;

public class ByteHelper {
	public static boolean bitIsSet(byte data, byte bit) {
		return (data & (1 << bit)) != 0;
	}

	public static byte setBit(byte data, byte bit, boolean value) {
		if (value) {
			data = (byte) (data | (1 << bit));
		} else {
			data = (byte) (data & ~(1 << bit));

		}

		return data;
	}
}