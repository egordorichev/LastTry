package org.egordorichev.lasttry;

public class Camera {
	private float x = 0;
	private float y = 0;

	/**
	 * Moves graphics view point to self positio
	 */
	public void set() {
		LastTry.graphics.translate(-this.x, -this.y);
	}

	/**
	 * Moves camera back to the start view point
	 */
	public void unset() {
		LastTry.graphics.translate(this.x, this.y);
	}

	/**
	 * Sets new camera position
	 * @param x new position X cooridnate
	 * @param y new position Y coordinate
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return camera x coordinate
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * @return camera y coordinate
	 */
	public float getY() {
		return this.y;
	}
}
