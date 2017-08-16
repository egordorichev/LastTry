package org.egordorichev.lasttry.item.wall.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class WallHelper {
	public static byte getHP(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 1);
	}

	public static byte setHP(byte data, byte hp) {
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(hp, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(hp, (byte) 1));

		return data;
	}

	public static byte getVariant(byte data) {
		return ByteHelper.getSum(data, (byte) 7, (byte) 7);
	}

	public static boolean redWireIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 2);
	}

	public static byte setRedWirePresent(byte data, boolean present) {
		return ByteHelper.setBit(data, (byte) 2, present);
	}

	public static boolean greenWireIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 3);
	}

	public static byte setGreenWirePresent(byte data, boolean present) {
		return ByteHelper.setBit(data, (byte) 3, present);
	}

	public static boolean blueWireIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 4);
	}

	public static byte setBlueWirePresent(byte data, boolean present) {
		return ByteHelper.setBit(data, (byte) 4, present);
	}

	public static boolean yellowWireIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 5);
	}

	public static byte setYellowWirePresent(byte data, boolean present) {
		return ByteHelper.setBit(data, (byte) 5, present);
	}

	public static boolean activatorIsPresent(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 6);
	}

	public static byte setActivatorPresent(byte data, boolean present) {
		return ByteHelper.setBit(data, (byte) 6, present);
	}
}