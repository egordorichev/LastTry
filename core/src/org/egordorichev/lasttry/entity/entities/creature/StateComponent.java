package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Stores current state
 */
public class StateComponent extends Component {
	/**
	 * The state of creature
	 */
	public String state = "idle";
	/**
	 * State timer
	 */
	public int time = 0;
}