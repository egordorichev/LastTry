package org.egordorichev.lasttry.util.binary;

/**
 * Helps with creating binary numbers
 */
public class BinaryPacker {
	/**
	 * Packs a binary number
	 *
	 * @param bits Bits
	 * @return Packed number
	 */
	public static int pack(boolean ... bits) {
		int number = 0;
		int power = 1;

		for (boolean bit : bits) {
			if (bit) {
				number += power;
			}

			power += power;
		}

		return number;
	}
}