package org.egordorichev.lasttry.item.modifier;

public class MeleeModifier extends Modifier {
    public static final Modifier large = new MeleeModifier(46, "Large", 0, 0, 0, +12, 0);
    public static final Modifier massive = new MeleeModifier(47, "Massive", 0, 0, 0, +18, 0);
    public static final Modifier dangerous = new MeleeModifier(48, "Dangerous", +5, 0, +2, +5, 0);
    public static final Modifier savage = new MeleeModifier(49, "Savage", +10, 0, 0, +10, +10);
    public static final Modifier sharp = new MeleeModifier(50, "Sharp", +15, 0, 0, 0, 0);
    public static final Modifier pointy = new MeleeModifier(51, "Pointy", +10, 0, 0, 0, 0);
    public static final Modifier tiny = new MeleeModifier(52, "Tiny", 0, 0, 0, -18, 0);
    public static final Modifier terrible = new MeleeModifier(53, "Terrible", -15, 0, 0, -13, -15);
    public static final Modifier small = new MeleeModifier(54, "Small", 0, 0, 0, -10, 0);
    public static final Modifier dull = new MeleeModifier(55, "Dull", -15, 0, 0, 0, 0);
    public static final Modifier unhappy = new MeleeModifier(56, "Unhappy", -0, -10, 0, -10, -10);
    public static final Modifier bulky = new MeleeModifier(57, "Bulky", +5, -5, 0, +10, +10);
    public static final Modifier shameful = new MeleeModifier(58, "Shameful", -10, 0, 0, +10, -20);
    public static final Modifier heavy = new MeleeModifier(59, "Heavy", 0, -10, 0, 0, +15);
    public static final Modifier light = new MeleeModifier(60, "Light", 0, +15, 0, 0, -10);
    public static final Modifier legendary = new MeleeModifier(61, "Legendary", +15, 10, +5, +10, +15);

    public MeleeModifier(int id, String name, int damage, int speed, int criticalStrikeChance, int size, int knockback) {
        super(id, name, damage, speed, criticalStrikeChance, 0, size, 0, knockback, 0, 0, 0);
    }
}