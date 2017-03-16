package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.entity.Stat;

import java.util.List;

public class Modifier {
	// Accessories
	// Defense

	public static final Modifier hard = new Modifier("Hard", Type.ACCESSORY, new Stat(Stat.Type.DEFENSE, +1));
	public static final Modifier guarding = new Modifier("Guarding", Type.ACCESSORY, new Stat(Stat.Type.DEFENSE, +2));
	public static final Modifier armored = new Modifier("Armored", Type.ACCESSORY, new Stat(Stat.Type.DEFENSE, +3));
	public static final Modifier warding = new Modifier("Warding", Type.ACCESSORY, new Stat(Stat.Type.DEFENSE, +4));

	// Mana

	public static final Modifier arcane = new Modifier("Arcane", Type.ACCESSORY, new Stat(Stat.Type.MANA, +20));

	// Critical Strike Chance

	public static final Modifier precise = new Modifier("Precise", Type.ACCESSORY, new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +2));
	public static final Modifier lucky = new Modifier("Lucky", Type.ACCESSORY, new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +4));

	// Damage

	public static final Modifier jagged = new Modifier("Jagged", Type.ACCESSORY, new Stat(Stat.Type.DAMAGE, +1));
	public static final Modifier spiked = new Modifier("Spiked", Type.ACCESSORY, new Stat(Stat.Type.DAMAGE, +2));
	public static final Modifier angry = new Modifier("Angry", Type.ACCESSORY, new Stat(Stat.Type.DAMAGE, +3));
	public static final Modifier menacing = new Modifier("Menacing", Type.ACCESSORY, new Stat(Stat.Type.DAMAGE, +4));

	// Movement Speed

	public static final Modifier brisk = new Modifier("Brisk", Type.ACCESSORY, new Stat(Stat.Type.MOVEMENT_SPEED, +1));
	public static final Modifier fleeting = new Modifier("Fleeting", Type.ACCESSORY, new Stat(Stat.Type.MOVEMENT_SPEED, +2));
	public static final Modifier hasty = new Modifier("Hasty", Type.ACCESSORY, new Stat(Stat.Type.MOVEMENT_SPEED, +3));
	public static final Modifier quick = new Modifier("Quick", Type.ACCESSORY, new Stat(Stat.Type.MOVEMENT_SPEED, +4));

	// Melee Speed

	public static final Modifier wild = new Modifier("Wild", Type.ACCESSORY, new Stat(Stat.Type.MELEE_SPEED, +1));
	public static final Modifier rash = new Modifier("Rash", Type.ACCESSORY, new Stat(Stat.Type.MELEE_SPEED, +2));
	public static final Modifier intrepid = new Modifier("Intrepid", Type.ACCESSORY, new Stat(Stat.Type.MELEE_SPEED, +3));
	public static final Modifier violent = new Modifier("Violent", Type.ACCESSORY, new Stat(Stat.Type.MELEE_SPEED, +4));

	// Universal

	public static final Modifier keen = new Modifier("Keen", Type.UNIVERSAL, new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +3));
	public static final Modifier superior = new Modifier("Superior", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, +10),
		new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +3), new Stat(Stat.Type.KNOCKBACK, +10));

	public static final Modifier forceful = new Modifier("Forceful", Type.UNIVERSAL, new Stat(Stat.Type.KNOCKBACK, +15));
	public static final Modifier broken = new Modifier("Broken", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, -30),
		new Stat(Stat.Type.KNOCKBACK, -20));

	public static final Modifier damaged = new Modifier("Damaged", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, -15));
	public static final Modifier shoddy = new Modifier("Shoddy", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, -10),
		new Stat(Stat.Type.KNOCKBACK, -15));

	public static final Modifier hurtful = new Modifier("Hurtful", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, +10));
	public static final Modifier strong = new Modifier("Strong", Type.UNIVERSAL, new Stat(Stat.Type.KNOCKBACK, +15));
	public static final Modifier unpleasant = new Modifier("Unpleasant", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, +5),
		new Stat(Stat.Type.KNOCKBACK, +15));

	public static final Modifier weak = new Modifier("Weak", Type.UNIVERSAL, new Stat(Stat.Type.KNOCKBACK, -10));
	public static final Modifier ruthless = new Modifier("Ruthless", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, +18),
		new Stat(Stat.Type.KNOCKBACK, -10));

	public static final Modifier demonic = new Modifier("Demonic", Type.UNIVERSAL, new Stat(Stat.Type.DAMAGE, +15),
			new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +5));

	public static final Modifier zealous = new Modifier("Zealous", Type.UNIVERSAL, new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +5));

	// Common

	public static final Modifier quick = new Modifier("Quick", Type.COMMON, new Stat(Stat.Type.SPEED, +10));
	public static final Modifier deady = new Modifier("Deadly", Type.COMMON, new Stat(Stat.Type.DAMAGE, +10),
		new Stat(Stat.Type.SPEED, +10));

	public static final Modifier aglie = new Modifier("Aglie", Type.COMMON, new Stat(Stat.Type.SPEED, +10),
		new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +3));

	public static final Modifier nimble = new Modifier("Nimble", Type.COMMON, new Stat(Stat.Type.SPEED, +5));
	public static final Modifier murderous = new Modifier("Murderous", Type.COMMON, new Stat(Stat.Type.DAMAGE, +7),
		new Stat(Stat.Type.SPEED, +6), new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +3));

	public static final Modifier slow = new Modifier("Slow", Type.COMMON, new Stat(Stat.Type.SPEED, -15));
	public static final Modifier sluggish = new Modifier("Sluggish", Type.COMMON, new Stat(Stat.Type.SPEED, -20));
	public static final Modifier lazy = new Modifier("Lazy", Type.COMMON, new Stat(Stat.Type.SPEED, -8));
	public static final Modifier annoying = new Modifier("Annoying", Type.COMMON, new Stat(Stat.Type.DAMAGE, -20),
		new Stat(Stat.Type.SPEED, -15));

	// Melee

	public static final Modifier large = new Modifier("Large", Type.MELEE, new Stat(Stat.Type.SIZE, +12));
	public static final Modifier massive = new Modifier("Massive", Type.MELEE, new Stat(Stat.Type.SIZE, +18));
	public static final Modifier dangerous = new Modifier("Dangerous", Type.MELEE, new Stat(Stat.Type.DAMAGE, +5),
		new Stat(Stat.Type.CRITICAL_STRIKE_CHANCE, +2), new Stat(Stat.Type.SIZE, +5));

	public static final Modifier savage = new Modifier("Savage", Type.MELEE, new Stat(Stat.Type.DAMAGE, +10),
		new Stat(Stat.Type.SIZE, 10), new Stat(Stat.Type.KNOCKBACK, +10));

	

	public enum Type {
		ACCESSORY,
		UNIVERSAL,
		COMMON,
		MELEE,
		RANGED,
		MAGIC
	}

	protected String name;
	protected Type type;
	protected Stat[] stats;
	protected float value;

	public Modifier(String name, Type type, Stat... stats) {
		this.name = name;
		this.type = type;
		this.stats = stats;
		this.value = value;
	}

	public void apply(Player player) {
		// TODO
	}

	public void remove(Player player) {
		// TODO
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

	public Stat[] getStats() {
		return this.stats;
	}

	public float getValue() {
		return this.value;
	}
}