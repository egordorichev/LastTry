package org.egordorichev.lasttry.item.modifier;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Log;

public class Modifier {
    protected String name;
    protected byte id;
    protected int damage;
    protected int speed;
    protected int criticalStrikeChance;
    protected int manaCost;
    protected int size;
    protected int velocity;
    protected int knockback;
    protected int mana;
    protected int movementSpeed;
    protected int defense;

    public Modifier(int id, String name, int damage, int speed, int criticalStrikeChance, int manaCost, int size,
            int velocity, int knockback, int mana, int movementSpeed, int defense) {

    	if (Modifiers.MODIFIER_CACHE[id] != null) {
		    Log.error("Modifier with id " + id + " is already defined!");
	        LastTry.abort();
    	}

	    Modifiers.MODIFIER_CACHE[id] = this;

    	this.id = (byte) id;
        this.name = name;
        this.damage = damage;
        this.speed = speed;
        this.criticalStrikeChance = criticalStrikeChance;
        this.manaCost = manaCost;
        this.size = size;
        this.velocity = velocity;
        this.knockback = knockback;
        this.mana = mana;
        this.movementSpeed = movementSpeed;
        this.defense = defense;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getCriticalStrikeChance() {
        return this.criticalStrikeChance;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public int getSize() {
        return this.size;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public int getKnockback() {
        return this.knockback;
    }

    public int getMana() {
        return this.mana;
    }

    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    public int getDefense() {
        return this.defense;
    }

	public byte getID() {
		return this.id;
	}

	public String getName() {
        return this.name;
    }

    public static Modifier fromID(byte id) {
	    if (id == 0) {
		    return null;
	    }

	    return Modifiers.MODIFIER_CACHE[id];
    }
}