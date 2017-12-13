package org.egordorichev.lasttry.util.binary;

/**
 * Helps with bit manipulations
 */
public class BitHelper {
	/**
	 * Sets bit in a number
	 *
	 * @param data The number
	 * @param bit Bit position
	 * @param value New bit value
	 * @return The number
	 */
	public static int setBit(int data, int bit, int value) {
		if (value == 1) {
			data = data | (1 << bit);
		} else {
			data = data & ~(1 << bit);
		}

		return data;
	}

	/**
	 * Extracts a bit from a number
	 *
	 * @param data The number
	 * @param bit Bit pos
	 * @return The number
	 */
	public static int getBit(int data, int bit) {
		return (data >> bit) & 1;
	}

	/**
	 * Puts a number into the data
	 *
	 * @param data The data
	 * @param bit Bit position
	 * @param size How many bits to write
	 * @param number The number to write
	 * @return The data
	 */
	public static int putNumber(int data, int bit, int size, int number) {
		for (int i = 0; i < size; i++) {
			data = setBit(data, bit + i, getBit(number, i));
		}

		return data;
	}

	/**
	 * Reads a number from the data
	 *
	 * @param data The data
	 * @param bit The position in the data
	 * @param size The number size
	 * @return The number
	 */
	public static int getNumber(int data, int bit, int size) {
		int num = 0;

		for (int i = 0; i < size; i++) {
			num += getBit(data, bit + i) << i;
		}

		return num;
	}
}