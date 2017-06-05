package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;

public class Weapon extends Tool {
    protected DamageType damageType;
    protected float baseDamage;
    protected boolean autoSwing;
	protected float criticalStrikeChance;

    public Weapon(String id) {
        super(id);
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