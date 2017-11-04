package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Handles entity target
 */
public class TargetComponent extends Component {
	public Entity target;

	public TargetComponent(Entity entity) {
		super(entity);
	}
}