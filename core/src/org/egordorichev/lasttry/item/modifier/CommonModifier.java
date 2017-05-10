package org.egordorichev.lasttry.item.modifier;

public class CommonModifier extends Modifier {
    public static final Modifier quick = new CommonModifier(0, "Quick", 0, +10, 0, 0);
    public static final Modifier deady = new CommonModifier(1, "Deadly", +10, +10, 0, 0);
    public static final Modifier aglie = new CommonModifier(2, "Aglie", 0, +10, +3, 0);
    public static final Modifier nimble = new CommonModifier(3, "Nimble", 0, +5, 0, 0);
    public static final Modifier murderous = new CommonModifier(4, "Murderous", +7, +6, +3, 0);
    public static final Modifier slow = new CommonModifier(5, "Slow", 0, -15, 0, 0);
    public static final Modifier sluggish = new CommonModifier(6, "Sluggish", 0, -20, 0, 0);
    public static final Modifier lazy = new CommonModifier(7, "Lazy", 0, -8, 0, 0);
    public static final Modifier annoying = new CommonModifier(8, "Annoying", -20, -15, 0, 0);

    public CommonModifier(int id, String name, int damage, int speed, int criticalStrikeChance, int knockback) {
        super(id, name, damage, speed, criticalStrikeChance, 0, 0, 0, knockback, 0, 0, 0);
    }
}
