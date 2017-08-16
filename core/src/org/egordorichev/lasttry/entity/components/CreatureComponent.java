package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Creature;

public class CreatureComponent implements Component {
	protected Creature creature;

	public CreatureComponent(Creature creature) {
		this.setCreature(creature);
	}

	@Override
	public void render() {

	}
	@Override
	public void update(int dt) {

	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

	public Creature getCreature() {
		return this.creature;
	}
}