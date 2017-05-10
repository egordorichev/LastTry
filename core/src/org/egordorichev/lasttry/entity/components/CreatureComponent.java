package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Creature;

public class CreatureComponent extends Component {
	protected Creature creature;

	public CreatureComponent(Creature creature) {
		this.setCreature(creature);
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