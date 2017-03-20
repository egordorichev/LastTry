package org.egordorichev.lasttry.item.modifier;

public class CommonModifier extends Modifier {
	public static final Modifier quick = new CommonModifier("Quick", 0, +10, 0, 0);
	public static final Modifier deady = new CommonModifier("Deadly", +10, +10, 0, 0);
	public static final Modifier aglie = new CommonModifier("Aglie", 0, +10, +3, 0);
	public static final Modifier nimble = new CommonModifier("Nimble", 0, +5, 0, 0);
	public static final Modifier murderous = new CommonModifier("Murderous", +7, +6, +3, 0);
	public static final Modifier slow = new CommonModifier("Slow", 0, -15, 0, 0);
	public static final Modifier sluggish = new CommonModifier("Sluggish", 0, -20, 0, 0);
	public static final Modifier lazy = new CommonModifier("Lazy", 0, -8, 0, 0);
	public static final Modifier annoying = new CommonModifier("Annoying", -20, -15, 0, 0);

	public CommonModifier(String name, int damage, int speed, int criticalStrikeChance, int knockback) {
		super(name, damage, speed, criticalStrikeChance, 0, 0, 0, knockback, 0, 0, 0);
	}
}
