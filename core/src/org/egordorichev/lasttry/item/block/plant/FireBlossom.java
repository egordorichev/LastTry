package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class FireBlossom extends Plant {
    public FireBlossom() {
        super(ItemID.fireBlossom, "Fire Blossom", Assets.getTexture(Textures.fireBlossomIcon), Assets.getTexture(Textures.fireBlossom));
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = LastTry.world.getBlockID(x, y + 1);

        if (id == ItemID.ashBlock) {
            return true;
        }

        return false;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = LastTry.world.getBlockHp(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (LastTry.environment.time.getHour() >= 4 && LastTry.environment.time.getHour() <= 7
                    && !LastTry.environment.isRaining()) { // TODO: from 3:45 to 7:30

                LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                LastTry.world.setBlockHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            LastTry.world.setBlockHP((byte) (hp + 1), x, y);
        }
    }
}