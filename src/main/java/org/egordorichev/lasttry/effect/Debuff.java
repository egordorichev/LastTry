package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Entity;
import org.newdawn.slick.Image;

import org.egordorichev.lasttry.entity.Entity;
import org.newdawn.slick.Image;

public abstract class Debuff extends Effect {
	// TODO: add buffs

	public Debuff(String name, String description, Image texture) {
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