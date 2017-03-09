package org.egordorichev.lasttry;

import org.newdawn.slick.Graphics;

public class Camera {
	private static float x = 0;
	private static float y = 0;

	public static void set(Graphics graphics) {
		graphics.translate(x, y);
	}

	public static void unset(Graphics graphics) {
		graphics.translate(-x, -y);
	}

	public static void setPosition(float x, float y) {
		x = x;
		y = y;
	}

	public static float getX() {
		return x;
	}

	public static float getY() {
		return y;
	}
}