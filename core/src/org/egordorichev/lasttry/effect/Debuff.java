package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.Entity;

public abstract class Debuff extends Effect {
	// TODO: add buffs

	public Debuff(String name, String description, Texture texture) {
		super(name, description, texture);

		this.canBeRemoved = false;
	}

	/**
	 * Abstact method, called on effect apply
	 * @param entity Entity, on witch it is applied
	 */
	public abstract void apply(Entity entity);

	/**
	 * Abstact method, called on effect remove
	 * @param entity Entity, from witch it is removed
	 */
	public abstract void remove(Entity entity);
}