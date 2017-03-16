package org.egordorichev.lasttry.world.gen;

import org.egordorichev.lasttry.item.ItemID;

public class SimpleWorldGen implements IWorldGenerator {

	@Override
	public int[][] generate(int width, int height) {
		int tileIDs[][] = new int[width][height];
		for (int x = 0; x < width; x++) {
			int yMax = 120 + (int) (Math.cos(x / 5.0) * 4);
			for (int y = 0; y < height; y++) {
				// Temporary basic generation
				if (y == yMax) {
					tileIDs[x][y] = ItemID.grassBlock;
				} else if (y > yMax) {
					tileIDs[x][y] = ItemID.dirtBlock;
				} else {
					tileIDs[x][y] = 0;
				}
			}
		}
		return tileIDs;
	}

}
