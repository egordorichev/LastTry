package org.egordorichev.lasttry.entity.component;

/**
 * Simple position component
 */
public class PositionComponent extends Component {
	/**
	 * X coordinate
	 */
	private float x;
	/**
	 * Y coordinate
	 */
	private float y;

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
}