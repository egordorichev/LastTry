package org.egordorichev.lasttry.world.gen;

import org.egordorichev.lasttry.item.ItemID;

public class SimpleWorldGen implements IWorldGenerator {

	@Override
	public int[][] generate(int width, int height) {
		int tileIDs[][] = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Temporary basic generation
				if (y == 120) {
					tileIDs[x][y] = ItemID.grassBlock;
				} else if (y > 120) {
					tileIDs[x][y] = ItemID.dirtBlock;
				} else {
					tileIDs[x][y] = 0;
				}
			}
		}
		return tileIDs;
	}

}
