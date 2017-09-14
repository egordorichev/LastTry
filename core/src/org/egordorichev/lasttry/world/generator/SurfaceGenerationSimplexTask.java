package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.util.SimplexNoise;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.biome.BiomeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurfaceGenerationSimplexTask extends GeneratorTask {
	private static final Logger logger = LoggerFactory.getLogger(SurfaceGenerationSimplexTask.class);
	/**
	 * Horizontal terrain stretch,
	 */
	private final float xScale = 200;

	/**
	 * Vertical intensity of generated hills.
	 */
	private final int hillStrength = 25;
	/**
	 * The deviation from the standard stone-generation depth.
	 */
	private final int stoneDeviation = 20;
	/**
	 * Depth below the highest point on the map that stone should spawn at.
	 */
	private final int stoneDepth = 15;

	/**
	 * Controls biome edge shapes. Lower values result in more consistent and straight edges.
	 */
	private final float biomeRoughness = 1f;

	/**
	 * Biome size scale.
	 */
	private final float biomeScale = 200f;

	private final BiomeManager biomeManager;

	SurfaceGenerationSimplexTask(){
		biomeManager = CoreRegistry.get(BiomeManager.class);
	}

	@Override
	public void run(WorldGenerator generator) {
		logger.info("Generating heightmap");
		int width = generator.getWorldWidth();
		int height = generator.getWorldHeight();

		// Range of height for terrain
		int max = height - 100;
		int min = max - hillStrength;

		for (int x = 0; x < width; x++) {
			double xin = x / xScale;
			double total = SimplexNoise.octavedNoise((float) xin, 0, 4, 1, 1) + 1;
			total *= hillStrength / 2.0;
			total += min;

			int yMax = (int) Math.round(total);
			int yStone = yMax - (stoneDepth + scaleStoneDeviation(SimplexNoise.octavedNoise((float) xin, 100f, 5, 3f, 1)));
			for (int y = 0; y <= yMax; y++) {
				Biome biome = getBiome(generator.world.getSeed(), x, y);
				if (y == yMax) {
					generator.world.blocks.set(biome.getMaterials().getTopSoil(), x, y);
				} else if (y < yStone) {
					generator.world.blocks.set(biome.getMaterials().getStone(), x, y);
					generator.world.walls.set(biome.getMaterials().getStoneWall(), x, y);
				} else if (y < yMax) {
					generator.world.blocks.set(biome.getMaterials().getSoil(), x, y);
					generator.world.walls.set(biome.getMaterials().getSoilWall(), x, y);
				}
			}
		}
	}

	private Biome getBiome(int seed, int x, int y) {
		int xt = x + (seed % 10_000);
		int yt = y + (seed % 10_000);
		int xh = -x - (seed % 10_000);
		int yh = -y - (seed % 10_000);
		int temperature = scaleHeight(SimplexNoise.octavedNoise(xt, yt, 3, biomeRoughness, 0.1f / biomeScale));
		int humidity = scaleHeight(SimplexNoise.octavedNoise(xh, yh, 3, biomeRoughness, 0.1f / biomeScale));
		return biomeManager.getClosest(temperature, humidity);
	}

	private static int scaleHeight(float input) {
		float old = input;
		float oldMin = -1;
		float oldMax = 1;
		// New range is hack to diversify results. The input values tend to be
		// around 0, and fade off to their range ends fast. This artifically
		// expands the range and clamps the results back into the intended
		// [0-100] range.
		float newMin = -40;
		float newMax = 140;
		input = (((old - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
		return (int) Util.clamp(input, 0, 99);
	}

	private int scaleStoneDeviation(float input) {
		float old = input;
		float oldMin = -1;
		float oldMax = 1;
		float newMin = -this.stoneDeviation;
		float newMax = this.stoneDeviation;
		input = (((old - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
		return (int) Util.clamp(input, 0, 99);
	}
}
