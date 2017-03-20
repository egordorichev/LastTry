package org.egordorichev.lasttry.item.modifier;

public class UniversalModifier extends Modifier {
	public static final Modifier keen = new UniversalModifier("Keen", 0, +3, 0);
	public static final Modifier superior = new UniversalModifier("Superior", +10, +3, +10);
	public static final Modifier forceful = new UniversalModifier("Forceful", 0, 0, +15);
	public static final Modifier broken = new UniversalModifier("Broken", -30, 0, -20);
	public static final Modifier damaged = new UniversalModifier("Damaged", -15, 0, 0);
	public static final Modifier shoddy = new UniversalModifier("Shoddy", -10, 0, -15);
	public static final Modifier hurtful = new UniversalModifier("Hurtful", +10, 0, 0);
	public static final Modifier strong = new UniversalModifier("Strong", 0, 0, +15);
	public static final Modifier unpleasant = new UniversalModifier("Unpleasant", +5, 0, +15);
	public static final Modifier weak = new UniversalModifier("Weak", 0, 0, -10);
	public static final Modifier ruthless = new UniversalModifier("Ruthless", +18, 0, -10);
	public static final Modifier demonic = new UniversalModifier("Demonic", +15, +5, 0);
	public static final Modifier zealous = new UniversalModifier("Zealous", 0, +5, 0);

	public UniversalModifier(String name, int damage, int criticalStrikeChance, int knockback) {
		super(name, damage, 0, criticalStrikeChance, 0, 0, 0, knockback, 0, 0, 0);
	}
}