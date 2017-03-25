package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CrimsonBiome extends Biome {
	public CrimsonBiome() {
		super("Crimson");

		this.texture = Textures.crimsonBack;
		// this.texture.setAlpha(0.0f);
	}
}