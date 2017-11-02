package org.egordorichev.lasttry.entity.system;

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
	void handleMessage(String message);
}