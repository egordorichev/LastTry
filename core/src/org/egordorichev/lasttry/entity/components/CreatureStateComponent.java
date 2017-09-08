package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;

public class CreatureStateComponent<T extends Entity> extends EntityComponent<T> {
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

	private State state = State.IDLE;

	public CreatureStateComponent(T entity) {
		super(entity);
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