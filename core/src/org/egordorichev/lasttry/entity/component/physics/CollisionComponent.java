package org.egordorichev.lasttry.entity.component.physics;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles collision data
 */
public class CollisionComponent extends Component {
	/**
	 * Shows, if the entity is solid
	 */
	public boolean solid = true;
	/**
	 * Shows, if entity is on the ground
	 */
	public boolean onGround = true;
	/**
	 * How fast the entity falls
	 */
	public float weight = 0.2f;
}