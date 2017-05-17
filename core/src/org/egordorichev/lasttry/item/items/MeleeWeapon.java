package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.DamageType;
import org.egordorichev.lasttry.item.Rarity;

public class MeleeWeapon extends Weapon {
    public MeleeWeapon(short id, String name, Rarity rarity, float baseDamage, int useSpeed, TextureRegion texture) {
        super(id, name, rarity, baseDamage, DamageType.MELEE, useSpeed, texture);
    }

    public MeleeWeapon(short id, String name, float baseDamage, int useSpeed, TextureRegion texture) {
        this(id, name, Rarity.WHITE, baseDamage, useSpeed, texture);
    }
}