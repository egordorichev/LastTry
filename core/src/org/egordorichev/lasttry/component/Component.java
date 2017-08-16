package org.egordorichev.lasttry.component;

/** Core component */
public interface Component {
	/** Being called on render */
	default void render() {

	}

	/**
	 * Being called on update
	 * @param dt Time, past since last update
	 */
	default void update(int dt) {

	}
}