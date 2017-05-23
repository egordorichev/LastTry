package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CrimsonDesertBiome extends Biome {
    public CrimsonDesertBiome() {
        super("CrimsonDesert", new SpawnInfo(390, 6), Assets.getTexture(Textures.crimsonBack), 100, 0);
    }
}