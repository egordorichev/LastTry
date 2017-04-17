package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CrimsonDesertBiome extends Biome {
    public CrimsonDesertBiome() {
        super(BiomeSpawnIdentifier.CRIMSONDESERT, "Crimson Desert", new SpawnInfo(390, 6), Assets.getTexture(Textures.crimsonBack));
    }
}