package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CrimsonBiome extends Biome {
    public CrimsonBiome() {
        super(Id.CRIMSON, new SpawnInfo(390, 6), Assets.getTexture(Textures.crimsonBack));
    }
}