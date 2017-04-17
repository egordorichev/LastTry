package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CorruptionBiome extends Biome {
    public CorruptionBiome() {
        super(Id.CORRUPTION, new SpawnInfo(390, 5), Assets.getTexture(Textures.corruptionBack));
    }
}