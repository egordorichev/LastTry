package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.items.Tool;
import org.egordorichev.lasttry.item.items.ToolPower;

public class Axe extends Tool {
	public Axe(short id, String name, Rarity rarity, float baseDamage, int power, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, ToolPower.axe(power), useSpeed, texture);
		this.autoSwing = true;
	}

	public Axe(short id, String name, float baseDamage, int power, int useSpeed, Texture texture) {
		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
	}

	@Override
	protected boolean onUse() {
		return false;
	}

	@Override
	public void update(int dt) {

	}

	@Override
	protected boolean onUseEnd() {
		return false;
	}
}