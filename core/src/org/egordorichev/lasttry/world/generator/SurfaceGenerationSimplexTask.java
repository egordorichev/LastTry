package org.egordorichev.lasttry.world.generator;

public class SurfaceGenerationSimplexTask extends GeneratorTask {

	@Override
	public void run(WorldGenerator generator) {
		int width = generator.getWorldWidth();
		int height = generator.getWorldHeight();

		// Hill size
		double smoothness = 50;
		int range = 25;
		// Range of height for terrain
		int max = height - 100;
		int min = max - range;

		for (int x = 0; x < width; x++) {
			double xin = x / smoothness;
			double h = SimplexNoise.noise(xin, 0) + 1;
			h *= range / 2;
			h += min;
			int yMax = (int) Math.round(h);
			for (int y = 0; y < yMax; y++) {
				if (y == yMax) {
					generator.world.blocks.set("lt:grass", x, y);
				} else if (y < yMax) {
					generator.world.blocks.set("lt:dirt", x, y);
					generator.world.walls.set("lt:dirt_wall", x, y);
				}
			}
		}
	}
}
