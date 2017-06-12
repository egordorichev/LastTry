package org.egordorichev.lasttry.entity.drop;

import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Util;

public class Drop {
	/**
	 * Minimum item amount to drop
	 */
	private int minAmount;
	/**
	 * Maximum item amount to drop
	 */
	private int maxAmount;
	/**
	 * Item to drop
	 */
	private Item item;
	/**
	 * Change to drop items
	 */
	private int chance;

	public Drop(Item item) {
		this(item, 1, 1, 1);
	}

	public Drop(Item item, int maxAmount) {
		this(item, 1, 1, maxAmount);
	}

	public Drop(Item item, int chance, int minAmount, int maxAmount) {
		this.item = item;
		this.chance = chance;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	/**
	 * Creates item holder and generates drop
	 *
	 * @return Item holder with generated drop
	 */
	public ItemHolder createHolder() {
		return new ItemHolder(this.item, Util.random(this.minAmount, this.maxAmount + 1));
	}

	/**
	 * @return Item, that can be dropped
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * @return Maximum amount of items, that can be dropped
	 */
	public int getMaxAmount() {
		return this.maxAmount;
	}

	/**
	 * @return Minimum amount of items, that can be dropped
	 */
	public int getMinAmount() {
		return this.minAmount;
	}

	/**
	 * @return Drop chance
	 */
	public int getChance() {
		return this.chance;
	}

	@Override
	public Drop clone() {
		return new Drop(this.item, this.chance, this.minAmount, this.maxAmount);
	}
}