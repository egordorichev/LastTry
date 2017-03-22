package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Entity;
import org.newdawn.slick.Image;

public abstract  class Buff extends Effect {
	public Buff(String name, Image texture) {
		super(name, texture);
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