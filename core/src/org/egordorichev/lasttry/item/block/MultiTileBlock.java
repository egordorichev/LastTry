package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.items.ToolPower;

public class MultiTileBlock extends Block {
    public MultiTileBlock(short id, String name, boolean solid, ToolPower requiredPower, Texture texture, Texture tiles, int gridWidth, int gridHeight) {
        super(id, name, solid, requiredPower, texture, tiles);

        this.width = gridWidth;
        this.height = gridHeight;
    }

    @Override
    public void renderBlock(int x, int y) {
		boolean first = false;

		if (LastTry.world.getBlockID(x - 1, y) == this.id) {

		} else {
			first = true;
		}

		if (first) {
		    short variant = 1;

		    LastTry.batch.draw(this.tiles, x * Block.SIZE,
				(LastTry.world.getHeight() - y - 1) * Block.SIZE);
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
				LastTry.world.setBlock(this.id, i, j);
			}
		}
	}
}