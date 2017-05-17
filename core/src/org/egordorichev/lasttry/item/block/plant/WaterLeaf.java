package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class WaterLeaf extends Plant {
    public WaterLeaf() {
        super(ItemID.waterLeaf, "Water Leaf", Assets.getTextureRegion(Textures.waterLeafIcon), Assets.getTextureRegion(Textures.waterLeaf));
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = Globals.world.blocks.getHP(x, y - 1);

        if (id != ItemID.sandBlock) {
            return false;
        }

        return true;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = Globals.world.blocks.getHP(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (Globals.environment.isRaining()) {
                Globals.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                Globals.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            Globals.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }
}