package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreaturePhysicsComponent extends PhysicsComponent<Creature> {
	public CreaturePhysicsComponent(Creature creature) {
		super(creature);
	}

	@Override
	public void jump() {
		if (this.velocity.y != 0) {
			return;
		}

		this.entity.state.set(CreatureStateComponent.State.JUMPING);
		this.velocity.y += 10f;
	}

	@Override
	public void move(Direction direction) {
		this.velocity.x += (direction == Direction.LEFT) ? -this.speed : this.speed;
		this.direction = direction;

		if (this.entity.state.get() != CreatureStateComponent.State.JUMPING
				&& this.entity.state.get() != CreatureStateComponent.State.FALLING
				&& this.entity.state.get() != CreatureStateComponent.State.FLYING) {

			this.entity.state.set(CreatureStateComponent.State.MOVING);
		}
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if (this.velocity.y < -1.6) {
			this.entity.state.set(CreatureStateComponent.State.FALLING);
		} else if (this.velocity.y == 0 && (this.entity.state.get() == CreatureStateComponent.State.FALLING || this.entity.state.get() == CreatureStateComponent.State.JUMPING)) {
			this.entity.state.set(CreatureStateComponent.State.IDLE);
		}

		if (this.velocity.x == 0 && this.entity.state.get() != CreatureStateComponent.State.IDLE
				&& this.entity.state.get() != CreatureStateComponent.State.FALLING
				&& this.entity.state.get() != CreatureStateComponent.State.JUMPING) {

			this.entity.state.set(CreatureStateComponent.State.IDLE);
		}
	}
}