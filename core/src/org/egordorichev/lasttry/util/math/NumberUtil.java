package org.egordorichev.lasttry.util.math;

/**
 * Simple number util
 */
public class NumberUtil {
	public static float clamp(float x, float y, float z) {
		if (x > y) {
			float tmp = x;

			x = y;
			y = tmp;
		}

		return Math.max(x, Math.min(y, z));
	}
}