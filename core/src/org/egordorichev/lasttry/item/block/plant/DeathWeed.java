package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class DeathWeed extends Plant {
    public DeathWeed() {
        super(ItemID.deathWeed, "Death Weed", Assets.getTexture(Textures.deathWeedIcon), Assets.getTexture(Textures.deathWeed));
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = LastTry.world.blocks.getID(x, y + 1);

        if (id == ItemID.ebonstoneBlock || id == ItemID.crimstoneBlock) {
            return true; // TODO: add corrupt and crimson grass
        }

        return false;
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = LastTry.world.blocks.getHP(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (LastTry.environment.isBloodMoon()) {
                LastTry.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                LastTry.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            LastTry.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }
}