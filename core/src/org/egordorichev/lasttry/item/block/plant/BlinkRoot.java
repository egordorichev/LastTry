package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class BlinkRoot extends Plant {
    public BlinkRoot() {
        super(ItemID.blinkRoot, "Blink Root", Assets.getTexture(Textures.blinkRootIcon), Assets.getTexture(Textures.blinkRoot));
    }

    @Override
    public void updateBlock(int x, int y) {
        byte hp = getGrowLevel(x, y);

        if (hp >= Plant.GROW_THRESHOLD + 1 && LastTry.random.nextInt(3) == 0) {
            Globals.world.blocks.setHP(Plant.GROW_THRESHOLD, x, y);
        } else if (hp == Plant.GROW_THRESHOLD || hp == Plant.GROW_THRESHOLD + 1) {
            Globals.world.blocks.setHP(Plant.GROW_THRESHOLD, x, y);
        } else if (hp < Plant.GROW_THRESHOLD) {
            Globals.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = Globals.world.blocks.getID(x, y - 1);

        if (id != ItemID.dirtBlock && id != ItemID.mudBlock) {
            return false;
        }

        return true;
    }
}