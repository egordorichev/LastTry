package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.ByteHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public class Chunk {
	public static final int SIZE = 256;
	public static final int TOTAL_SIZE = 256 * 256;

	private ChunkData data;
	private Vector2 position;
	private LocalDateTime lastAccessedTime;
	private UUID uniqueChunkId;
	private boolean unloadable = true;


	private final ItemManager itemManager;

	public Chunk(ChunkData data, Vector2 position) {
		itemManager = CoreRegistry.get(ItemManager.class);

		this.updateLastAccessedTime();
		this.data = data;
		this.position = position;
		this.uniqueChunkId = UUID.randomUUID();
	}

	public void update() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Block block = (Block) itemManager.getItem(this.data.blocks[x + y * SIZE]);

				if (block != null) {
					block.updateBlock(x, y);
				}
			}
		}

		this.updateLastAccessedTime();
	}

	public String getBlock(int globalX, int globalY) {
		return this.getBlockInside(globalX - this.getX(), globalY - this.getY());
	}

	public String getBlockInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return null;
		}

		return this.data.blocks[x + y * SIZE];
	}

	public void setBlock(String id, int globalX, int globalY) {
		this.setBlockInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setBlockInside(String id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		int n = LastTry.random.nextInt(2) + 1;

		this.data.blocks[x + y * SIZE] = id;
		this.data.blocksHealth[x + y * SIZE] = ByteHelper.create(true, true, (n == 1 || n == 3), (n == 2), false, false,
				false, false);
	}

	public byte getBlockHP(int globalX, int globalY) {
		return this.getBlockHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getBlockHPInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return 0;
		}

		return this.data.blocksHealth[x + y * SIZE];
	}

	public void setBlockHP(byte hp, int globalX, int globalY, boolean die) {
		this.setBlockHPInside(hp, globalX - this.getX(), globalY - this.getY(), die);
	}

	public void setBlockHPInside(byte hp, int x, int y, boolean die) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.updateLastAccessedTime();

		if (die) {
			Block block = (Block) itemManager.getItem(this.data.blocks[x + y * SIZE]);

			this.data.blocksHealth[x + y * SIZE] = hp;
			this.setBlockInside(null, x, y);

			if (block != null) {
				block.die((short) (x + this.getX()), (short) (y + this.getY()));
			}
		} else {
			this.data.blocksHealth[x + y * SIZE] = hp;
		}
	}

	public String getWall(int globalX, int globalY) {
		return this.getWallInside(globalX - this.getX(), globalY - this.getY());
	}

	public String getWallInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return null;
		}

		return this.data.walls[x + y * SIZE];
	}

	public void setWall(String id, int globalX, int globalY) {
		this.setWallInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallInside(String id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}
		this.updateLastAccessedTime();
		int n = LastTry.random.nextInt(2) + 1;

		this.data.wallsHealth[x + y * SIZE] = ByteHelper.create(true, true, (n == 1 || n == 3), (n == 2), false, false,
				false, false);
		this.data.walls[x + y * SIZE] = id;
	}

	public byte getWallHP(int globalX, int globalY) {
		return this.getWallHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getWallHPInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return 0;
		}

		return this.data.wallsHealth[x + y * SIZE];
	}

	public void setWallHP(byte hp, int globalX, int globalY) {
		this.setWallHPInside(hp, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallHPInside(byte hp, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}
		this.updateLastAccessedTime();

		if (ByteHelper.getBitValue(hp, (byte) 0) == 0 && ByteHelper.getBitValue(hp, (byte) 1) == 0) {
			Wall wall = (Wall)itemManager.getItem(this.data.blocks[x + y * SIZE]);

			if (wall != null) {
				wall.die(x + this.getX(), y + this.getY());
			}

			this.setWallInside(null, x, y);
		} else {
			this.data.wallsHealth[x + y * SIZE] = hp;
		}

		this.data.wallsHealth[x + y * SIZE] = hp;
	}

	public byte getLight(int globalX, int globalY) {
		return this.getLightInside(globalX - this.getX(), globalY - this.getY());
	}

	private byte getLightInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return 0;
		}

		return this.data.light[x + y * SIZE];
	}

	public void setLight(byte light, int globalX, int globalY) {
		this.setLightInside(light, globalX - this.getX(), globalY - this.getY());
	}

	private void setLightInside(byte light, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.data.light[x + y * SIZE] = light;
	}

	public ChunkData getData() {
		return this.data;
	}

	public int getGridX() {
		return (int) this.position.x;
	}

	public int getGridY() {
		return (int) this.position.y;
	}

	public int getX() {
		return (int) this.position.x * SIZE;
	}

	public int getY() {
		return (int) this.position.y * SIZE;
	}

	private boolean isInside(int x, int y) {
		return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
	}

	public UUID getUniqueChunkId() {
		return this.uniqueChunkId;
	}

	private void updateLastAccessedTime() {
		this.lastAccessedTime = LocalDateTime.now();
	}

	public LocalDateTime getLastAccessedTime() {
		return this.lastAccessedTime;
	}

	public boolean isUnloadable() {
		return this.unloadable;
	}

	public void setUnloadable(boolean unloadable) {
		this.unloadable = unloadable;
	}
}