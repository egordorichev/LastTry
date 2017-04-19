package org.egordorichev.lasttry.item.modifier;

public class AccessoryModifier extends Modifier {
    public static final Modifier hard = new AccessoryModifier(62, "Hard", 0, 0, 0, 0, 0, 0, +1);
    public static final Modifier guarding = new AccessoryModifier(63, "Guarding", 0, 0, 0, 0, 0, 0, +2);
    public static final Modifier armored = new AccessoryModifier(64, "Armored", 0, 0, 0, 0, 0, 0, +3);
    public static final Modifier warding = new AccessoryModifier(65, "Warding", 0, 0, 0, 0, 0, 0, +4);
    public static final Modifier arcane = new AccessoryModifier(66, "Arcane", 0, 0, 0, 0, +20, 0, 0);
    public static final Modifier precise = new AccessoryModifier(67, "Precise", 0, 0, +2, 0, 0, 0, 0);
    public static final Modifier lucky = new AccessoryModifier(68, "Lucky", 0, 0, +4, 0, 0, 0, 0);
    public static final Modifier jagged = new AccessoryModifier(69, "Jagged", +1, 0, 0, 0, 0, 0, 0);
    public static final Modifier spiked = new AccessoryModifier(70, "Spiked", +2, 0, 0, 0, 0, 0, 0);
    public static final Modifier angry = new AccessoryModifier(71, "Angry", +3, 0, 0, 0, 0, 0, 0);
    public static final Modifier menacing = new AccessoryModifier(72, "Menacing", +4, 0, 0, 0, 0, 0, 0);
    public static final Modifier brisk = new AccessoryModifier(73, "Brisk", 0, 0, 0, 0, 0, +1, 0);
    public static final Modifier fleeting = new AccessoryModifier(74, "Fleeting", 0, 0, 0, 0, 0, +2, 0);
    public static final Modifier hasty = new AccessoryModifier(75, "Hasty", 0, 0, 0, 0, 0, +3, 0);
    public static final Modifier quick = new AccessoryModifier(76, "Quick", 0, 0, 0, 0, 0, +4, 0);
    public static final Modifier wild = new AccessoryModifier(77, "Wild", 0, +1, 0, 0, 0, 0, 0);
    public static final Modifier rash = new AccessoryModifier(78, "Rash", 0, +2, 0, 0, 0, 0, 0);
    public static final Modifier intrepid = new AccessoryModifier(79, "Intrepid", 0, +3, 0, 0, 0, 0, 0);
    public static final Modifier violent = new AccessoryModifier(80, "Violent", 0, +4, 0, 0, 0, 0, 0);


    public AccessoryModifier(int id, String name, int damage, int speed, int criticalStrikeChance, int knockback, int mana, int movementSpeed, int defense) {
        super(id, name, damage, speed, criticalStrikeChance, 0, 0, 0, knockback, mana, movementSpeed, defense);
    }
}