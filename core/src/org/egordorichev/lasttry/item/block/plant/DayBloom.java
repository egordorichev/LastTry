package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class DayBloom extends Plant {
    public DayBloom() {
        super(ItemID.dayBloom, "Day Bloom", Assets.getTexture(Textures.dayBloomIcon), Assets.getTexture(Textures.dayBloom));
    }

    @Override
    public void updateBlock(int x, int y) {
        int hp = getGrowLevel(x, y);

        if (hp >= Plant.GROW_THRESHOLD) {
            if (Globals.environment.time.isDay() && LastTry.random.nextInt(10) != 0) {
                Globals.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD + 1), x, y);
            } else {
                Globals.world.blocks.setHP((byte) (Plant.GROW_THRESHOLD), x, y);
            }
        } else {
            Globals.world.blocks.setHP((byte) (hp + 1), x, y);
        }
    }

    @Override
    public boolean canBeGrownAt(int x, int y) {
        if (!super.canBeGrownAt(x, y)) {
            return false;
        }

        short id = Globals.world.blocks.getID(x, y - 1);

        if (id != ItemID.grassBlock) {
            return false;
        }

        return true;
    }
}