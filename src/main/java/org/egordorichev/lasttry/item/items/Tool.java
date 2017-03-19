package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.newdawn.slick.Image;

public class Tool extends Item {
	protected boolean autoSwing;
	protected float criticalStrikeChance;
	protected float baseDamage; // All tools have melee damage
	protected float useDelay;

	protected int useSpeed;
	protected int pickaxePower;
	protected int axePower;
	protected int hammerPower;

	public Tool(int id, String name, Rarity rarity, float baseDamage, int pickaxePower,
			int axePower, int hammerPower, int useSpeed, Image texture) {

		super(id, name, rarity, texture);

		this.criticalStrikeChance = 4.0f;
		this.autoSwing = false;
		this.useDelay = 0.0f;
		this.baseDamage = baseDamage;

		this.pickaxePower = pickaxePower;
		this.axePower = axePower;
		this.hammerPower = hammerPower;
		this.useSpeed = useSpeed;
	}

	public Tool(int id, String name, float baseDamage, int pickaxePower,
			int axePower, int hammerPower, int useSpeed, Image texture) {

		this(id, name, Rarity.WHITE, baseDamage, pickaxePower, axePower, hammerPower, useSpeed, texture);
	}

	@Override
	public boolean use() {
		return false;
	}

	public int getPickaxePower() {
		return this.pickaxePower;
	}

	public int getAxePower() {
		return this.axePower;
	}

	public int getHammerPower() {
		return this.hammerPower;
	}

	public boolean isAutoSwing() {
		return this.autoSwing;
	}

	public boolean isReady() {
		return Math.abs(0.0f - this.useDelay) < 0.05f;
	}

	public float getCriticalStrikeChance() {
		return this.criticalStrikeChance;
	}

	public float getBaseDamage() {
		return this.baseDamage;
	}

	public Rarity getRarity() {
		return this.rarity;
	}
}