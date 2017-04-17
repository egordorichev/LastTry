package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreaturePhysicsComponent extends PhysicsComponent {
	public CreaturePhysicsComponent() {

	}

	@Override
	public void jump() {
		((Creature) this.entity).state.set(StateComponent.State.JUMPING);
		this.velocity.y -= 10.0f;
	}

	@Override
	public void move(Direction direction) {
		this.velocity.x += (direction == Direction.LEFT) ? -1 : 1;
		this.direction = direction;

		StateComponent state = ((Creature) this.entity).state;

		if (state.get() != StateComponent.State.JUMPING
				&& state.get() != StateComponent.State.FALLING
				&& state.get() != StateComponent.State.FLYING) {

			state.set(StateComponent.State.MOVING);
		}
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		StateComponent state = ((Creature) this.entity).state;

		if (this.velocity.y > 0) {
			state.set(StateComponent.State.FALLING);
		} else if (this.velocity.y == 0 && state.get() == StateComponent.State.FALLING) {
			state.set(StateComponent.State.IDLE);
		}

		if (this.velocity.x == 0 && state.get() != StateComponent.State.IDLE &&
				state.get() != StateComponent.State.FALLING && state.get() != StateComponent.State.JUMPING) {

			state.set(StateComponent.State.IDLE);
		}
	}
}