package org.egordorichev.lasttry.item.modifier;

public class MagicModifier extends Modifier {

    public static final Modifier mystic = new MagicModifier(22, "Mystic", +10, 0, 0, -15, 0);
    public static final Modifier adept = new MagicModifier(23, "Adept", 0, 0, 0, -15, 0);
    public static final Modifier masterful = new MagicModifier(24, "Masterful", +15, 0, 0, -20, +5);
    public static final Modifier inept = new MagicModifier(25, "Inept", 0, 0, 0, +10, 0);
    public static final Modifier ignorant = new MagicModifier(26, "Ignorant", -10, 0, 0, +20, 0);
    public static final Modifier deranged = new MagicModifier(27, "Deranged", -10, 0, 0, 0, -10);
    public static final Modifier intense = new MagicModifier(28, "Intense", +10, 0, 0, +15, 0);
    public static final Modifier taboo = new MagicModifier(29, "Taboo", 0, +10, 0, +10, +10);
    public static final Modifier celestial = new MagicModifier(30, "Celestial", +10, -10, 0, -10, +10);
    public static final Modifier furious = new MagicModifier(31, "Furious", +15, 0, 0, +20, +15);
    public static final Modifier manic = new MagicModifier(32, "Manic", -10, +10, 0, -10, 0);
    public static final Modifier mythical = new MagicModifier(33, "Mythical", +15, +10, +5, -10, +15);

    public MagicModifier(int id, String name, int damage, int speed, int criticalStrikeChance, int manaCost, int knockback) {
        super(id, name, damage, speed, criticalStrikeChance, manaCost, 0, 0, knockback, 0, 0, 0);
    }
}