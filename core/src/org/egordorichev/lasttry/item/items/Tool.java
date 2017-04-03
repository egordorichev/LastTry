package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;

public class Tool extends Item {
	protected boolean autoSwing;
	protected float criticalStrikeChance;
	protected float baseDamage; // All tools have melee damage
	protected float useDelay;

	protected int useSpeed;
	protected int pickaxePower;
	protected int axePower;
	protected int hammerPower;

	public Tool(short id, String name, Rarity rarity, float baseDamage, int pickaxePower,
			int axePower, int hammerPower, int useSpeed, Texture texture) {

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

	public Tool(short id, String name, float baseDamage, int pickaxePower,
			int axePower, int hammerPower, int useSpeed, Texture texture) {

		this(id, name, Rarity.WHITE, baseDamage, pickaxePower, axePower, hammerPower, useSpeed, texture);
	}

	@Override
	public boolean use() {
		if (!this.isReady()) {
			return false;
		}

		this.useDelay = this.useSpeed;
		return this.onUse();
	}

	@Override
	public void update(int dt) {
		if (this.isReady()) {
			return;
		}

		this.useDelay = Math.max(0, this.useDelay - 1);
		this.onUpdate();

		if (this.isReady()) {
			this.onUseEnd();
		}
	}

	protected boolean onUse() {
		return false;
	}

	protected void onUpdate() {

	}

	protected boolean onUseEnd() {
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
		return this.useDelay == 0;
	}

	public float getCriticalStrikeChance() {
		return this.criticalStrikeChance;
	}

	public float getBaseDamage() {
		return this.baseDamage;
	}

	protected float getCurrentAngle() {
		return 0; // TODO;
	}

	public Rarity getRarity() {
		return this.rarity;
	}
}