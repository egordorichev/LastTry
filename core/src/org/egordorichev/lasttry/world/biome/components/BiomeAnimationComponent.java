package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.world.biome.Biome;

/**
 * Created by Admin on 20/04/2017.
 */
public class BiomeAnimationComponent extends BiomeComponent {
    public BiomeAnimationComponent(Biome biome) {
        super(biome);
    }

    /**
     * Texture alpha
     */
    public float alpha = 0;
}
