package org.egordorichev.lasttry.util.geometry;

/**
 * Simple rect class
 */
public class Rectangle {
	/**
	 * Rect X
	 */
	public float x = 0;
	/**
	 * Rect Y
	 */
	public float y = 0;
	/**
	 * Rect width
	 */
	public float w = 0;
	/**
	 * Rect height
	 */
	public float h = 0;

	public Rectangle(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}