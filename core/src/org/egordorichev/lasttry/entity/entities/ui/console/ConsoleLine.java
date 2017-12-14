package org.egordorichev.lasttry.entity.entities.ui.console;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Represents one line in chat
 */
public class ConsoleLine extends Component {
	/**
	 * How long the line will be on screen
	 */
	public static float EXPIRE_TIME = 10.0f;
	/**
	 * The text
	 */
	public String line = "";
	/**
	 * How long the line is already on screen
	 */
	public float time = 0.0f;
}