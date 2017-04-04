package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.items.ToolPower;

public class Plant extends Block {
    public static final byte GROW_THRESHOLD = 20;

    public Plant(short id, String name, Texture texture, Texture tiles) {
        super(id, name, false, ToolPower.pickaxe(10), texture, tiles);
    }

    @Override
    public void renderBlock(int x, int y) {
        int hp = LastTry.world.getBlockHp(x, y);

        int tx = 0;

        if (hp >= GROW_THRESHOLD + 1) {
            tx = 32;
        } else if (hp == GROW_THRESHOLD) {
            tx = 16;
        }

        LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE, (LastTry.world.getHeight() - y - 1) * Block.TEX_SIZE,
                tx, 0, Block.TEX_SIZE, Block.TEX_SIZE);
    }

    @Override
    protected boolean renderCracks() {
	    return false;
    }

    public boolean canBeGrownAt(int x, int y) {
        short id = LastTry.world.getBlockID(x, y);

        if (id != ItemID.none) {
            return false;
        }

        return true;
    }
}