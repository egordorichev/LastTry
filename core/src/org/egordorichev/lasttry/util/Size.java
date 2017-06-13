package org.egordorichev.lasttry.util;

import com.badlogic.gdx.math.Vector2;

public class Size {
	public float width;
	public float height;
	private Vector2 size;

	public Size(float width, float height) {
		size = new Vector2(width, height);
		width = size.x;
		height = size.y;
	}
}