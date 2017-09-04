package org.egordorichev.lasttry.world.generator;

public class SurfaceGenerationSimplexTask extends GeneratorTask {

	@Override
	public void run(WorldGenerator generator) {
		int width = generator.getWorldWidth();
		int height = generator.getWorldHeight();

		// Hill size
		double stretch = 200;
		int hillStrength = 25;
		int stoneHeightVarience = 15;
		// Range of height for terrain
		int max = height - 100;
		int min = max - hillStrength;

		for (int x = 0; x < width; x++) {
			double xin = x / stretch;
			double total = SimplexNoise.octavedNoise((float) xin, 0, 4, 1, 1) + 1;
			total *= hillStrength / 2.0;
			total += min;
			
			double stoneLevel = SimplexNoise.octavedNoise((float) xin, 100f, 5, 3f, 1) + 1;
			stoneLevel *= stoneHeightVarience / 2.0;

			int yMax = (int) Math.round(total);
			for (int y = 0; y < yMax; y++) {
				if (y == yMax) {
					generator.world.blocks.set("lt:grass", x, y);
				} else if (y < yMax - stoneLevel) {
					generator.world.blocks.set("lt:stone", x, y);
					generator.world.walls.set("lt:stone_wall", x, y);
				} else if (y < yMax) {
					generator.world.blocks.set("lt:dirt", x, y);
					generator.world.walls.set("lt:dirt_wall", x, y);
				}
			}
		}
	}
}
