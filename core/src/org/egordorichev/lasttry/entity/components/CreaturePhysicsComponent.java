package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreaturePhysicsComponent extends PhysicsComponent {
	public CreaturePhysicsComponent() {

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