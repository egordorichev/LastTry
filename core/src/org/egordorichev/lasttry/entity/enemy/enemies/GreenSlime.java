package org.egordorichev.lasttry.entity.enemy.enemies;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.world.biome.Biome;

public class GreenSlime extends Slime {
    public GreenSlime() {
        super(EnemyID.greenSlime, LastTry.world.flags.isExpertMode() ? 10 : 10, 0, LastTry.world.flags.isExpertMode() ? 6 : 16,
		     Assets.getTexture(Textures.greenSlime));

        this.drops.add(new Drop(Items.copperCoin, 3, 3));

    }

    @Override
    public boolean canSpawn(){
        return LastTry.environment.time.isDay() && LastTry.environment.getCurrentBiome()==Biome.forest;
    }

}
