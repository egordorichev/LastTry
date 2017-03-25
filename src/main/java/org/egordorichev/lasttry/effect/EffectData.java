package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Entity;

public class EffectData {
	/** Effect witch is applied */
	private Effect effect;

	/** Time left in effect */
	private int currentTime;

	/** Total effect time */
	private int totalTime;

	/** Entity who receives effect */
	private Entity entity;

	/** Shows if effect is done */
	private boolean done;

	public EffectData(Entity entity, Effect effect, int time) {
		this.effect = effect;
		this.entity = entity;
		this.setTime(time);
		this.effect.apply(this.entity);
	}

	/**
	 * Renders effect icon at given position.
	 * 
	 * @param x
	 *            X-position
	 * @param y
	 *            Y-position
	 */
	public void render(int x, int y) {
		this.effect.render(x, y);
	}

	/**
	 * Updates effect and returns true, if it is done
	 * 
	 * @param dt
	 *            The milliseconds passed since the last update.
	 * @return true, if it is done
	 */
	public boolean update(int dt) {
		if (this.done) {
			return true;
		}

		this.currentTime--;
		this.effect.update(dt);

		if (this.currentTime == 0) {
			this.done = true;
			this.effect.remove(this.entity);
			return true;
		}

		return false;
	}

	/**
	 * Sets the time it takes for the effect to complete.
	 * 
	 * @param time
	 *            Time to complete effect.
	 */
	public void setTime(int time) {
		this.currentTime = time * 60;
		this.totalTime = time * 60;
	}

	/**
	 * Returns effect.
	 * 
	 * @return Held effect.
	 */
	public Effect getEffect() {
		return this.effect;
	}

	/**
	 * Returns true if the effect has ended.
	 * 
	 * @return true if the effect has ended.
	 */
	public boolean isDone() {
		return this.done;
	}
}