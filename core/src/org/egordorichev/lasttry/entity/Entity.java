package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.engine.Engine;
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
	protected final HashMap<Class<? extends Component>, Component> components = new HashMap<>();
	/**
	 * Entity on-screen-z coordinate
	 */
	protected byte zIndex = 0;

	@SuppressWarnings("all")
	public Entity(Class<? extends Component>... components) {
		for (Class<? extends Component> type : components) {
			this.addComponent(type);
		}
	}

	/**
	 * Renders the entity
	 */
	public void render() {

	}

	/**
	 * Renders UI
	 */
	public void renderUi() {

	}

	/**
	 * Registers a component
	 *
	 * @param component
	 *            Component class to register
	 * @return First added component
	 */
	public final Component addComponent(Class<? extends Component>... component) {
		Component first = null;

		for (Class<? extends Component> c : component) {
			Component instance = null;

			try {
				Constructor<?> constructor = c.getConstructor();
				instance = (Component) constructor.newInstance();
				instance.setEntity(this);
				instance.init();

				if (first == null) {
					first = instance;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to create a component " + c.getName());
			}

			this.components.put(c, instance);
		}

		return first;
	}

	/**
	 * Removes a component
	 *
	 * @param component
	 *            Component class to remove
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
	 * @param component
	 *            Component class to find
	 * @return Entity has a component
	 */
	public boolean hasComponent(Class<? extends Component> component) {
		return this.components.containsKey(component);
	}

	/**
	 * Searches for a component and returns it
	 *
	 * @param component
	 *            Component class to find
	 * @return Component or null
	 */
	public <T extends Component> T getComponent(Class<T> component) {
		return (T) this.components.get(component);
	}

	/**
	 * Sets entity z-index. Does not update engine's Z-indices.
	 * 
	 * @param zIndex
	 *            New z-index
	 */
	public void setZIndex(byte zIndex) {
		setZIndex(zIndex, false);
	}

	/**
	 * Set entity z-index and have the engine recalculate Z-indices after the
	 * update if desired.
	 * 
	 * @param zIndex
	 *            New z-index
	 * @param refreshZIndices
	 *            Option to recalculate engine Z-indices
	 */
	public void setZIndex(byte zIndex, boolean refreshZIndices) {
		this.zIndex = zIndex;
		if (refreshZIndices) {
			Engine.recalculateZIndices();
		}
	}

	/**
	 * @return All components
	 */
	public HashMap<Class<? extends Component>, Component> getComponents() {
		return this.components;
	}

	/**
	 * @return Entity Z-index
	 */
	public byte getZIndex() {
		return this.zIndex;
	}
}