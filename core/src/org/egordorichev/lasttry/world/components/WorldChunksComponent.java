package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.item.block.helpers.NullBlockHelper;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.chunk.ChunkIO;
import java.awt.Rectangle;
import java.util.*;

/**
 * Methods that alter any of the collections that contain Chunks, are synchronized.
 * The ChunkGc thread will alter the Chunk collections (separate from the main thread), when removing unused Chunks.
 * Therefore chunk collection altering methods are made synchronized.
 */
public class WorldChunksComponent extends WorldComponent {
	private Chunk[] chunks;
	private ArrayList<Chunk> loadedChunks = new ArrayList<>();
	private int size;

	public WorldChunksComponent(World world) {
		super(world);

		this.size = world.getWidth() * world.getHeight();
		this.chunks = new Chunk[this.size];

		Util.runDelayedThreadSeconds(new Callable() {
			@Override
			public void call() {
				updateLogic();
			}
		}, World.UPDATE_DELAY_SECONDS);
	}

	public void update() {

	}

	public synchronized void updateLogic() {
		for (int i = 0; i < this.loadedChunks.size(); i++) {
			this.loadedChunks.get(i).update();
		}
	}

	public void renderLiquids() {
		Rectangle blocksRect = Camera.getBlocksOnScreen();

		for (int y = blocksRect.y; y < blocksRect.y + blocksRect.height; y++) {
			for (int x = blocksRect.x; x < blocksRect.x + blocksRect.width; x++) {
				Block block = (Block) Item.fromID(this.world.blocks.getID(x, y));

				if (block == null) {
					byte hp = this.world.blocks.getHP(x, y);
					byte liquidLevel = BlockHelper.empty.getLiquidLevel(hp);

					if (liquidLevel > 0) {
						float light = Globals.getWorld().light.get(x, y);
						Graphics.batch.setColor(light, light, light, 1f);
						Graphics.batch.draw(Graphics.waterTexture[0][liquidLevel - 1], x * Block.SIZE, y * Block.SIZE);
						Block bottom = Globals.getWorld().blocks.get(x, y - 1);

						if (bottom == null) {
							byte bottomHp = Globals.getWorld().blocks.getHP(x, y - 1);
							byte bottomLiquidLevel = BlockHelper.empty.getLiquidLevel(bottomHp);

							if (bottomLiquidLevel < 15) { // FIXME: fit 16 somewhere?
								liquidLevel -= 1;
								hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
								Globals.getWorld().blocks.setHP(hp, x, y);
								bottomLiquidLevel += 1;
								bottomHp = BlockHelper.empty.setLiquidLevel(bottomHp, bottomLiquidLevel);
								Globals.getWorld().blocks.setHP(bottomHp, x, y - 1);
								continue;
							}
						}

						Block left = Globals.getWorld().blocks.get(x - 1, y);

						byte leftHp = Globals.getWorld().blocks.getHP(x - 1, y);
						byte leftLiquidLevel = BlockHelper.empty.getLiquidLevel(leftHp);
						byte rightHp = Globals.getWorld().blocks.getHP(x + 1, y);
						byte rightLiquidLevel = BlockHelper.empty.getLiquidLevel(rightHp);

						Block right = Globals.getWorld().blocks.get(x + 1, y);
						boolean toLeft = left == null && leftLiquidLevel < liquidLevel;
						boolean toRight = right == null && rightLiquidLevel < liquidLevel;

						if (toLeft && toRight) {
							toLeft = Math.random() > 0.5;
							toRight = !toLeft;
						}

						if (left == null && toLeft) {
							if (leftLiquidLevel < 15) { // FIXME: fit 16 somewhere?
								liquidLevel -= 1;
								hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
								Globals.getWorld().blocks.setHP(hp, x, y);
								leftLiquidLevel += 1;
								leftHp = BlockHelper.empty.setLiquidLevel(leftHp, leftLiquidLevel);
								Globals.getWorld().blocks.setHP(leftHp, x - 1, y);
							}
						}

						if (right == null && toRight) {
							if (rightLiquidLevel < 15) { // FIXME: fit 16 somewhere?
								liquidLevel -= 1;

								if (liquidLevel < 0) {
									liquidLevel = 0;
								}

								hp = BlockHelper.empty.setLiquidLevel(hp, liquidLevel);
								Globals.getWorld().blocks.setHP(hp, x, y);
								rightLiquidLevel += 1;
								rightHp = BlockHelper.empty.setLiquidLevel(rightHp, rightLiquidLevel);
								Globals.getWorld().blocks.setHP(rightHp, x + 1, y);
							}
						}
					}
				}
			}
		}
		
		Graphics.batch.setColor(1f, 1f, 1f, 1f);
	}

