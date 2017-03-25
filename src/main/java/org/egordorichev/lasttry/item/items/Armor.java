package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;

public class Armor extends Item {
	public enum Slot {
		HEAD, BODY, LEGS
	}

	/**
	 * Defense, provided by this armor piece.
	 */
	protected int defense;

	/**
	 * The slot that the armor can be placed in.
	 */
	protected Slot slot;

	public Armor(short id, String name, int defense, Slot slot, Image texture) {
		super(id, name, texture);

		this.defense = defense;
		this.slot = slot;
	}

	/**
	 * Return the total defense points the armor gives.
	 * 
	 * @return armor defense
	 */
	public int getDefense() {
		return this.defense;
	}

	/**
	 * Return the slot that the armor can be placed in.
	 * 
	 * @return armor slot
	 */
	public Slot getSlot() {
		return this.slot;
	}
}
