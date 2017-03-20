package org.egordorichev.lasttry.item.modifier;

public class MagicModifier extends Modifier {

	public static final Modifier mystic = new MagicModifier("Mystic", +10, 0, 0, -15, 0);
	public static final Modifier adept = new MagicModifier("Adept", 0, 0, 0, -15, 0);
	public static final Modifier masterful = new MagicModifier("Masterful", +15, 0, 0, -20, +5);
	public static final Modifier inept = new MagicModifier("Inept", 0, 0, 0, +10, 0);
	public static final Modifier ignorant = new MagicModifier("Ignorant", -10, 0, 0, +20, 0);
	public static final Modifier deranged = new MagicModifier("Deranged", -10, 0, 0, 0, -10);
	public static final Modifier intense = new MagicModifier("Intense", +10, 0, 0, +15, 0);
	public static final Modifier taboo = new MagicModifier("Taboo", 0, +10, 0, +10, +10);
	public static final Modifier celestial = new MagicModifier("Celestial", +10, -10, 0, -10, +10);
	public static final Modifier furious = new MagicModifier("Furious", +15, 0, 0, +20,  +15);
	public static final Modifier manic = new MagicModifier("Manic", -10, +10, 0, -10, 0);
	public static final Modifier mythical = new MagicModifier("Mythical", +15, +10, +5, -10, +15);

	public MagicModifier(String name, int damage, int speed, int criticalStrikeChance, int manaCost, int knockback) {
		super(name, damage, speed, criticalStrikeChance, manaCost, 0, 0, knockback, 0, 0, 0);
	}
}