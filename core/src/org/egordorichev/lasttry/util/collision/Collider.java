package org.egordorichev.lasttry.util.collision;

/**
 * Helper class, tests collisions between objects
 */
public class Collider {
	public static boolean testAABB(
		float x1, float y1, float w1, float h1,
	  float x2, float y2, float w2, float h2
	) {
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	}
}