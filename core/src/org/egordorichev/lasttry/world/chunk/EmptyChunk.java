package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;

public class EmptyChunk extends Chunk {
	public EmptyChunk(Vector2 position) {
		super(null, position);
	}

	@Override
	public void setBlock(String id, int globalX, int globalY) {

	}

	@Override
	public void setBlockHP(byte hp, int globalX, int globalY) {

	}

	@Override
	public void setWall(String id, int globalX, int globalY) {

	}

	@Override
	public void setWallHP(byte hp, int globalX, int globalY) {

	}

	@Override
	public String getBlock(int globalX, int globalY) {
		return null;
	}

	@Override
	public byte getBlockHP(int globalX, int globalY) {
		return 0;
	}

	@Override
	public String getWall(int globalX, int globalY) {
		return null;
	}

	@Override
	public byte getWallHP(int globalX, int globalY) {
		return 0;
	}
}
