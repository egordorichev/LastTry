package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class ForestBiome extends Biome {
    public ForestBiome() {
        super(Id.FOREST, new SpawnInfo(600, 5), Assets.getTexture(Textures.forestBack));
    }
}
