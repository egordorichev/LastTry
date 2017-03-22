package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Entity;

public class EffectData {
	/** Effect, witch is applied */
	private Effect effect;

	/** Time, left in effect */
	private int currentTime;

	/** Total effect time */
	private int totalTime;

	/** Entity, who receives effect */
	private Entity entity;

	/** Shows if effect is done */
	private boolean done;

	public EffectData(Entity entity, Effect effect, int time) {
		this.effect = effect;
		this.entity = entity;
		this.totalTime = time;
		this.currentTime = time;
		this.done = false;

		this.effect.apply(this.entity);
	}

	/**
	 * Updates effect and returns true, if it is done
	 * @param dt delta from previous update
	 * @return true, if it is done
	 */
	public boolean update(int dt) {
		if(this.done) {
			return true;
		}

		this.currentTime--;

		if(this.currentTime == 0) {
			this.done = true;
			this.effect.remove(this.entity);
			return true;
		}

		return false;
	}

	/**
	 * Sets effect time
	 * @param time effect time
	 */
	public void setTime(int time) {
		this.currentTime = time;
		this.totalTime = time;
	}

	/**
	 * Returns effect
	 * @return holding effect
	 */
	public Effect getEffect() {
		return this.effect;
	}
}