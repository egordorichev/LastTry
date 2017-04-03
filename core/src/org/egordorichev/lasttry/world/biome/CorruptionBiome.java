package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Textures;

public class CorruptionBiome extends Biome {
    public CorruptionBiome() {
        super("Corruption", new SpawnInfo(390, 5), Textures.corruptionBack);
    }
}