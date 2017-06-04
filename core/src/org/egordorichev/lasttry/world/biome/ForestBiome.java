package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class ForestBiome extends Biome {
    public ForestBiome() {
        super("Forest", new SpawnInfo(800, 5), Assets.getTexture(Textures.forestBack), 40, 40);
    }
}
