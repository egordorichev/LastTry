package org.egordorichev.lasttry.world.biome;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;

public class CorruptionBiome extends Biome {
	public CorruptionBiome() {
		super("Corruption");

		this.texture = Textures.corruptionBack;
		// this.texture.setAlpha(0.0f);
	}
}