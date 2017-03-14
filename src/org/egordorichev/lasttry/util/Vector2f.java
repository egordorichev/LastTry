package org.egordorichev.lasttry.util;

import org.newdawn.slick.util.FastTrig;

public class Vector2f {
	

	private float angle;

	public float x;
	public float y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(float angle) {
		this.x = 1;
		this.y = 0;

		this.rotate(angle);
	}

	public Vector2f(Vector2f other) {
		this(other.x, other.y);
	}

	public Vector2f() {

	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void rotate(float angle) {
		this.angle = angle;

		if((angle < -360) || (angle > 360)) {
			angle = angle % 360;
		}

		if(angle < 0) {
			angle = 360 + angle;
		}

		float length = this.getLength();

		this.x = length * (float) FastTrig.cos(StrictMath.toRadians(angle));
		this.y = length * (float) FastTrig.sin(StrictMath.toRadians(angle));
	}

	public Vector2f getPerpendicular() {
		return new Vector2f(-this.y, this.x);
	}

	public void negate() {
		this.x = -this.x;
		this.y = -this.y;
	}

	public void add(Vector2f other) {
		this.x += other.x;
		this.y += other.y;
	}

	public void multiply(float a) {
		this.x *= a;
		this.y *= a;
	}

	public void normalise() {
		float length = this.getLength();

		if(length == 0) {
			return;
		}

		this.x /= length;
		this.y /= length;
	}

	public float getLengthSquared() {
		return (this.x * this.x) + (this.y * this.y);
	}

	public float getLength() {
		return (float) Math.sqrt(this.getLengthSquared());
	}

	public float getRotation() {
		return this.angle;
	}

}