package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;

public class CrimsonBiome extends Biome {
	public CrimsonBiome() {
		super("Crimson");

		this.texture = Assets.crimsonBackTexture;
		this.texture.setAlpha(0.0f);
	}
}