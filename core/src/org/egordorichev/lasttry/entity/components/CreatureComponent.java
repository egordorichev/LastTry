package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreatureComponent extends Creature {
	protected Creature creature;

	public CreatureComponent(Creature creature) {
		this.creature = creature;
	}

	public CreatureComponent() {

	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

	public Creature getEntity() {
		return this.creature;
	}
}