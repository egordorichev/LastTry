package org.egordorichev.lasttry.world;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.components.*;

import java.util.Random;

public class World {
	public static final int UPDATE_DELAY_SECONDS = 20; // Should be 20 by default
	/**
	 * World flags (hardmode, evil)
	 */
	public final WorldFlagsComponent flags;
	/**
	 * Chunks manager
	 */
	public final WorldChunksComponent chunks;
	/**
	 * Blocks manager
	 */
	public final WorldBlocksComponent blocks;
	/**
	 * Walls manager
	 */
	public final WorldWallsComponent walls;
	/**
	 * Light manager
	 */
	public final WorldLightingComponent light;
	/**
	 * Random instance, used for terrain generation. Since it's associated with
	 * the world seed, it will provide the same results every time if the seed
	 * is the same.
	 */
	public final Random random;
	/**
	 * World size.
	 */
	private final Size size;
	/**
	 * World name.
	 */
	private final String name;
	/**
	 * World seed, used for terrain generation.
	 */
	private final int seed;
	/**
	 * Boolean flag for if light needs to be recalculated near the player.
	 */
	private boolean lightDirty;

	public World(String name, Size size, int flags, int seed) {
		this.size = size;
		this.name = name;
		this.seed = seed;
		this.random = new Random(seed);
		this.chunks = new WorldChunksComponent(this);
		this.flags = new WorldFlagsComponent(this, flags);
		this.blocks = new WorldBlocksComponent(this);
		this.walls = new WorldWallsComponent(this);
		this.light = new WorldLightingComponent(this);

		Util.runDelayedThreadSeconds(new Callable() {
			@Override
			public void call() {
				update();
			}
		}, UPDATE_DELAY_SECONDS);
	}

	public void renderLights() {
		if (!LastTry.noLight) {
			this.light.render();
		}
	}

	public void render() {
		this.chunks.render();
	}

	public void updateLight(int dt) {
		if (!LastTry.noLight) {
			this.light.update(dt);
			lightDirty = false;
		}
	}

	/**
	 * Create the light map for the entire world.
	 */
	public void initLights() {
		if (!LastTry.noLight) {
			this.light.updateNearPlayer();
		}
	}

	/**
	 * Called when a block is broken.
	 *
	 * @param x
	 * @param y
	 */
	public void onBlockBreak(int x, int y) {
		// update lighting
		if (closeToPlayer(x, y)) {
			lightDirty = true;
		}
	}

	/**
	 * Called when a wall is broken.
	 *
	 * @param x
	 * @param y
	 */
	public void onWallBreak(int x, int y) {
		// update lighting
		if (closeToPlayer(x, y)) {
			lightDirty = true;
		}
	}

	/**
	 * Boolean checking of the given coordinates are near the player. Used
	 * primarily if checking if lighting should be re-calculated from a block
	 * modification event.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean closeToPlayer(int x, int y) {
		if (LastTry.noLight) {
			return false;
		}
		return Globals.getPlayer().physics.getGridPosition().dst(new Vector2(x, y)) < (Camera.getXInBlocks());
	}

	/**
	 * Given a hitbox, returns the distance in the direction of the given
	 * velocity for there to be a collision.
	 *
	 * @param hitbox
	 * @param velocityX
	 * @return
	 */
	public float distToHorizontalCollision(Rectangle hitbox, float velocityX) {
		Rectangle tmp = hitbox.copy();

			boolean collision = isColliding(tmp);
		boolean negativeMotion = velocityX < 0;
		int direction = negativeMotion ? -1 : 1;
		float change = direction * Block.SIZE;
		float distance = 0f;

		while (!collision) {
			tmp.x += change;
			distance += change;
			collision = isColliding(tmp);
		}
		return distance;
	}

	/**
	 * Given a hitbox, returns the distance in the direction of the given
	 * velocity for there to be a collision.
	 *
	 * @param hitbox
	 * @param velocityY
	 * @return
	 */
	public float distToVerticalCollision(Rectangle hitbox, float velocityY) {
		Rectangle tmp = hitbox.copy();
		boolean collision = isColliding(tmp);
		boolean negativeMotion = velocityY < 0;
		int direction = negativeMotion ? -1 : 1;
		float change = direction * Block.SIZE;
		float distance = 0f;
		while (!collision) {
			tmp.y += change;
			distance += change;
			collision = isColliding(tmp);
		}
		return distance;
	}

	/**
	 * Checks if the given bounds intersect with any blocks.
	 *
	 * @param bounds
	 * @return
	 */
	public boolean isColliding(Rectangle bounds) {
		Rectangle gridBounds = bounds.copy();
		gridBounds.x /= Block.SIZE;
		gridBounds.y /= Block.SIZE;
		gridBounds.width /= Block.SIZE;
		gridBounds.height /= Block.SIZE;

		for (int y = (int) gridBounds.y - 1; y < gridBounds.y + gridBounds.height + 1; y++) {
			for (int x = (int) gridBounds.x - 1; x < gridBounds.x + gridBounds.width + 1; x++) {
				if (!this.isInside(x, y)) {
					return true;
				}

				Block block = (Block) Item.fromID(this.blocks.getID(x, y));

				if (block == null || !block.isSolid()) {
					continue;
				}

				Rectangle blockRect = new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE);

				if (blockRect.intersects(bounds)) {
					return true;
				}
			}
		}

		return false;
	}

	public void update() {
		this.chunks.update();
	}

	public short getWidth() {
		return this.size.getWidth();
	}

	public short getHeight() {
		return this.size.getHeight();
	}

	public Size getSize() {
		return this.size;
	}

	public String getName() {
		return this.name;
	}

	public int getSeed() {
		return seed;
	}

	// GridPoints
	public boolean isInside(int x, int y) {
		return (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight());
	}

	public enum Size {
		SMALL("Small", 4096, 1024), // Small contains 64 chunks
		MEDIUM("Medium", 6144, 2048), // Medium contains 192 chunks
		LARGE("Large", 8192, 2304); // Large contains 288 chunks

		private String name;
		private short width;
		private short height;

		Size(String name, int width, int height) {
			this.name = name;
			this.width = (short) width;
			this.height = (short) height;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public short getWidth() {
			return this.width;
		}

		public short getHeight() {
			return this.height;
		}

		public short getMaxChunks() {
			return (short) ((width / Chunk.SIZE) * (height / Chunk.SIZE));
		}
	}
}