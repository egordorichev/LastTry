package org.egordorichev.lasttry.item.modifier;

public class RangedModifier extends Modifier {
    public static final Modifier sighted = new RangedModifier("Sighted", +10, 0, +3, 0, 0);
    public static final Modifier rapid = new RangedModifier("Rapid", 0, +15, 0, +10, 0);
    public static final Modifier rangedHasty = new RangedModifier("Hasty", 0, +10, 0, +15, 0);
    public static final Modifier intimidating = new RangedModifier("Intimidating", 0, 0, 0, +5, +15);
    public static final Modifier deadly = new RangedModifier("Deadly", +10, +5, +2, +5, +5);
    public static final Modifier staunch = new RangedModifier("Staunch", +10, 0, 0, 0, +15);
    public static final Modifier awful = new RangedModifier("Awful", -15, 0, 0, -10, -10);
    public static final Modifier lethargic = new RangedModifier("Lethargic", 0, -15, 0, -10, 0);
    public static final Modifier awkward = new RangedModifier("Awkward", 0, -10, 0, 0, -20);
    public static final Modifier powerful = new RangedModifier("Powerful", +15, -10, +1, 0, 0);
    public static final Modifier frenzying = new RangedModifier("Frenzying", -15, +15, 0, 0, 0);
    public static final Modifier unreal = new RangedModifier("Unreal", +15, +10, +5, +10, +15);

    public RangedModifier(String name, int damage, int speed, int criticalStrikeChance, int velocity, int knockback) {
        super(name, damage, speed, criticalStrikeChance, 0, 0, velocity, knockback, 0, 0, 0);
    }
}