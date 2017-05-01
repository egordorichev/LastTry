package org.egordorichev.lasttry.entity.enemy.enemies;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.enemy.EnemyID;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.world.biome.Biome;

public class GreenSlime extends Slime {
    public GreenSlime() {
        super(EnemyID.greenSlime, "Green Slime", Globals.world.flags.isExpertMode() ? 10 : 10, 0, Globals.world.flags.isExpertMode() ? 6 : 16,
		     Assets.getTexture(Textures.greenSlime));

        this.drops.add(new Drop(Items.copperCoin, 3, 3));

    }

    @Override
    public boolean canSpawn(){
        return Globals.environment.time.isDay() && Globals.environment.currentBiome.get() == Biome.forest;
    }
}
