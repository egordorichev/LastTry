package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;

public class CreatureStateComponent extends EntityComponent {
	public enum State {
		IDLE(0), MOVING(1), JUMPING(2), FALLING(3), FLYING(4), DEAD(5), ACTING(6);

		private int id;

		State(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}
	}

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
}