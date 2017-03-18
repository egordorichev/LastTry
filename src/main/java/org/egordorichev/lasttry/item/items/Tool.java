package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;

public class Tool extends Item {
	public enum Rarity {
		GRAY,
		WHITE,
		BLUE,
		GREEN,
		ORANGE,
		LIGHT_RED,
		PINK,
		LIGHT_PURPLE,
		LIME,
		YELLOW,
		CYAN,
		RED,
		PURPLE,
		EXPERT,
		AMBER
	}

	protected boolean autoSwing;
	protected float speed;
	protected float criticalStrikeChance;
	protected float baseDamage;
	protected float useDelay;
	protected Rarity rarity;

	public Tool(int id, String name, Rarity rarity, float baseDamage, Image texture) {
		super(id, name, texture);

		this.rarity = rarity;
		this.criticalStrikeChance = 4.0f;
		this.autoSwing = false;
		this.useDelay = 0.0f;
		this.baseDamage = baseDamage;
	}

	public Tool(int id, String name, float baseDamage, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, texture);
	}

	@Override
	public void use() {
		if(this.isReady()) {

		}
	}

	public void render(int x, int y) {

	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public boolean isAutoSwing() {
		return this.autoSwing;
	}

	public boolean isReady() {
		return Math.abs(0.0f - this.useDelay) < 0.05f;
	}

	public float getSpeed() {
		return this.speed;
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