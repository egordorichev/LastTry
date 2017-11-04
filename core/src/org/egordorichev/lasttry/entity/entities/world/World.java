package org.egordorichev.lasttry.entity.entities.world;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.item.tile.Wall;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.entity.entities.world.chunk.ChunkIO;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Handles chunks
 */
public class World extends Entity {
	public World() {
		this.addComponent(SizeComponent.class);

		SizeComponent size = this.getComponent(SizeComponent.class);

		size.width = 4;
		size.height = 4;

		this.addComponent(WorldChunksComponent.class);

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (Math.random() > 0.5) {
					this.setBlock("lt:dirt", (short) x, (short) y);
				}
			}
		}
	}

	/**
	 * Renders the world
	 */
	@Override
	public void render() {
		short xStart = (short) Math.floor(Globals.camera.getComponent(CameraComponent.class).camera.position.x / Block.SIZE);
		short yStart = (short) Math.floor(Globals.camera.getComponent(CameraComponent.class).camera.position.y / Block.SIZE);
		short width = (short) (Math.floor(Gdx.graphics.getWidth() / Block.SIZE) + 1);
		short height = (short) (Math.floor(Gdx.graphics.getHeight() / Block.SIZE) + 1);

		for (short x = xStart; x < xStart + width; x++) {
			for (short y = yStart; y < yStart + height; y++) {
				String blockId = this.getBlock(x, y);

				if (blockId != null) {
					Block block = (Block) Assets.items.get(blockId);
					block.render(x, y);
				} else {
					String wallId = this.getWall(x, y);

					if (wallId != null) {
						Wall wall = (Wall) Assets.items.get(wallId);
						wall.render(x, y);
					}
				}
			}
		}
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
			chunk.setWall(value, chunk.toRelativeX(x), chunk.toRelativeY(y));
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
		return x + y * this.getComponent(SizeComponent.class).width;
	}

	/**
	 * Returns chunk, that contains given block
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return The chunk
	 */
	private Chunk getChunkFor(int x, int y) {
		int index = this.getChunkIndex((int) Math.floor(x / Chunk.SIZE), (int) Math.floor(y / Chunk.SIZE));

		WorldChunksComponent chunks = this.getComponent(WorldChunksComponent.class);

		if (index < 0 || index > chunks.chunks.length - 1) {
			return null;
		}

		if (chunks.chunks[index] == null) {
			short cx = (short) Math.floor(x / Chunk.SIZE);
			short cy = (short) Math.floor(y / Chunk.SIZE);

			Log.debug("Loading chunk " + cx + ":" + cy);
			this.loadChunk(cx, cy);

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
	private boolean isOut(short x, short y) {
		SizeComponent size = this.getComponent(SizeComponent.class);
		return x < 0 || y < 0 || x > size.width * Chunk.SIZE - 1 || y > size.height * Chunk.SIZE -1;
	}

	private void loadChunk(short x, short y) {
		this.getComponent(WorldChunksComponent.class).chunks[this.getChunkIndex(x, y)] = ChunkIO.load(x, y);
	}
}