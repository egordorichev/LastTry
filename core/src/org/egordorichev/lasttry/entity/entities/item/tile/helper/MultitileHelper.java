package org.egordorichev.lasttry.entity.entities.item.tile.helper;

import org.egordorichev.lasttry.util.binary.BitHelper;
import org.egordorichev.lasttry.util.math.RandomGenerator;

/**
 * Handles multitile block data
 */
public class MultitileHelper extends TileHelper {
	/**
	 * @param x Tile X (starts from the body X)
	 * @param y Tile Y (starts from the body Y)
	 *
	 * @return New random tile data
	 */
	public int create(short x, short y) {
		int data = 0;

		data = this.setVariant(data, RandomGenerator.random(0, 2));
		data = this.setBlockHealth(data, 3);
		data = this.setWallHealth(data, 3);
		data = this.setX(data, x);
		data = this.setY(data, y);

		return data;
	}

	/**
	 * Sets tile part X
	 *
	 * @param data The data
	 * @param x The part X
	 * @return The data
	 */
	public int setX(int data, short x) {
		return BitHelper.putNumber(data, 7, 3, x);
	}

	/**
	 * Sets tile part Y
	 *
	 * @param data The data
	 * @param y The part Y
	 * @return The data
	 */
	public int setY(int data, short y) {
		return BitHelper.putNumber(data, 10, 3, y);
	}

	/**
	 * Returns tile part X
	 *
	 * @param data The data
	 * @return The tile part X
	 */
	public int getX(int data) {
		return BitHelper.getNumber(data, 7, 3);
	}

	/**
	 * Returns tile part Y
	 *
	 * @param data The data
	 * @return The tile part Y
	 */
	public int getY(int data) {
		return BitHelper.getNumber(data, 10, 3);
	}
}