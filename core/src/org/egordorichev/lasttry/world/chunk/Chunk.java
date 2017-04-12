package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.item.ItemID;

public class Chunk {
	public static final int SIZE = 512;

	private ChunkData data;
	private Vector2 position;

	public Chunk(ChunkData data, Vector2 position) {
		this.data = data;
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

		this.data.blocks[x + y * SIZE] = id;
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

		this.data.blocksHealth[x + y * SIZE] = hp;
	}

	public short getWall(int globalX, int globalY) {
		return this.getWallInside(globalX - this.getX(), globalY - this.getY());
	}

	public short getWallInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.blocks[x + y * SIZE];
	}

	public void setWall(short id, int globalX, int globalY) {
		this.setWallInside(id, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallInside(short id, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.data.blocks[x + y * SIZE] = id;
	}

	public byte getWallHP(int globalX, int globalY) {
		return this.getWallHPInside(globalX - this.getX(), globalY - this.getY());
	}

	public byte getWallHPInside(int x, int y) {
		if (!this.isInside(x, y)) {
			return ItemID.none;
		}

		return this.data.blocksHealth[x + y * SIZE];
	}

	public void setWallHP(byte hp, int globalX, int globalY) {
		this.setWallHPInside(hp, globalX - this.getX(), globalY - this.getY());
	}

	public void setWallHPInside(byte hp, int x, int y) {
		if (!this.isInside(x, y)) {
			return;
		}

		this.data.blocksHealth[x + y * SIZE] = hp;
	}

	public int getGridX() {
		return (int) this.position.x * SIZE;
	}

	public int getGridY() {
		return (int) this.position.y / SIZE;
	}

	public int getX() {
		return (int) this.position.x;
	}

	public int getY() {
		return (int) this.position.y;
	}

	private boolean isInside(int x, int y) {
		return (x >= 0 && x < SIZE && y >= 0 && y < SIZE);
	}
}