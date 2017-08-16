package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Creature;

/**
 * Effect handler for creature
 */
public class EffectData {
	/**
	 * Effect, that is being handled
	 */
	private Effect effect;
	/**
	 * Time, last for effect
	 */
	private int currentTime;
	/**
	 * Creature, that has the effect
	 */
	private Creature creature;
	/**
	 * Effect is finished
	 */
	private boolean done;

	public EffectData(Creature creature, Effect effect, int time) {
		this.effect = effect;
		this.creature = creature;
		this.setTime(time);
		this.effect.apply(this.creature);
	}

	/**
	 * Renders effect icon
	 *
	 * @param x Icon X
	 * @param y Icon Y
	 */
	public void render(int x, int y) {
		this.effect.render(x, y);
	}

	/**
	 * Updates effect
	 *
	 * @param dt Time, since last frame
	 * @return Effect is done
	 */
	public boolean update(int dt) {
		if (this.done) {
			return true;
		}

		this.currentTime--;
		this.effect.update(this.creature, dt);

		if (this.currentTime == 0) {
			this.done = true;
			this.effect.remove(this.creature);
			return true;
		}

		return false;
	}

	/**
	 * Sets time, last for effect
	 *
	 * @param time New time
	 */
	public void setTime(int time) {
		this.currentTime = time * 60;
	}

	/**
	 * @return Effect, that is being handled
	 */
	public Effect getEffect() {
		return this.effect;
	}

	/**
	 * @return Effect is done
	 */
	public boolean isDone() {
		return this.done;
	}
}