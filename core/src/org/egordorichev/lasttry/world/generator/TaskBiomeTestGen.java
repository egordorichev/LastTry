package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.biome.BiomeMap;

public class TaskBiomeTestGen extends GeneratorTask {
	@Override
	public void run(WorldGenerator generator) {
		// TODO: Better offset math than this
		int tempXOffset = 100 * generator.world.getSeed();
		int tempYOffset = -100 * generator.world.getSeed();
		int humidXOffset = 200 * generator.world.getSeed();
		int humidYOffset = -200 * generator.world.getSeed();
		// TODO: These numbers shouldn't be hard-coded
		// TODO: Let the user choose these variables when generating a world.
		float biomeSize = 800f;
		int octaves = 1;
		float roughness = 0.5f;
		float scale = 1f / biomeSize;
		for (int x = 0; x < generator.getWorldWidth(); x++) {
			for (int y = 0; y < generator.getWorldHeight(); y++) {
				if (generator.world.blocks.getID(x, y).isEmpty()) {
					float n1 = SimplexNoise.octavedNoise(x + tempXOffset, y + tempYOffset, octaves, roughness, scale);
					float n2 = SimplexNoise.octavedNoise(-x + humidXOffset, -y + humidYOffset, octaves, roughness,
							scale);
					int temperature = (int) ((n1 + 1) * (BiomeMap.TEMP_MAX / 2));
					int humidity = (int) ((n2 + 1) * (BiomeMap.HUMID_MAX / 2));
					Biome biome = Globals.biomeMap.getClosest(temperature, humidity);

					if (biome == Biome.desert) {
						generator.world.blocks.set("lt:sand", x, y);
					} else if (biome == Biome.forest) {
						generator.world.blocks.set("lt:dirt", x, y);
					} else if (biome == Biome.crimson) {
						generator.world.blocks.set("lt:crimstone", x, y);
					} else if (biome == Biome.crimsonDesert) {
						generator.world.blocks.set("lt:crimsand", x, y);
					} else if (biome == Biome.corruption) {
						generator.world.blocks.set("lt:ebonstone", x, y);
					} else if (biome == Biome.corruptDesert) {
						generator.world.blocks.set("lt:ebonsand", x, y);
					}
				}
			}
		}
	}
}
