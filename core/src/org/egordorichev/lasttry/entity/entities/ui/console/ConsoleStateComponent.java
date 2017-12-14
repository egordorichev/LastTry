package org.egordorichev.lasttry.entity.entities.ui.console;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Stores state for console
 */
public class ConsoleStateComponent extends Component {
	/**
	 * The input
	 */
	public String input = "";
	/**
	 * If the console is open
	 */
	public boolean open = false;
}