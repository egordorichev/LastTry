package org.egordorichev.lasttry.entity.engine.system;

/**
 * Handles one kind of action
 */
public interface System {
	/**
	 * Updates all entities with it's component
	 *
	 * @param delta
	 */
	default void update(float delta) {

	}

	/**
	 * Handles message
	 *
	 * @param message Message from the engine
	 */
	default void handleMessage(String message) {

	}
}