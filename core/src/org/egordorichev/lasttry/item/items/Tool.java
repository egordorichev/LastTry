package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Util;

public class Tool extends Item {
	protected boolean autoSwing;
	protected float criticalStrikeChance;
	protected float baseDamage; // All tools have melee damage
	protected ToolPower power;

	public Tool(short id, String name, Rarity rarity, float baseDamage, ToolPower power,
	        int useSpeed, Texture texture) {

		super(id, name, rarity, texture);

		this.criticalStrikeChance = 4.0f;
		this.autoSwing = false;
		this.useDelay = 0.0f;
		this.baseDamage = baseDamage;
		this.power = power;
		this.useSpeed = useSpeed;
	}

	public Tool(short id, String name, float baseDamage, ToolPower power,
	        int useSpeed, Texture texture) {

		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
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
		return this.power.pickaxe;
	}

	public int getAxePower() {
		return this.power.axe;
	}

	public int getHammerPower() {
		return this.power.axe;
	}

	@Override
	public boolean isAutoUse() {
		return this.autoSwing;
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