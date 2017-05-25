package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class DesertBiome extends Biome {
    public DesertBiome() {
        super("Desert", new SpawnInfo(180, 7), Assets.getTexture(Textures.forestBack), 90, 0); // TODO: replace
    }
}