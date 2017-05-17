package org.egordorichev.lasttry.item.block.station;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorkBench extends WorkingStation {
	public WorkBench(short id, String name, TextureRegion texture, int gridWidth, int gridHeight) {
		super(id, name, false, texture, gridWidth, gridHeight);

		// TODO: solid top
	}
}
