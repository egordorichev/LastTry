package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.items.ToolPower;

public class MultiTileBlock extends Block {
    protected int width;
    protected int height;

    public MultiTileBlock(short id, String name, boolean solid, ToolPower requiredPower, Texture texture, Texture tiles, int gridWidth, int gridHeight) {
        super(id, name, solid, requiredPower, texture, tiles);

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