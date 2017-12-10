package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.entity.entities.world.chunk.ChunkIO;

/**
 * Handles chunks
 */
public class ChunkManager extends Entity {
	public ChunkManager() {
		super(SizeComponent.class);

		SizeComponent size = this.getComponent(SizeComponent.class);

		size.width = 4;
		size.height = 4;

		this.addComponent(ChunksComponent.class);
	}

	/**
	 * Returns block ID at given position in world
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Block ID at given position
	 */
	public String getBlock(short x, short y) {
		if (this.isOut(x, y)) {
			return null;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return null;
		} else {
			return chunk.getBlock(chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Sets block ID at given position in world
	 *
	 * @param value New block ID
	 * @param x Block X
	 * @param y Block Y
	 */
	public void setBlock(String value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk != null) {
			Engine.sendMessage("set_block_" + x + "_" + y);
			chunk.setBlock(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Returns wall ID at given position in world
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @return Wall ID at given position
	 */
	public String getWall(short x, short y) {
		if (this.isOut(x, y)) {
			return null;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return null;
		} else {
			return chunk.getWall(chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Sets wall ID at given position in world
	 *
	 * @param value New wall ID
	 * @param x Wall X
	 * @param y Wall Y
	 */
	public void setWall(String value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk != null) {
			Engine.sendMessage("set_wall_" + x + "_" + y);
			chunk.setWall(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Returns light at given position in world
	 *
	 * @param x Light X
	 * @param y Light Y
	 * @return Light at given position
	 */
	public float getLight(short x, short y) {
		if (this.isOut(x, y)) {
			return 1.0f;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return 1.0f;
		} else {
			return chunk.getLight(chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Sets light ID at given position in world
	 *
	 * @param value New light value
	 * @param x Light X
	 * @param y Light Y
	 */
	public void setLight(float value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk != null) {
			chunk.setLight(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Returns data at given position in world
	 *
	 * @param x Data X
	 * @param y Data Y
	 * @return Data at given position
	 */
	public short getData(short x, short y) {
		if (this.isOut(x, y)) {
			return 0;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return 0;
		} else {
			return chunk.getData(chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}

	/**
	 * Sets data at given position in world
	 *
	 * @param value New data
	 * @param x Data X
	 * @param y Data Y
	 */
	public void setData(short value, short x, short y) {
		if (this.isOut(x, y)) {
			return;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk != null) {
			chunk.setData(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
		}
	}


	/**
	 * Returns chunk array index, containing given block
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Chunk array index, containing given block (unsafe!)
	 */
	private int getChunkIndex(int x, int y) {
		return (int) (x + y * this.getComponent(SizeComponent.class).width);
	}

	/**
	 * Returns chunk, that contains given block
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return The chunk
	 */
	public Chunk getChunkFor(int x, int y) {
		int index = this.getChunkIndex((int) Math.floor(x / Chunk.SIZE), (int) Math.floor(y / Chunk.SIZE));

		ChunksComponent chunks = this.getComponent(ChunksComponent.class);

		if (index < 0 || index > chunks.chunks.length - 1) {
			return null;
		}

		if (chunks.chunks[index] == null) {
			short cx = (short) Math.floor(x / Chunk.SIZE);
			short cy = (short) Math.floor(y / Chunk.SIZE);

			this.loadChunk(cx, cy);
			// chunks.chunks[index].calculateLighting();

			Engine.sendMessage("load_chunk_" + cx + "_" + cy);

			return chunks.chunks[index];
		}

		return chunks.chunks[index];
	}

	/**
	 * Returns true, if given position is outside of block array
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return True, if given position is outside of block array
	 */
	protected boolean isOut(short x, short y) {
		SizeComponent size = this.getComponent(SizeComponent.class);
		return x < 0 || y < 0 || x > size.width * Chunk.SIZE - 1 || y > size.height * Chunk.SIZE -1;
	}

	protected void loadChunk(short x, short y) {
		String id = "data/worlds/" + this.getComponent(IdComponent.class).id.replace(':', '/');

		ChunksComponent chunks = this.getComponent(ChunksComponent.class);
		Chunk chunk = ChunkIO.load(id, x, y);

		chunks.loaded.add(chunk);
		chunks.chunks[this.getChunkIndex(x, y)] = chunk;
	}
}