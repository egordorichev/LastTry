package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class WaterLeaf extends Plant {
    public WaterLeaf() {
        super(ItemID.waterLeaf, "Water Leaf", Textures.waterLeafIcon, Textures.waterLeaf);
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = LastTry.world.getBlockID(x, y + 1);

        if (id != ItemID.sandBlock) {
            return false;
        }

        return true;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = LastTry.world.getBlockHp(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (LastTry.environment.isRaining()) {
                LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            LastTry.world.setBlockHP((byte) (hp + 1), x, y);
        }
    }
}