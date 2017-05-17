package org.egordorichev.lasttry.item.block.station;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.block.MultiTileBlock;
import org.egordorichev.lasttry.item.items.ToolPower;

public class WorkingStation extends MultiTileBlock {
	public WorkingStation(short id, String name, boolean solid, TextureRegion texture, int gridWidth, int gridHeight) {
		super(id, name, solid, ToolPower.pickaxe(10), texture, texture, gridWidth, gridHeight);
	}

	// TODO
}