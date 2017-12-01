package org.egordorichev.lasttry.entity.entities.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.util.log.Log;

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
	 * Lights
	 */
	private float[] light;
	/**
	 * Data: 8 bits for blocks, 8 bit for walls, 16 bits general use
	 */
	private short[] data;

	public Chunk(short x, short y) {
		super(PositionComponent.class);

		PositionComponent position = this.getComponent(PositionComponent.class);

		position.x = x;
		position.y = y;

		int size = SIZE * SIZE;

		this.blocks = new String[size];
		this.walls = new String[size];
		this.light = new float[size];
		this.data = new short[size];

		for (int i = 0; i < size; i++) {
			this.light[i] = 1.0f;
		}
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
	 * Returns light ID at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Light X
	 * @param y Light Y
	 * @return Light at given position
	 */
	public float getLight(short x, short y) {
		if (this.isOut(x, y)) {
			return 1.0f;
		}

		return this.light[this.getIndex(x, y)];
	}

	/**
	 * Sets light ID at given position in chunk
	 * Coordinates MUST be relative
	 *
	 * @param value New light value
	 * @param x Light X
	 * @param y Light Y
	 */
	public void setLight(float value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		this.light[this.getIndex(x, y)] = value;
	}

	/**
	 * Returns data at given position in chunk
	 * Coordinates MUST be relative, or null will be returned
	 *
	 * @param x Data X
	 * @param y Data Y
	 * @return Data at given position
	 */
	public short getData(short x, short y) {
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
	public void setData(short value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		this.data[this.getIndex(x, y)] = value;
	}

	/**
	 * Relates X to chunk
	 *
	 * @param x X
	 * @return Related X
	 */
	public short toRelativeX(short x) {
		return (short) (x - this.getX() * SIZE);
	}

	/**
	 * Relates Y to chunk
	 *
	 * @param y Y
	 * @return Related Y
	 */
	public short toRelativeY(short y) {
		return (short) (y - this.getY() * SIZE);
	}


	/**
	 * @return Chunk X
	 */
	public short getX() {
		return (short) this.getComponent(PositionComponent.class).x;
	}

	/**
	 * @return Chunk Y
	 */
	public short getY() {
		return (short) this.getComponent(PositionComponent.class).y;
	}

	/**
	 * Calculates light in a chunk
	 */
	public void calculateLighting() {
		Log.debug("Calculating lighting...");

		for (short x = 0; x < SIZE; x++) {
			for (short y = 0; y < SIZE; y++) {
				this.calculateLightingForBlock(x, y);
			}
		}
	}

	/**
	 * Calculates light for block
	 * Coordinates MUST be relative
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void calculateLightingForBlock(short x, short y) {
		short sampleRadius = 6;
		float max = Block.MAX_LIGHT;
		float average = 0;

		float divisor = (float) (Math.pow(sampleRadius * 2, 2));

		for (short i = (short) -sampleRadius; i < sampleRadius; i++) {
			short high = World.instance.getHighest((short) (x + i));

			for (short k = (short) -sampleRadius; k < sampleRadius; k++) {
				String id = World.instance.getBlock((short) (x + i), (short) (y + k));

				float strength = 8.0f;
				boolean hasBlock = (id != null);
				boolean canSeeSky = y >= high;

				if (hasBlock) {
					Block block = (Block) Assets.items.get(id);

					if (block.isEmitter()) {
						float dist = new Vector2(-i, -k).len();

						if (dist <= sampleRadius - 0.125f) {
							strength = (float) Math.pow(10000, 1.75f / dist);
						}
					}
				} else {
					if (canSeeSky) {
						strength += strength * 1f;
					} else {
						strength += -strength * 5f;
					}
				}

				float value = World.instance.getLight((short) (x + i), (short) (y + k));
				average += (value * strength / divisor);
			}
		}

		// Log.info(average / max + " : " + average);

		float output = Math.min(Math.max(average / ((float) max), 0), 1);
		this.setLight(output, x, y);
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