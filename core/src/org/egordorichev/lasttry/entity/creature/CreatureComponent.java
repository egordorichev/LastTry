package org.egordorichev.lasttry.entity.creature;

import org.egordorichev.lasttry.component.Component;

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

	public Creature getCreature() {
		return this.creature;
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}
}