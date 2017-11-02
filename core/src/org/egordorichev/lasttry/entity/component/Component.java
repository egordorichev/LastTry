package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Base class for all components
 */
public class Component {
	/**
	 * Entity, that has this component
	 */
	protected Entity entity;

	public Component(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return Component's entity
	 */
	public Entity getEntity() {
		return this.entity;
	}
}