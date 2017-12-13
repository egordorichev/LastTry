package org.egordorichev.lasttry.util.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random stuff
 */
public class RandomGenerator {
	/**
	 * Generates a random int
	 *
	 * @param min Min int
	 * @param max Max int
	 * @return Random number
	 */
	public static int random(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}