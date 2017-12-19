package org.egordorichev.lasttry.entity.entities.item.tile.helper;

import org.egordorichev.lasttry.util.binary.BitHelper;
import org.egordorichev.lasttry.util.math.RandomGenerator;

/**
 * Helps with tile data
 */
public class TileHelper {
	/**
	 * The default tile helper
	 */
	public static TileHelper main = new TileHelper();

	/**
	 * @return New random tile data
	 */
	public int create() {
		int data = 0;

		data = setVariant(data, RandomGenerator.random(0, 2));
		data = setBlockHealth(data, 3);
		data = setWallHealth(data, 3);

		return data;
	}

	/**
	 * Sets variant in the data
	 *
	 * @param data The data
	 * @param variant New variant
	 * @return The data
	 */
	public int setVariant(int data, int variant) {
		return BitHelper.putNumber(data, 0, 2, variant);
	}

	/**
	 * Returns variant from the data
	 *
	 * @param data The data
	 * @return The variant
	 */
	public int getVariant(int data) {
		return BitHelper.getNumber(data, 0, 2);
	}

	/**
	 * Sets block health in the data
	 *
	 * @param data The data
	 * @param health New health
	 * @return The data
	 */
	public int setBlockHealth(int data, int health) {
		return BitHelper.putNumber(data, 2, 2, health);
	}

	/**
	 * Returns block health from the data
	 *
	 * @param data The data
	 * @return The health
	 */
	public int getBlockHealth(int data) {
		return BitHelper.getNumber(data, 2, 2);
	}

	/**
	 * Sets wall health in the data
	 *
	 * @param data The data
	 * @param health New health
	 * @return The data
	 */
	public int setWallHealth(int data, int health) {
		return BitHelper.putNumber(data, 4, 2, health);
	}

	/**
	 * Returns wall health from the data
	 *
	 * @param data The data
	 * @return The health
	 */
	public int getWallHealth(int data) {
		return BitHelper.getNumber(data, 4, 2);
	}
}