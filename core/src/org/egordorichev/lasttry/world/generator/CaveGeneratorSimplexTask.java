package org.egordorichev.lasttry.world.generator;

public class CaveGeneratorSimplexTask extends GeneratorTask {

	@Override
	public void run(WorldGenerator generator) {
		// TODO: These numbers shouldn't be hard-coded
		// TODO: Let the user choose these variables when generating a world.
		int caveBiasDepth = 30;
		int octaves = 4;
		float roughness = 0.6f;
		float scale = 1f / 20f;
		float cut = 0f;
		boolean[][] solidMap = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];
		for (int x = 0; x < generator.getWorldWidth(); x++) {
			int antiCaveBiasHeight = generator.getHighest(x) - caveBiasDepth;
			for (int y = 0; y < generator.getWorldHeight(); y++) {
				// Get noise value at the coordinates
				float f = SimplexNoise.octavedNoise(x, y, octaves, roughness, scale);
				if (y >= antiCaveBiasHeight) {
					// Average the value with a negative number to lessen the
					// effect of cave generation above (Y = antiCaveBiasHeight)
					f -= Math.abs(Math.sin(x / 30f) / 1.5f);
					f /= 4;
				}
				// Solidity is determined if the noise (which is [-1,1] -
				// antiCaveBias) is less than a given value. If cut is 0.5f, 75%
				// of the land should end up being solid, 25% will become caves.
				boolean isSolid = f <= cut;
				solidMap[x][y] = isSolid;
			}
		}
		for (int x = 0; x < generator.getWorldWidth(); x++) {
			for (int y = 0; y < generator.getWorldHeight(); y++) {
				// Skip existing air blocks
				if (generator.world.blocks.getID(x, y) == null) {
					continue;
				}
				// Insert caves
				if (!solidMap[x][y]) {
					generator.world.blocks.set(null, x, y);
				} else {
					// For non caves, apply grass borders.
					// Cut off any blocks with too few neighbors to prevent
					// the some of the floating blocks.
					int neighbors = this.calculateNeighbors(generator, solidMap, x, y);
					if (neighbors <= 3) {
						generator.world.blocks.set(null, x, y);
					} else if (neighbors != 8) {
						// Create cave wall lining based on what tile is being repalced.
						String id = generator.world.blocks.getID(x, y);
						if (id.equals("lt:grass") || id.equals("lt:dirt")) {
							generator.world.blocks.set("lt:grass", x, y);
						} else {
							generator.world.blocks.set("lt:stone", x, y);
						}
					}
				}
			}
		}
	}
}
