package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.items.ToolPower;

public class MultiTileBlock extends Block {
    public MultiTileBlock(short id, String name, boolean solid, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles, int gridWidth, int gridHeight) {
        super(id, name, solid, requiredPower, texture, tiles);

        this.width = gridWidth;
        this.height = gridHeight;
    }

    @Override
    public void renderBlock(int x, int y, byte binary) {
		boolean first = false;

		if (Globals.world.blocks.getID(x - 1, y) == this.id) {

		} else {
			first = true;
		}

		if (first) {
		    short variant = 1;

		    // Graphics.batch.draw(this.tiles, x * Block.SIZE, y * Block.SIZE); : TODO: fixme
	    }
    }

    public int getGridWidth() {
        return this.width;
    }

    public int getGridHeight() {
        return this.height;
    }

	@Override
	public void place(int x, int y) {
		for (int j = y; j < y + this.height; j++) {
			for (int i = x; i < x + this.width; i++) {
				Globals.world.blocks.set(this.id, i, j);
			}
		}
	}
}