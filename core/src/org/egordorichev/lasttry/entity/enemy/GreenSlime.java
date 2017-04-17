package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.world.biome.Biome;

public class GreenSlime extends Slime {
    public GreenSlime() {
        super(EnemyID.greenSlime, LastTry.world.isExpertMode() ? 14 : 28, 0, LastTry.world.isExpertMode() ? 6 : 16,2,
		    Biome.BiomeSpawnIdentifier.ALL, Assets.getTexture(Textures.greenSlime));

        this.drops.add(new Drop(Items.copperCoin, 3, 3));
    }
}
