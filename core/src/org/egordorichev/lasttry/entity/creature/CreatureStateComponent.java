package org.egordorichev.lasttry.entity.creature;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.EntityComponent;

public class CreatureStateComponent extends EntityComponent {
	/**
	 * Current state
	 */
	private State state = State.IDLE;

	public CreatureStateComponent(Entity entity) {
		super(entity);
	}

	/**
	 * Sets state
	 *
	 * @param state New state
	 */
	public void set(State state) {
		this.state = state;
	}

	/**
	 * @return Current state
	 */
	public State get() {
		return this.state;
	}

	public enum State {
		IDLE(0), MOVING(1), JUMPING(2), FALLING(3), FLYING(4), DEAD(5), ACTING(6);

		/**
		 * State ID
		 */
		private byte id;

		State(int id) {
			this.id = (byte) id;
		}

		/**
		 * @return State ID
		 */
		public int getID() {
			return this.id;
		}
	}
}