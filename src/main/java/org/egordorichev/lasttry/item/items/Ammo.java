package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;

public class Ammo extends Item {
	public enum Type {
		BULLET,
		ARROW,
		ROCKET,
		DART,
		SOLUTION, // Used by some tools
		BAIT,
		WIRE,
		OTHER
	}
	
	/**
	 * Ammo damage
	 */
	protected int damage;
	
	/**
	 * Ammo type
	 */
	protected Type type;
	
	public Ammo(int id, String name, int damage, Type type, Image texture) {
		super(id, name, texture);
		
		this.damage = damage;
		this.type = type;
	}

	/**
	 * @return if ammo can damage
	 */
	public boolean hasDamage() {
		return this.damage > 0;
	}
	
	/**
	 * @return ammo damage
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * @return ammo type
	 */
	public Type getType() {
		return this.type;
	}
}
