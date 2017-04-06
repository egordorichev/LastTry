package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Rarity;

public class Pickaxe extends DigTool {
    public Pickaxe(short id, String name, Rarity rarity, float baseDamage, int power, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, ToolPower.pickaxe(power), useSpeed, texture);
    }

    public Pickaxe(short id, String name, float baseDamage, int power, int useSpeed, Texture texture) {
        this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
    }
}