package org.egordorichev.lasttry.entity.entities.creature.player;

import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;

/**
 * The player
 */
public class Player extends Creature {
	/**
	 * Static instance
	 */
	public static Player instance;

	public Player() {
		super(InputComponent.class);
		instance = this;
	}
}