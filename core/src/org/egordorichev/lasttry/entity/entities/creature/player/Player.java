package org.egordorichev.lasttry.entity.entities.creature.player;

import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;

/**
 * The player
 */
public class Player extends Creature {
	public Player() {
		super(InputComponent.class);

		// InputComponent input = this.getComponent(InputComponent.class);
	}
}