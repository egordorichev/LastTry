package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.DamageType;
import org.egordorichev.lasttry.item.Rarity;

public class Weapon extends Tool {
    protected DamageType damageType;
    protected float baseDamage;
    protected boolean autoSwing;
	protected float criticalStrikeChance;

    public Weapon(short id, String name, Rarity rarity, float baseDamage, DamageType damageType, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, new ToolPower(0, 0, 0), useSpeed, texture);

        this.damageType = damageType;
    }

    public Weapon(short id, String name, float baseDamage, DamageType damageType, int useSpeed, Texture texture) {
        this(id, name, Rarity.WHITE, baseDamage, damageType, useSpeed, texture);
    }

	public DamageType getDamageType() {
        return this.damageType;
    }

    public float getBaseDamage() {
        return this.baseDamage;
    }

    public boolean isMagic() {
        return this.damageType == DamageType.MAGIC;
    }

    public boolean isMelee() {
        return this.damageType == DamageType.MELEE;
    }

    public boolean isThrowing() {
        return this.damageType == DamageType.THROWING;
    }

    public boolean isRanged() {
        return this.damageType == DamageType.RANGED;
    }

    public boolean isSummoning() {
        return this.damageType == DamageType.SUMMON;
    }

    public boolean isAutoSwing() {
        return this.autoSwing;
    }
}