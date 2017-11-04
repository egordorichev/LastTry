package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Handles entity id
 */
public class IdComponent extends Component {
	/**
	 * Entity id
	 */
	public String id = "";

	public IdComponent(Entity entity) {
		super(entity);
	}
}