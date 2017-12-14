package org.egordorichev.lasttry.entity.entities.item;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles item use timing
 */
public class ItemUseComponent extends Component {
	/**
	 * How long do you use the item
	 */
	public float useTime = 0.1f;
	/**
	 * Current delay
	 */
	public float currentTime = 0.0f;
	/**
	 * Shows, if you can just hold the mouse button
	 */
	public boolean autoUse = false;
}