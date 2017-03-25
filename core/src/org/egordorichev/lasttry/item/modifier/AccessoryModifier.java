package org.egordorichev.lasttry.item.modifier;

public class AccessoryModifier extends Modifier {
	public static final Modifier hard = new AccessoryModifier("Hard", 0, 0, 0, 0, 0, 0, +1);
	public static final Modifier guarding = new AccessoryModifier("Guarding", 0, 0, 0, 0, 0, 0, +2);
	public static final Modifier armored = new AccessoryModifier("Armored", 0, 0, 0, 0, 0, 0, +3);
	public static final Modifier warding = new AccessoryModifier("Warding", 0, 0, 0, 0, 0, 0, +4);
	public static final Modifier arcane = new AccessoryModifier("Arcane", 0, 0, 0, 0, +20, 0, 0);
	public static final Modifier precise = new AccessoryModifier("Precise", 0, 0, +2, 0, 0, 0, 0);
	public static final Modifier lucky = new AccessoryModifier("Lucky", 0, 0, +4, 0, 0, 0, 0);
	public static final Modifier jagged = new AccessoryModifier("Jagged", +1, 0, 0, 0, 0, 0, 0);
	public static final Modifier spiked = new AccessoryModifier("Spiked", +2, 0, 0, 0, 0, 0, 0);
	public static final Modifier angry = new AccessoryModifier("Angry", +3, 0, 0, 0, 0, 0, 0);
	public static final Modifier menacing = new AccessoryModifier("Menacing", +4, 0, 0, 0, 0, 0, 0);
	public static final Modifier brisk = new AccessoryModifier("Brisk", 0, 0, 0, 0, 0, +1, 0);
	public static final Modifier fleeting = new AccessoryModifier("Fleeting", 0, 0, 0, 0, 0, +2, 0);
	public static final Modifier hasty = new AccessoryModifier("Hasty", 0, 0, 0, 0, 0, +3, 0);
	public static final Modifier quick = new AccessoryModifier("Quick", 0, 0, 0, 0, 0, +4, 0);
	public static final Modifier wild = new AccessoryModifier("Wild", 0, +1, 0, 0, 0, 0, 0);
	public static final Modifier rash = new AccessoryModifier("Rash", 0, +2, 0, 0, 0, 0, 0);
	public static final Modifier intrepid = new AccessoryModifier("Intrepid", 0, +3, 0, 0, 0, 0, 0);
	public static final Modifier violent = new AccessoryModifier("Violent", 0, +4, 0, 0, 0, 0, 0);


	public AccessoryModifier(String name, int damage, int speed, int criticalStrikeChance, int knockback, int mana, int movementSpeed, int defense) {
		super(name, damage, speed, criticalStrikeChance, 0, 0, 0, knockback, mana, movementSpeed, defense);
	}
}