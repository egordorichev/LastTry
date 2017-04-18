package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.world.biome.Biome;

public class BlueSlime extends Slime {
    public BlueSlime() {
        super("BlueSlime", EnemyID.blueSlime, LastTry.world.isExpertMode() ? 10 : 10, 2, LastTry.world.isExpertMode() ? 7 : 14,3, Assets.getTexture(Textures.blueSlime));

        this.drops.add(new Drop(Items.copperCoin, 25, 25));
    }

    @Override
    public boolean canSpawn(){
        return LastTry.environment.time.isDay() && LastTry.environment.getCurrentBiome()==Biome.forest;
    }
}
