package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.Item;

import java.util.ArrayList;

public class Drop {
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

		private int random;

		private Chance(int random) {
			this.random = random;
		}

		public int getRate() {
			return random;
		}
	}

	public enum Type {
		DROP,
		CONTAINER
	}

	private int minAmount;
	private int maxAmount;
	private Item item;
	private Chance chance;
	private Type type;
	private ArrayList<Drop> drops;

	public Drop(Item item, Chance chance, int minAmount, int maxAmount) {
		this.item = item;
		this.chance = chance;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;

		this.type = Type.DROP;
	}

	public Drop() {
		this.type = Type.CONTAINER;
		this.drops = new ArrayList<>();
	}

	public void add(Drop drop) {
		if(this.type == Type.DROP) {
			this.type = Type.CONTAINER;
			this.drops = new ArrayList<>();
			this.drops.add(new Drop(this.item, this.chance, this.minAmount, this.maxAmount));
		}

		this.drops.add(drop);
	}

	/**
	 * TODO: public ArrayList<ItemHolder> random()
	 */

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
}