package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Rarity;

public class Axe extends DigTool {
	public Axe(short id, String name, Rarity rarity, float baseDamage, int power, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, ToolPower.axe(power), useSpeed, texture);
		this.autoSwing = true;
	}

	public Axe(short id, String name, float baseDamage, int power, int useSpeed, Texture texture) {
		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
	}
}