package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CorruptDesertBiome extends Biome {
    public CorruptDesertBiome() {
        super(BiomeSpawnIdentifier.CORRUPTDESERT, "Corrupt Desert", new SpawnInfo(390, 6), Assets.getTexture(Textures.corruptionBack)); // TODO: replace texture
    }
}