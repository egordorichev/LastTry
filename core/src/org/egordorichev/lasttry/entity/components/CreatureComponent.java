package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Creature;

public class CreatureComponent extends Component {
	protected Creature creature;

	public CreatureComponent(Creature creature) {
		this.setCreature(creature);
	}

	public CreatureComponent() {
		// Unsafe!
	}

	public void render() {

	}

	public void update(int dt) {

	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

	public Creature getCreature() {
		return this.creature;
	}
}