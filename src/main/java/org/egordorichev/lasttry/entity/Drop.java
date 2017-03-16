package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Modifier;

public class Drop {
	private int minAmount;
	private int maxAmount;
	private Item item;
	private Chance chance;

	public Drop(Item item, Chance chance, int minAmount, int maxAmount) { // TODO: one of
		this.item = item;
		this.chance = chance;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	public ItemHolder createHolder() {
		int randBound = (this.maxAmount - this.minAmount) + 1;
		int count = LastTry.random.nextInt(randBound) + this.maxAmount;
		return new ItemHolder(this.item, count, Modifier.random(this.item));
	}

	public Item getItem() {
		return this.item;
	}

	public int getMaxAmount() {
		return this.maxAmount;
	}

	public int getMinAmount() {
		return this.minAmount;
	}

	public Chance getChance() {
		return this.chance;
	}

	/**
	 * Enumeration for drop chance rarity.
	 */
	public enum Chance {
		ALWAYS(1), // 100% 1/1
		ALMOST_ALWAYS(2), // 50%
		VERY_COMMON(5), // 20%
		COMMON(20), // 5% 1/20
		UNCOMMON(50), // 2% 1/50
		VERY_UNCOMMON(100), // 1% 1/100
		RARE(200), // 0.5% 1/200
		VERY_RARE(286), // 0.35% 1/286
		EXTREMELY_RARE(500), // 0.2% 1/500
		LEGENDARY(1000); // 0.1% 1/1000

		/**
		 * The chance factor.
		 */
		private final int rate;

		private Chance(int rate) {
			this.rate = rate;
		}

		/**
		 * Check if a random number between 0 and the rate (exclusive) is 0.
		 * 
		 * @return Random chance based on rarity.
		 */
		public boolean roll() {
			return LastTry.random.nextInt(rate) == 0;
		}
	}
}
