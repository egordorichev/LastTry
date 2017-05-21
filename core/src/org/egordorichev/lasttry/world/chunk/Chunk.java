package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.util.Util;

import java.time.LocalDateTime;
import java.util.UUID;

public class Chunk {
	public static final int SIZE = 256;
	public static final int TOTAL_SIZE = 256 * 256;

	private ChunkData data;
	private Vector2 position;
	private LocalDateTime lastAccessedTime;
	private UUID uniqueChunkId;


	public Chunk(ChunkData data, Vector2 position) {
		this.updateLastAccessedTime();
		this.data = data;
		this.position = position;
		this.uniqueChunkId = UUID.randomUUID();
	}

	public void update() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Block block = (Block) Item.fromID(this.data.blocks[x + y * SIZE]);

				if (block != null) {
					block.updateBlock(x, y);
				}
			}
		}

		this.updateLastAccessedTime();
	}

	public void updateGraphics() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				Block block = (Block) Item.fromID(this.data.blocks[x + y * SIZE]);

				if (block != null) {
					block.updateBlockStyle(x, y);
				}
			}
		}
	}

	private void updateLastAccessedTime() {
		this.lastAccessedTime = LocalDateTime.now();
	}

	public short getBlock(int globalX, int globalY) {
		return this.getBlockInside(globalX - this.getX(), globalY - this.getY());
	}

	public short getBlockInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.blocks[x + y * SIZE];
	}

	public void setBlock(short id, int globalX, int globalY) {
		this.setBlockInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setBlockInside(short id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		int n = LastTry.random.nextInt(2) + 1;

		this.data.blocks[x + y * SIZE] = id;
		this.data.blocksHealth[x + y * SIZE] = ByteHelper.create(true, true, (n == 1 || n == 3),
				(n == 2), false, false, false, false);
	}

	public byte getBlockHP(int globalX, int globalY) {
		return this.getBlockHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getBlockHPInside(int x, int y) {
		if (!this.isInside(x, y)) {

			return ItemID.none;
		}

		return this.data.blocksHealth[x + y * SIZE];
	}

	public void setBlockHP(byte hp, int globalX, int globalY) {
		this.setBlockHPInside(hp, globalX - this.getX(), globalY - this.getY());
	}

	public void setBlockHPInside(byte hp, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}
		this.updateLastAccessedTime();
		if (ByteHelper.getBitValue(hp, (byte) 0) == 0 && ByteHelper.getBitValue(hp, (byte) 1) == 0) {
			Block block = (Block) Item.fromID(this.data.blocks[x + y * SIZE]);

			if (block != null) {
				block.die(x + this.getX(), y + this.getY());
			}

			this.setBlockInside(ItemID.none, x, y);
		} else {
			this.data.blocksHealth[x + y * SIZE] = hp;
		}
	}

	public short getWall(int globalX, int globalY) {
		return this.getWallInside(globalX - this.getX(), globalY - this.getY());
	}

	public short getWallInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.walls[x + y * SIZE];
	}

	public void setWall(short id, int globalX, int globalY) {
		this.setWallInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallInside(short id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}
		this.updateLastAccessedTime();
		int n = LastTry.random.nextInt(2) + 1;

		this.data.wallsHealth[x + y * SIZE] = ByteHelper.create(true, true, (n == 1 || n == 3),
				(n == 2), false, false, false, false);
		this.data.walls[x + y * SIZE] = id;
	}

	public byte getWallHP(int globalX, int globalY) {
		return this.getWallHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getWallHPInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
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
			Wall wall = (Wall) Item.fromID(this.data.blocks[x + y * SIZE]);

			if (wall != null) {
				wall.die(x + this.getX(), y + this.getY());
			}

			this.setWallInside(ItemID.none, x, y);
		} else {
			this.data.wallsHealth[x + y * SIZE] = hp;
		}

		this.data.wallsHealth[x + y * SIZE] = hp;
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

	public LocalDateTime getLastAccessedTime() {
		return this.lastAccessedTime;
	}
}