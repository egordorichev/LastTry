package org.egordorichev.lasttry.entity.entities.world.chunk;

/**
 * Just returns null from every get method
 */
public class EmptyChunk extends Chunk {
	public EmptyChunk(short x, short y) {
		super(x, y);
	}

	/**
	 * Returns block ID at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Block ID at given position
	 */
	@Override
	public String getBlock(short x, short y) {
		return null;
	}

	/**
	 * Sets block ID at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New block ID
	 * @param x Block X
	 * @param y Block Y
	 */
	@Override
	public void setBlock(String value, short x, short y) {

	}

	/**
	 * Returns wall ID at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @return Wall ID at given position
	 */
	@Override
	public String getWall(short x, short y) {
		return null;
	}

	/**
	 * Sets wall ID at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New wall ID
	 * @param x Wall X
	 * @param y Wall Y
	 */
	@Override
	public void setWall(String value, short x, short y) {

	}

	/**
	 * Returns data at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Data X
	 * @param y Data Y
	 * @return Data at given position
	 */
	@Override
	public short getData(short x, short y) {
		return 0;
	}

	/**
	 * Sets data at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New data
	 * @param x Data X
	 * @param y Data Y
	 */
	@Override
	public void setData(short value, short x, short y) {

	}
}