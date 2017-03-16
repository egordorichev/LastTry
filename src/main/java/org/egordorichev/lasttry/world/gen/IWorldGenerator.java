package org.egordorichev.lasttry.world.gen;

public interface IWorldGenerator {
	/**
	 * Generate an array of block ID's 
	 * @param width
	 * @param height
	 * @return
	 */
	int[][] generate(int width, int height);
}
