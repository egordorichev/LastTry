package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.BlockGround;

public class Grass extends BlockGround {
    public Grass(String id) {
        super(id);
    }

    @Override
    public void updateBlock(int x, int y) {
        // TODO: spread it
    }

    @SuppressWarnings("unused")
	private void spread(int x, int y) {
        ItemManager itemManager = CoreRegistry.get(ItemManager.class);

        Block block = (Block) itemManager.getItem(Globals.getWorld().blocks.getID(x, y));

        if (block != null && this.canBeGrownAt(block.getID())) {
            Globals.getWorld().blocks.set(this.id, x, y);
        }
    }

    public boolean canBeGrownAt(String id) {
        return id.equals("lt:grass_block");
    }
}