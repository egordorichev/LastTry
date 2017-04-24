package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Animation;

public class CreatureGraphicsComponent extends GraphicsComponent {
	private Animation[] animations;
	private Creature creature;

	public CreatureGraphicsComponent(Creature creature) {
		this.creature = creature;

		int size = CreatureStateComponent.State.values().length;

		this.animations = new Animation[size];

		for (int i = 0; i < size; i++) {
			this.animations[i] = new Animation(true);
		}
	}

	@Override
	public void render() {
		this.animations[this.creature.state.get().getID()].render(this.creature.physics.getX(), this.creature.physics.getY());
	}
}