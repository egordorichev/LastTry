package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.Entity;

public class CreaturePhysicsComponent extends PhysicsComponent {
	private Creature creature;

	public CreaturePhysicsComponent(Creature creature) {
		super(creature);
	}

	public CreaturePhysicsComponent() {
		super();
	}

	@Override
	public void setEntity(Entity entity) {
		super.setEntity(entity);
		this.creature = (Creature) entity;
	}

	@Override
	public void jump() {
		if (this.velocity.y != 0) {
			return;
		}

		this.creature.state.set(CreatureStateComponent.State.JUMPING);
		this.velocity.y += 10f;
	}

	@Override
	public void move(Direction direction) {
		this.velocity.x += (direction == Direction.LEFT) ? -this.speed : this.speed;
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

		if (this.velocity.y < -1.6) {
			this.creature.state.set(CreatureStateComponent.State.FALLING);
		} else if (this.velocity.y == 0 && (this.creature.state.get() == CreatureStateComponent.State.FALLING || this.creature.state.get() == CreatureStateComponent.State.JUMPING)) {
			this.creature.state.set(CreatureStateComponent.State.IDLE);
		}

		if (this.velocity.x == 0 && this.creature.state.get() != CreatureStateComponent.State.IDLE
				&& this.creature.state.get() != CreatureStateComponent.State.FALLING
				&& this.creature.state.get() != CreatureStateComponent.State.JUMPING) {

			this.creature.state.set(CreatureStateComponent.State.IDLE);
		}
	}
}