	public void render() {
		Rectangle blocksRect = Camera.getBlocksOnScreen();

		for (int y = blocksRect.y; y < blocksRect.y + blocksRect.height; y++) {
			for (int x = blocksRect.x; x < blocksRect.x + blocksRect.width; x++) {
				Block block = (Block) Item.fromID(this.world.blocks.getID(x, y));

				if (block != null) {
					block.updateBlockStyle(x, y);

					byte binary = block.calculateBinary(x, y);

					if (binary != 15) {
						Wall wall = (Wall) Item.fromID(this.world.walls.getID(x, y));

						if (wall != null) {
							wall.renderWall(x, y);
						}
					}

				 	block.renderBlock(x, y, binary);
				} else {
					Wall wall = (Wall) Item.fromID(this.world.walls.getID(x, y));

					if (wall != null) {
						wall.renderWall(x, y);
					}
				}
			}
		}
	}

	public synchronized void load(int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return;
		}

		this.set(ChunkIO.load(x, y), x, y);
		this.loadedChunks.add(this.chunks[index]);
	}

	public synchronized void set(Chunk chunk, int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return;
		}

		this.chunks[index] = chunk;
	}

	public synchronized Chunk get(int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return null;
		}

		return this.chunks[index];
	}

	public synchronized Chunk getFor(int x, int y) {
		x /= Chunk.SIZE;
		y /= Chunk.SIZE;

		Chunk chunk = this.get(x, y);

		if (chunk == null) {
			this.load(x, y);
			return this.get(x, y);
		}

		return chunk;
	}

	private boolean isInside(int index) {
		if (index >= this.size || index < 0) {
			return false;
		}

		return true;
	}

	// TODO: may be better to pass entire arraylist, rather than one by one. Therefore no need to lose and regain monitor continuously?
	public synchronized void removeChunk(UUID uniqueIdOfChunkToBeRemoved) {
		for (Chunk chunk : this.loadedChunks) {
			if (chunk.getUniqueChunkId().equals(uniqueIdOfChunkToBeRemoved)) {
				ChunkIO.save(chunk.getGridX(), chunk.getGridY());
				this.loadedChunks.remove(chunk);
				break;
			}
		}
	}

	@SuppressWarnings("unused")
	private synchronized void removeChunkInChunksArray(final int index, Optional<Chunk> optionalChunk, UUID uniqueIdOfChunkToBeRemoved) {
		optionalChunk.ifPresent(chunk -> {
			if(chunk.getUniqueChunkId().equals(uniqueIdOfChunkToBeRemoved)){
				chunks[index] = null;
			}
		});
	}

	private int getIndex(int x, int y) {
		return x + y * this.world.getWidth() / Chunk.SIZE;
	}

	public synchronized List<Chunk> getImmutableLoadedChunks() {
		return Collections.unmodifiableList(loadedChunks);
	}

	public void save() {
		for (int y = 0; y < Globals.getWorld().getHeight() / Chunk.SIZE; y++) {
			for (int x = 0; x < Globals.getWorld().getWidth() / Chunk.SIZE; x++) {
				if (this.get(x, y) != null) {
					ChunkIO.save(x, y);
				}
			}
		}
	}
}