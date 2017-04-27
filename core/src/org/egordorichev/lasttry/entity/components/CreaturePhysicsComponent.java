package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreaturePhysicsComponent extends PhysicsComponent {
	public CreaturePhysicsComponent(Creature creature) {
		super(creature);
	}

	public CreaturePhysicsComponent() {
		super();
	}

	@Override
	public void jump() {
		this.creature.state.set(CreatureStateComponent.State.JUMPING);
		this.velocity.y += 10.0f;
	}

	@Override
	public void move(Direction direction) {
		this.velocity.x += (direction == Direction.LEFT) ? -1 : 1;
		this.direction = direction;

		if (this.creature.state.get() != CreatureStateComponent.State.JUMPING
				&& this.creature.state.get() != CreatureStateComponent.State.FALLING
				&& this.creature.state.get() != CreatureStateComponent.State.FLYING) {

			this.creature.state.set(CreatureStateComponent.State.MOVING);
		}
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if (this.velocity.y > 0) {
			this.creature.state.set(CreatureStateComponent.State.FALLING);
		} else if (this.velocity.y == 0 && this.creature.state.get() == CreatureStateComponent.State.FALLING) {
			this.creature.state.set(CreatureStateComponent.State.IDLE);
		}

		if (this.velocity.x == 0 && this.creature.state.get() != CreatureStateComponent.State.IDLE
				&& this.creature.state.get() != CreatureStateComponent.State.FALLING
				&& this.creature.state.get() != CreatureStateComponent.State.JUMPING) {

			this.creature.state.set(CreatureStateComponent.State.IDLE);
		}
	}
}