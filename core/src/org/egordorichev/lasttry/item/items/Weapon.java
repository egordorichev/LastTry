package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.DamageType;

public class Weapon extends Tool {
	protected DamageType damageType;
	protected float baseDamage;
	protected boolean autoSwing;
	protected float criticalStrikeChance;

	public Weapon(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		this.baseDamage = root.getFloat("damage", 1);
		this.criticalStrikeChance = root.getFloat("criticalStrikeChance", 1);
		this.autoSwing = root.getBoolean("autoSwing", true);
		this.damageType = DamageType.valueOf(root.getString("damageType", "melee").toUpperCase());
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

	@Override
	public boolean canBeUsed(short x, short y) {
		return true;
	}
}