package org.egordorichev.lasttry.item.modifier;

public class RangedModifier extends Modifier {
    public static final Modifier sighted = new RangedModifier(34, "Sighted", +10, 0, +3, 0, 0);
    public static final Modifier rapid = new RangedModifier(35, "Rapid", 0, +15, 0, +10, 0);
    public static final Modifier rangedHasty = new RangedModifier(36, "Hasty", 0, +10, 0, +15, 0);
    public static final Modifier intimidating = new RangedModifier(37, "Intimidating", 0, 0, 0, +5, +15);
    public static final Modifier deadly = new RangedModifier(38, "Deadly", +10, +5, +2, +5, +5);
    public static final Modifier staunch = new RangedModifier(39, "Staunch", +10, 0, 0, 0, +15);
    public static final Modifier awful = new RangedModifier(40, "Awful", -15, 0, 0, -10, -10);
    public static final Modifier lethargic = new RangedModifier(41, "Lethargic", 0, -15, 0, -10, 0);
    public static final Modifier awkward = new RangedModifier(42, "Awkward", 0, -10, 0, 0, -20);
    public static final Modifier powerful = new RangedModifier(43, "Powerful", +15, -10, +1, 0, 0);
    public static final Modifier frenzying = new RangedModifier(44, "Frenzying", -15, +15, 0, 0, 0);
    public static final Modifier unreal = new RangedModifier(45, "Unreal", +15, +10, +5, +10, +15);

    public RangedModifier(int id, String name, int damage, int speed, int criticalStrikeChance, int velocity, int knockback) {
        super(id, name, damage, speed, criticalStrikeChance, 0, 0, velocity, knockback, 0, 0, 0);
    }
}