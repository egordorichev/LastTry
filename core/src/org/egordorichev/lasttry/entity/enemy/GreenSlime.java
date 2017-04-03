package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Item;

public class GreenSlime extends Slime {
    public GreenSlime() {
        super(EnemyID.greenSlime, LastTry.world.isExpertMode() ? 14 : 28, 0, LastTry.world.isExpertMode() ? 6 : 16,
                Textures.greenSlime);

        this.drops.add(new Drop(Item.copperCoin, 3, 3));
    }
}
