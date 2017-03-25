package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class ForestBiome extends Biome {
	public ForestBiome() {
		super("Forest");

		this.texture = Textures.forestBack;
		// this.texture.setAlpha(0.0f);
	}
}
