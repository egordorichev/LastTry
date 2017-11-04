package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Simple position component
 */
public class PositionComponent extends Component {
	/**
	 * X coordinate
	 */
	public float x;
	/**
	 * Y coordinate
	 */
	public float y;

	public PositionComponent(Entity entity) {
		super(entity);
	}
}