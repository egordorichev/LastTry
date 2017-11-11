package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.entity.Entity;

/**
 * Base class for all components
 */
public class Component {
	/**
	 * Entity, that has this component
	 */
	protected Entity entity;

	/**
	 * Inits the component
	 */
	public void init() {

	}

	/**
	 * Sets entity
	 *
	 * @param entity New entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * @return Component's entity
	 */
	public Entity getEntity() {
		return this.entity;
	}

	/**
	 * Writes component to file
	 *
	 * @param writer File, to write
	 */
	public void write(FileWriter writer) {

	}

	/**
	 * Loads component from a file
	 *
	 * @param reader File with component
	 */
	public void load(FileReader reader) {

	}
}