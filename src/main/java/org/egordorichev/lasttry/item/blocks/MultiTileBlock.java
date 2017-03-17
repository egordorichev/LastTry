package org.egordorichev.lasttry.item.blocks;

import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class MultiTileBlock extends Block {
	protected int width;
	protected int height;

	public MultiTileBlock(int id, String name, boolean solid, Image texture, Image tiles, int gridWidth, int gridHeight) {
		super(id, name, solid, texture, tiles);

		this.width = gridWidth;
		this.height = gridHeight;
	}

	@Override
	public void renderBlock(TileData data, int x, int y) {
		// TODO
	}

	public int getGridWidth() {
		return this.width;
	}

	public int getGridHeight() {
		return this.height;
	}
}