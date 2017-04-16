package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.item.ItemID;

public class EmptyChunk extends Chunk {
	public EmptyChunk(Vector2 position) {
		super(null, position);
	}

	@Override
	public void setBlock(short id, int globalX, int globalY) {

	}

	@Override
	public void setBlockHP(byte hp, int globalX, int globalY) {

	}

	@Override
	public void setWall(short id, int globalX, int globalY) {

	}

	@Override
	public void setWallHP(byte hp, int globalX, int globalY) {

	}

	@Override
	public short getBlock(int globalX, int globalY) {
		return ItemID.none;
	}

	@Override
	public byte getBlockHP(int globalX, int globalY) {
		return 0;
	}

	@Override
	public short getWall(int globalX, int globalY) {
		return ItemID.none;
	}

	@Override
	public byte getWallHP(int globalX, int globalY) {
		return 0;
	}
}
