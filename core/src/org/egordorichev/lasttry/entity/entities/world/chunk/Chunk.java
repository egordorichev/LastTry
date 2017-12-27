package org.egordorichev.lasttry.entity.entities.world.chunk;

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
	
	private static int iterations = 7;
	private static float lightFade = 1f / iterations;
	private static float sqrt2 = 1.41421356237f;
	
	/**
	 * Chunk size in blocks
	 */
	//THEORETICALLY decreasing the size of a chunk to, let's say, 16, would increase the speed of the lighting
	//algorithm since lighting is calculated IN CHUNKS. Although, I have not tested this yet.
	public static int SIZE = 32;
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
	private int[] data;

	public Chunk(int x, int y) {
		super(PositionComponent.class);

		PositionComponent position = this.getComponent(PositionComponent.class);

		position.x = x;
		position.y = y;

		int size = SIZE * SIZE;

		this.blocks = new String[size];
		this.walls = new String[size];
		this.light = new float[size];
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
	public String getBlock(int x, int y) {
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
	public void setBlock(String value, int x, int y) {
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
	public String getWall(int x, int y) {
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
	public void setWall(String value, int x, int y) {
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
	public float getLight(int x, int y) {
		if (this.isOut(x, y)) {
			return 0.0f;
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
	public void setLight(float value, int x, int y) {
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
	public int getData(int x, int y) {
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
	public void setData(int value, int x, int y) {
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
	public int toRelativeX(int x) {
		return x - this.getX() * SIZE;
	}

	/**
	 * Relates Y to chunk
	 *
	 * @param y Y
	 * @return Related Y
	 */
	public int toRelativeY(int y) {
		return y - this.getY() * SIZE;
	}


	/**
	 * @return Chunk X
	 */
	public int getX() {
		return (int) this.getComponent(PositionComponent.class).x;
	}

	/**
	 * @return Chunk Y
	 */
	public int getY() {
		return (int) this.getComponent(PositionComponent.class).y;
	}

	/**
	 * Calculates light in a chunk
	 */
	public void calculateLighting(float globalLight) {
		PositionComponent pos = this.getComponent(PositionComponent.class);
		Log.debug("Calculating lighting for chunk (" + (int)pos.x + ", " + (int)pos.y + ")");
		
		final int chunkX = (int)(pos.x * SIZE);
		final int chunkY = (int)(pos.y * SIZE);
		
		//Calculate global light.
		for(int x = 0; x < SIZE; x++){
			int top = World.instance.getHighest((short)(chunkX + x)) - chunkY;
			
			for(int y = SIZE - 1; y >= 0; y--){
				if(y >= top)setLight(globalLight, x, y);
				else setLight(0f, x, y);
			}
		}
		
		//Grab all emitters in the area.
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				String id = World.instance.getBlock((short)(x + chunkX), (short)(y + chunkY));
				if(id != null){
					Block block = Assets.items.getUnsafe(id);
					if(block.isEmitter()){
						//For when blocks get brightness
						//setLight(block.getBrightness(), x, y);
						setLight(1.0f, x, y);
					}
				}
			}
		}
		
		for(int i = 0; i < iterations; i++){
			for(int x = chunkX; x < chunkX + SIZE; x++){
				for(int y = chunkY; y < chunkY + SIZE; y++){
					float light = getLight(x - chunkX, y - chunkY);
					float lightFade = Chunk.lightFade;
					light = Math.max(light, lightCandidate(x - 1, y, lightFade));//Left
					light = Math.max(light, lightCandidate(x + 1, y, lightFade));//Right
					light = Math.max(light, lightCandidate(x, y - 1, lightFade));//Bottom
					light = Math.max(light, lightCandidate(x, y + 1, lightFade));//Top
					
					lightFade *= sqrt2;
					light = Math.max(light, lightCandidate(x - 1, y + 1, lightFade));//Top-Left
					light = Math.max(light, lightCandidate(x + 1, y + 1, lightFade));//Top-Right
					light = Math.max(light, lightCandidate(x - 1, y - 1, lightFade));//Bottom-Left
					light = Math.max(light, lightCandidate(x + 1, y - 1, lightFade));//Bottom-Right
					setLight(light, x - chunkX, y - chunkY);
				}
			}
		}
	}
	
	private float lightCandidate(int x, int y, float lightFade){
		if(World.instance.getBlock(x, y) != null)lightFade *= 1.5;
		return World.instance.getLight(x, y) - lightFade;
	}

	/**
	 * Returns array index for block with given coordinates
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Array index (non-safe!)
	 */
	private int getIndex(int x, int y) {
		return x + y * SIZE;
	}

	/**
	 * Returns true, if given position is outside of block array
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return True, if given position is outside of block array
	 */
	private boolean isOut(int x, int y) {
		return x < 0 || y < 0 || x > SIZE - 1 || y > SIZE -1;
	}
}