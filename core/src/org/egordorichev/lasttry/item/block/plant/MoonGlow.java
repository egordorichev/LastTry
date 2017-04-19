package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class MoonGlow extends Plant {
    public MoonGlow() {
        super(ItemID.moonGlow, "Moon Glow", Assets.getTexture(Textures.moonGlowIcon), Assets.getTexture(Textures.moonGlow));
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = LastTry.world.blocks.getID(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (LastTry.environment.time.isNight()) {
                LastTry.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                LastTry.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            LastTry.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = LastTry.world.blocks.getHP(x, y - 1);

        if (id != ItemID.jungleGrassBlock) {
            return false;
        }

        return true;
    }
}