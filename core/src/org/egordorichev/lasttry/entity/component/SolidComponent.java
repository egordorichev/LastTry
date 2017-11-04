package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Handles solid state of an entity
 */
public class SolidComponent extends Component {
	/**
	 * Solid flag
	 */
	public boolean solid;

	public SolidComponent(Entity entity) {
		super(entity);
	}
}