package org.egordorichev.lasttry;

import org.newdawn.slick.Graphics;

public class Camera {
	private float x = 0;
	private float y = 0;

	public void set(Graphics graphics) {
		graphics.translate(-this.x, -this.y);
	}

	public void unset(Graphics graphics) {
		graphics.translate(this.x, this.y);
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
}