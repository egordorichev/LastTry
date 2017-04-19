package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.BlockGround;
import org.egordorichev.lasttry.item.items.ToolPower;

public class Grass extends BlockGround {
    public Grass(short id, String name, Texture texture, Texture tiles) {
        super(id, name, ToolPower.pickaxe(10) ,texture, tiles);
    }

    @Override
    public void updateBlock(int x, int y) {
        // TODO: spread it
    }

    private void spread(int x, int y) {
        Block block = (Block) Item.fromID(LastTry.world.blocks.getID(x, y));

        if (block != null && this.canBeGrownAt(block.getID())) {
            LastTry.world.blocks.set(this.id, x, y);
        }
    }

    public boolean canBeGrownAt(short id) {
        if (id == ItemID.dirtBlock) {
            return true;
        }

        return false;
    }
}