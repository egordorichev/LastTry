package org.egordorichev.lasttry.entity.creature;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.EntityComponent;

public class CreatureStateComponent extends EntityComponent {
	private Entity entity;
	private State state = State.IDLE;
	public CreatureStateComponent(Entity entity) {
		this.entity = entity;
	}

	public void set(State state) {
		this.state = state;
	}

	public State get() {
		return this.state;
	}

	public boolean isIdle() {
		return this.state == State.IDLE;
	}

	public enum State {
		IDLE(0), MOVING(1), JUMPING(2), FALLING(3), FLYING(4), DEAD(5), ACTING(6);

		private int id;

		State(int id) {
			this.id = id;
		}

		public int getID() {
			return this.id;
		}
	}
}