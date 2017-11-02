package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

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

	public PositionComponent(Entity entity) {
		super(entity);
	}

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