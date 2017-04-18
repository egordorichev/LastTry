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
        super("GreenSlime", EnemyID.greenSlime, LastTry.world.isExpertMode() ? 10 : 10, 0, LastTry.world.isExpertMode() ? 6 : 16,2,
		     Assets.getTexture(Textures.greenSlime));

        this.drops.add(new Drop(Items.copperCoin, 3, 3));

    }

    @Override
    public boolean canSpawn(){
        return LastTry.environment.time.isDay() && LastTry.environment.getCurrentBiome()==Biome.forest;
    }

}
