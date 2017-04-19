package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreaturePhysicsComponent extends PhysicsComponent {
	public CreaturePhysicsComponent(Creature creature) {
		super(creature);
	}

	@Override
	public void jump() {
		this.creature.state.set(CreatureStateComponent.State.JUMPING);
		this.velocity.y -= 10.0f;
	}

	@Override
	public void move(Direction direction) {
		this.velocity.x += (direction == Direction.LEFT) ? -1 : 1;
		this.direction = direction;

		CreatureStateComponent state = ((Creature) this.entity).state;

		if (state.get() != CreatureStateComponent.State.JUMPING
				&& state.get() != CreatureStateComponent.State.FALLING
				&& state.get() != CreatureStateComponent.State.FLYING) {

			state.set(CreatureStateComponent.State.MOVING);
		}
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		CreatureStateComponent state = ((Creature) this.entity).state;

		if (this.velocity.y > 0) {
			state.set(CreatureStateComponent.State.FALLING);
		} else if (this.velocity.y == 0 && state.get() == CreatureStateComponent.State.FALLING) {
			state.set(CreatureStateComponent.State.IDLE);
		}

		if (this.velocity.x == 0 && state.get() != CreatureStateComponent.State.IDLE &&
				state.get() != CreatureStateComponent.State.FALLING && state.get() != CreatureStateComponent.State.JUMPING) {

			state.set(CreatureStateComponent.State.IDLE);
		}
	}
}