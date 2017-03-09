package org.egordorichev.lasttry.world.tile;

import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.item.Wall;

import java.util.Random;

public class TileData {
	public Block block;
	public Wall wall;
	public byte blockHp;
	public byte wallHp;
	public byte data;
	public byte variant;

	public static byte maxHp = 10;

	public TileData(Block block, Wall wall) {
		this.block = block;
		this.wall = wall;
		this.blockHp = maxHp;
		this.wallHp = maxHp;
		this.data = 0;
		this.variant = (byte) new Random().nextInt(3);
	}
}