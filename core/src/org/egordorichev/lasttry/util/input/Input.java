package org.egordorichev.lasttry.util.input;

import com.badlogic.gdx.InputMultiplexer;

/**
 * Handles some input stuff
 */
// TODO: make us able to bind stuff to mouse and gamepad events
public class Input {
	/**
	 * Helps with binding multiple input sources
	 */
	public static InputMultiplexer multiplexer = new InputMultiplexer();
}