package org.egordorichev.lasttry.entity.component;

import com.badlogic.gdx.Input;

/**
 * Handles input
 */
public class InputComponent extends Component {
	/**
	 * Jump button
	 */
	public int jump = Input.Keys.SPACE;
	/**
	 * Move left button
	 */
	public int moveLeft = Input.Keys.S;
	/**
	 * Move right button
	 */
	public int moveRight = Input.Keys.D;
}