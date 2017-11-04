package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Handles descriptions
 */
public class DescriptionComponent extends Component {
	/**
	 * Entity description
	 */
	public String description = "";

	public DescriptionComponent(Entity entity) {
		super(entity);
	}
}