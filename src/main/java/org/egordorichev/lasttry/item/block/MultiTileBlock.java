package org.egordorichev.lasttry.item.block;

import org.newdawn.slick.Image;

public class MultiTileBlock extends Block {
	protected int width;
	protected int height;

	public MultiTileBlock(short id, String name, boolean solid, Image texture, Image tiles, int gridWidth, int gridHeight) {
		super(id, name, solid, texture, tiles);

		this.width = gridWidth;
		this.height = gridHeight;
	}

	@Override
	public void renderBlock(int x, int y) {
		// TODO
	}

	public int getGridWidth() {
		return this.width;
	}

	public int getGridHeight() {
		return this.height;
	}
}