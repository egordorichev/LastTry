package org.egordorichev.lasttry.item.modifier;

public class MeleeModifier extends Modifier {
    public static final Modifier large = new MeleeModifier("Large", 0, 0, 0, +12, 0);
    public static final Modifier massive = new MeleeModifier("Massive", 0, 0, 0, +18, 0);
    public static final Modifier dangerous = new MeleeModifier("Dangerous", +5, 0, +2, +5, 0);
    public static final Modifier savage = new MeleeModifier("Savage", +10, 0, 0, +10, +10);
    public static final Modifier sharp = new MeleeModifier("Sharp", +15, 0, 0, 0, 0);
    public static final Modifier pointy = new MeleeModifier("Pointy", +10, 0, 0, 0, 0);
    public static final Modifier tiny = new MeleeModifier("Tiny", 0, 0, 0, -18, 0);
    public static final Modifier terrible = new MeleeModifier("Terrible", -15, 0, 0, -13, -15);
    public static final Modifier small = new MeleeModifier("Small", 0, 0, 0, -10, 0);
    public static final Modifier dull = new MeleeModifier("Dull", -15, 0, 0, 0, 0);
    public static final Modifier unhappy = new MeleeModifier("Unhappy", -0, -10, 0, -10, -10);
    public static final Modifier bulky = new MeleeModifier("Bulky", +5, -5, 0, +10, +10);
    public static final Modifier shameful = new MeleeModifier("Shameful", -10, 0, 0, +10, -20);
    public static final Modifier heavy = new MeleeModifier("Heavy", 0, -10, 0, 0, +15);
    public static final Modifier light = new MeleeModifier("Light", 0, +15, 0, 0, -10);
    public static final Modifier legendary = new MeleeModifier("Legendary", +15, 10, +5, +10, +15);

    public MeleeModifier(String name, int damage, int speed, int criticalStrikeChance, int size, int knockback) {
        super(name, damage, speed, criticalStrikeChance, 0, size, 0, knockback, 0, 0, 0);
    }
}