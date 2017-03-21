package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;

public class Armor extends Item {
	public enum Slot {
		HEAD,
		BODY,
		LEGS
	}
	
	/**
	 * Defense, provided by this armor piece
	 */
	protected int defense;
	
	/**
	 * In witch slot this armor can be placed
	 */
	protected Slot slot;
	
	public Armor(int id, String name, int defense, Slot slot, Image texture) {
		super(id, name, texture);
	
		this.defense = defense;
		this.slot = slot;
	}
	
	/**
	 * @return armor defense
	 */
	public int getDefense() {
		return this.defense;
	}
	
	/**
	 * @return armor slot
	 */
	public Slot getSlot() {
		return this.slot;
	}
}
