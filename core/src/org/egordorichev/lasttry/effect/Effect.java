package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Simple effect handler
 */
public class Effect {
	/**
	 * Effect can be removed by right-click
	 */
	protected boolean canBeRemoved;
	/**
	 * Effect name
	 */
	protected String name;
	/**
	 * Effect description
	 */
	protected String description;
	/**
	 * Effect icon
	 */
	protected TextureRegion texture;

	public Effect(String name, String description, TextureRegion texture) {
		this.name = name;
		this.canBeRemoved = true;
		this.description = description;
		this.texture = texture;
	}

	/**
	 * Renders effect Icon
	 *
	 * @param x Icon X
	 * @param y Icon Y
	 */
	public void render(int x, int y) {
		Graphics.batch.draw(this.texture, x, y);
	}

	/**
	 * Called, when effect is applied to creature
	 *
	 * @param creature Creature, witch has the effect
	 */
	public void apply(Creature creature) {
		// TODO: handle
	}

	/**
	 * Called, when effect is removed from creature
	 *
	 * @param creature Creature, that had the effect
	 */
	public void remove(Creature creature) {
		// TODO: handle
	}

	/**
	 * Updates effect on creature
	 *
	 * @param creature Creature with effect
	 * @param dt       Time, since last frame
	 */
	public void update(Creature creature, int dt) {
		// TODO: handle
	}

	/**
	 * @return Effect name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Effect description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return Effect can be removed by right-click
	 */
	public boolean canBeRemoved() {
		return this.canBeRemoved;
	}

	public static Effect load(JsonValue root) {
		return null; // TODO!
	}
}