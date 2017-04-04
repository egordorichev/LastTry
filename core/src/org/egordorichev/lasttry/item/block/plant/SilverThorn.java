package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class SilverThorn extends Plant {
    public SilverThorn() {
        super(ItemID.silverThorn, "Silver Thorn", Textures.silverThornIcon, Textures.silverThorn);
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = LastTry.world.getBlockID(x, y + 1);

        if (id == ItemID.iceBlock || id == ItemID.snowBlock) {
            return true;
        }

        return false;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = LastTry.world.getBlockHp(x, y);

        if (hp > Plant.GROW_THRESHOLD) {
            LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
        } else {
            LastTry.world.setBlockHP((byte) (hp + 1), x, y);
        }
    }
}