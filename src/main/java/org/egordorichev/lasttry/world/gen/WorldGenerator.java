package org.egordorichev.lasttry.world.gen;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;

public class WorldGenerator implements IWorldGenerator {
	private int tiles[][];

	@Override
	public int[][] generate(int width, int height) { // Must be power of two
		this.tiles = new int[width][height];

		// TODO: biomes

		double displace = height / 4;
		double roughness = 0.45;
		double[] points = new double[width + 1];
		double power = Math.pow(2, Math.ceil(Math.log(width) / (Math.log(2))));

		points[0] = 200 + ((displace) * LastTry.random.nextDouble() * 2) - displace;
		points[(int) power] = 200 + ((displace) * LastTry.random.nextDouble() * 2) - displace;

		displace *= roughness;

		for(int i = 1; i < power; i *= 2) {
			for(int j = (int) (power / i) / 2; j < power; j+= power / i){
				points[j] = ((points[j - (int)(power / i) / 2] + points[j + (int) (power / i) / 2]) / 2);
				points[j] += ((displace) * LastTry.random.nextDouble() * 2) - displace;
			}

			displace *= roughness;
		}

		for (int x = 0; x < width; x++) {
			int yMax = (int) points[x];


			for (int y = 0; y < height; y++) {
				// Temporary basic generation
				if (y == yMax) {
					this.tiles[x][y] = ItemID.grassBlock;
				} else if (y > yMax) {
					this.tiles[x][y] = ItemID.dirtBlock;
				} else {
					this.tiles[x][y] = 0;
				}
			}
		}

		return this.tiles;
	}
}
