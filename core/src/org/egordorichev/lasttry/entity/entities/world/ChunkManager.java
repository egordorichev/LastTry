package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessage;
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
		
		addComponent(ChunksComponent.class);
	}

	/**
	 * Returns block ID at given position in world
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Block ID at given position
	 */
	public String getBlock(int x, int y) {
		if (this.isOut(x, y)) {
			return null;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return null;
		}
		return chunk.getBlock(chunk.toRelativeX(x), chunk.toRelativeY(y));
	}

	/**
	 * Sets block ID at given position in world
	 *
	 * @param value New block ID
	 * @param x Block X
	 * @param y Block Y
	 */
	public void setBlock(String value, int x, int y) {
		if (this.isOut(x, y)) {
			return;
		}
		
		Chunk chunk = this.getChunkFor(x, y);
		
		if (chunk != null) {
			chunk.setBlock(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
			Engine.sendMessage(SystemMessage.Type.TILE_UPDATE.create()
					.with("action", "set")
					.with("value", value)
					.with("x", x)
					.with("y", y));
		}
	}

	/**
	 * Returns wall ID at given position in world
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @return Wall ID at given position
	 */
	public String getWall(int x, int y) {
		if (this.isOut(x, y)) {
			return null;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return null;
		}
		return chunk.getWall(chunk.toRelativeX(x), chunk.toRelativeY(y));
	}

	/**
	 * Sets wall ID at given position in world
	 *
	 * @param value New wall ID
	 * @param x Wall X
	 * @param y Wall Y
	 */
	public void setWall(String value, int x, int y) {
		if (this.isOut(x, y)) {
			return;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk != null) {
			chunk.setWall(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
			Engine.sendMessage(SystemMessage.Type.WALL_UPDATE.create()
					.with("action", "set")
					.with("value", value)
					.with("x", x)
					.with("y", y));
		}
	}

	/**
	 * Returns light at given position in world
	 *
	 * @param x Light X
	 * @param y Light Y
	 * @return Light at given position
	 */
	public float getLight(int x, int y) {
		if (isOut(x, y)) {
			return 0.0f;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return 0.0f;
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
	public void setLight(float value, int x, int y) {
		if (isOut(x, y)) {
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
	public int getData(int x, int y) {
		if (this.isOut(x, y)) {
			return 0;
		}

		Chunk chunk = this.getChunkFor(x, y);

		if (chunk == null) {
			return 0;
		}
		return chunk.getData(chunk.toRelativeX(x), chunk.toRelativeY(y));
	}

	/**
	 * Sets data at given position in world
	 *
	 * @param value New data
	 * @param x Data X
	 * @param y Data Y
	 */
	public void setData(int value, int x, int y) {
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
		return x + y * (int)this.getComponent(SizeComponent.class).width;
	}

	/**
	 * Returns chunk, that contains given block
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return The chunk
	 */
	public Chunk getChunkFor(int x, int y) {
		return getChunk(x / Chunk.SIZE, y / Chunk.SIZE);
	}
	
	public Chunk getChunk(int x, int y){
		SizeComponent size = getComponent(SizeComponent.class);
		if(x < 0 || y < 0 || x >= size.width || y >= size.height)return null;
		
		int index = getChunkIndex(x, y);
		ChunksComponent chunks = this.getComponent(ChunksComponent.class);
		
		if (chunks.chunks[index] == null) {
			loadChunk(x, y);
			Chunk chunk = chunks.chunks[index];
			Engine.sendMessage(SystemMessage.Type.LOAD_CHUNK.create()
					.with("chunk", chunk)
					.with("x", x)
					.with("y", y));
			return chunk;
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
	protected boolean isOut(int x, int y) {
		SizeComponent size = this.getComponent(SizeComponent.class);
		return x < 0 || y < 0 || x > size.width * Chunk.SIZE - 1 || y > size.height * Chunk.SIZE -1;
	}

	protected void loadChunk(int x, int y) {
		String id = "data/worlds/" + this.getComponent(IdComponent.class).id.replace(':', '/');

		ChunksComponent chunks = this.getComponent(ChunksComponent.class);
		Chunk chunk = ChunkIO.load(id, x, y);

		chunks.loaded.add(chunk);
		chunks.chunks[this.getChunkIndex(x, y)] = chunk;

		Engine.addEntity(chunk);
	}
}