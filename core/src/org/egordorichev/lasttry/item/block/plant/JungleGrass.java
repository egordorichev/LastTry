package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class JungleGrass extends Grass {
    public JungleGrass() {
        super(ItemID.jungleGrassBlock, "Jungle Grass Block",
		    Assets.getTextureRegion(Textures.jungleGrassIcon), Assets.getTextureRegion(Textures.jungleGrass));
    }

    @Override
    public boolean canBeGrownAt(short id) {
        if (id == ItemID.mudBlock) {
            return true;
        }

        return false;
    }
}