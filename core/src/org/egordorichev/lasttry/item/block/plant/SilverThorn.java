package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class SilverThorn extends Plant {
    public SilverThorn() {
        super(ItemID.silverThorn, "Silver Thorn", Assets.getTexture(Textures.silverThornIcon), Assets.getTexture(Textures.silverThorn));
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = Globals.world.blocks.getID(x, y - 1);

        if (id == ItemID.iceBlock || id == ItemID.snowBlock) {
            return true;
        }

        return false;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = getGrowLevel(x, y);

        if (hp > Plant.GROW_THRESHOLD) {
            Globals.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
        } else {
            Globals.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }
}