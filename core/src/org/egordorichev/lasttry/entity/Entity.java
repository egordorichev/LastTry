package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.util.log.Log;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * The base class for in-game entities
 */
public class Entity {
	/**
	 * Registered components
	 */
	protected HashMap<Class<? extends Component>, Component> components;

	public Entity(Class<? extends Component> ... components) {
		this.components = new HashMap<>();

		for (Class<? extends Component> type : components) {
			this.addComponent(type);
		}
	}

	public Entity() {
		this.components = new HashMap<>();
	}

	/**
	 * Registers a component
	 *
	 * @param component Component class to register
	 */
	public void addComponent(Class<? extends Component> ... component) {
		for (Class<? extends Component> c : component) {
			Component instance = null;

			try {
				Constructor<?> constructor = c.getConstructor();
				instance = (Component) constructor.newInstance();
				instance.setEntity(this);
				instance.init();
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to create a component " + c.getName());
			}

			this.components.put(c, instance);
		}
	}

	/**
	 * Removes a component
	 *
	 * @param component Component class to remove
	 */
	public void removeComponent(Class<? extends Component> component) {
		this.components.remove(component);
	}

	/**
	 * Removes all components from entity
	 */
	public void removeAll() {
		this.components.clear();
	}

	/**
	 * Searches for a component
	 *
	 * @param component Component class to find
	 * @return Entity has a component
	 */
	public boolean hasComponent(Class<? extends Component> component) {
		return this.components.containsKey(component);
	}

	/**
	 * Searches for a component and returns it
	 *
	 * @param component Component class to find
	 * @return Component or null
	 */
	public <T extends Component> T getComponent(Class<T> component) {
		return (T) this.components.get(component);
	}
}