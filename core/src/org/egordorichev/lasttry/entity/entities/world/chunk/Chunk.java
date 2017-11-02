package org.egordorichev.lasttry.entity.entities.world.chunk;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;

/**
 * Handles blocks
 * This class is a messy one,
 * So I'm looking for a better solution here
 * I did not use the position component,
 * Because we use position x1000 times per tick here,
 * And plain data is faster
 *
 * @Col-E, this is a good place for optimization
 */
public class Chunk extends Entity {
	/**
	 * Chunk size in blocks
	 */
	public static short SIZE = 32;
	/**
	 * Block ID's
	 */
	private String[] blocks;
	/**
	 * Wall ID's
	 */
	private String[] walls;
	/**
	 * Data: 8 bits for blocks, 8 bit for walls, 16 bits general use
	 */
	private int[] data;

	public Chunk(short x, short y) {
		this.addComponent(PositionComponent.class);
		PositionComponent position = (PositionComponent) this.getComponent(PositionComponent.class);

		position.setX(x);
		position.setY(y);

		int size = SIZE * SIZE;

		this.blocks = new String[size];
		this.walls = new String[size];
		this.data = new int[size];
	}

	/**
	 * Returns block ID at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Block ID at given position
	 */
	public String getBlock(short x, short y) {
		if (this.isOut(x, y)) {
			return null;
		}

		return this.blocks[this.getIndex(x, y)];
	}

	/**
	 * Sets block ID at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New block ID
	 * @param x Block X
	 * @param y Block Y
	 */
	public void setBlock(String value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		this.blocks[this.getIndex(x, y)] = value;
	}

	/**
	 * Returns wall ID at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @return Wall ID at given position
	 */
	public String getWall(short x, short y) {
		if (this.isOut(x, y)) {
			return null;
		}

		return this.walls[this.getIndex(x, y)];
	}

	/**
	 * Sets wall ID at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New wall ID
	 * @param x Wall X
	 * @param y Wall Y
	 */
	public void setWall(String value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		this.walls[this.getIndex(x, y)] = value;
	}

	/**
	 * Returns data at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Data X
	 * @param y Data Y
	 * @return Data at given position
	 */
	public int getData(short x, short y) {
		if (this.isOut(x, y)) {
			return 0;
		}

		return this.data[this.getIndex(x, y)];
	}

	/**
	 * Sets data at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New data
	 * @param x Data X
	 * @param y Data Y
	 */
	public void setBlock(int value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		this.data[this.getIndex(x, y)] = value;
	}

	/**
	 * Returns array index for block with given coordinates
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Array index (non-safe!)
	 */
	private int getIndex(short x, short y) {
		return x + y * SIZE;
	}

	/**
	 * Returns true, if given position is outside of block array
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return True, if given position is outside of block array
	 */
	private boolean isOut(short x, short y) {
		return x < 0 || y < 0 || x > SIZE - 1 || y > SIZE -1;
	}
}