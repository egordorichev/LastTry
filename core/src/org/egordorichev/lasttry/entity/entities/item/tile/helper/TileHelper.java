package org.egordorichev.lasttry.entity.entities.item.tile.helper;

import org.egordorichev.lasttry.util.binary.BitHelper;
import org.egordorichev.lasttry.util.random.RandomGenerator;

/**
 * Helps with tile data
 */
public class TileHelper {
	/**
	 * Variant position in the data
	 */
	public static int VARIANT_POSITION = 0;

	/**
	 * @return New random tile data
	 */
	public static int create() {
		int data = 0;

		data = setVariant(data, RandomGenerator.random(0, 2));

		return data;
	}

	/**
	 * Sets variant in the data
	 *
	 * @param data The data
	 * @param variant New variant
	 * @return The data
	 */
	public static int setVariant(int data, int variant) {
		return BitHelper.putNumber(data, VARIANT_POSITION, 2, variant);
	}

	/**
	 * Returns variant from the data
	 *
	 * @param data The data
	 * @return The variant
	 */
	public static int getVariant(int data) {
		return BitHelper.getNumber(data, VARIANT_POSITION, 2);
	}
}