package org.egordorichev.lasttry.entity.creature;

import org.egordorichev.lasttry.component.Component;

public class CreatureComponent implements Component {
	/**
	 * Creature, that is being served
	 */
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

	/**
	 * @return Creature, that is being served
	 */
	public Creature getCreature() {
		return this.creature;
	}

	/**
	 * Sets creature, to be served
	 *
	 * @param creature New creature
	 */
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
}