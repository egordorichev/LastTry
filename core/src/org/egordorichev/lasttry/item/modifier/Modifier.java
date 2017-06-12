package org.egordorichev.lasttry.item.modifier;

import org.egordorichev.lasttry.item.Item;

public class Modifier {
    protected String id;
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

    public Modifier(String id, int damage, int speed, int criticalStrikeChance, int manaCost, int size,
            int velocity, int knockback, int mana, int movementSpeed, int defense) {

    	this.id = id;
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

	public String getID() {
		return this.id;
	}
}