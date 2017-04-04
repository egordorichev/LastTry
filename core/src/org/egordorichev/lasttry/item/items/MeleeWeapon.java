package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.DamageType;
import org.egordorichev.lasttry.item.Rarity;

public class MeleeWeapon extends Weapon {
    public MeleeWeapon(short id, String name, Rarity rarity, float baseDamage, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, DamageType.MELEE, useSpeed, texture);
    }

    public MeleeWeapon(short id, String name, float baseDamage, int useSpeed, Texture texture) {
        this(id, name, Rarity.WHITE, baseDamage, useSpeed, texture);
    }
}