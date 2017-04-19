package org.egordorichev.lasttry.item.modifier;

public class UniversalModifier extends Modifier {
    public static final Modifier keen = new UniversalModifier(9, "Keen", 0, +3, 0);
    public static final Modifier superior = new UniversalModifier(10, "Superior", +10, +3, +10);
    public static final Modifier forceful = new UniversalModifier(11, "Forceful", 0, 0, +15);
    public static final Modifier broken = new UniversalModifier(12, "Broken", -30, 0, -20);
    public static final Modifier damaged = new UniversalModifier(13, "Damaged", -15, 0, 0);
    public static final Modifier shoddy = new UniversalModifier(14, "Shoddy", -10, 0, -15);
    public static final Modifier hurtful = new UniversalModifier(15, "Hurtful", +10, 0, 0);
    public static final Modifier strong = new UniversalModifier(16, "Strong", 0, 0, +15);
    public static final Modifier unpleasant = new UniversalModifier(17, "Unpleasant", +5, 0, +15);
    public static final Modifier weak = new UniversalModifier(18, "Weak", 0, 0, -10);
    public static final Modifier ruthless = new UniversalModifier(19, "Ruthless", +18, 0, -10);
    public static final Modifier demonic = new UniversalModifier(20, "Demonic", +15, +5, 0);
    public static final Modifier zealous = new UniversalModifier(21, "Zealous", 0, +5, 0);

    public UniversalModifier(int id, String name, int damage, int criticalStrikeChance, int knockback) {
        super(id, name, damage, 0, criticalStrikeChance, 0, 0, 0, knockback, 0, 0, 0);
    }
}