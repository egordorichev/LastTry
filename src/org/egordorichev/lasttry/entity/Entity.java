package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Rectangle;
import org.newdawn.slick.Image;

public class Entity {
	protected int hp;
	protected int maxHp;
	protected int defense;
	protected int damage;
	protected boolean invulnerable;
	protected boolean active;
	protected boolean friendly;
	protected String name;
	protected Rectangle rect;
	protected Image image;

	public Entity(String name, boolean friendly, int maxHp, int defense, int damage) {
		this.name = name;
		this.friendly = friendly;
		this.defense = defense;
		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.active = false;
		this.invulnerable = false;
		this.rect = new Rectangle(0, 0, 32, 48);
	}

	public Entity(String name, boolean friendly) {
		this(name, friendly, 10, 0, 0);
	}

	public void render() {
		this.image.draw(this.rect.x, this.rect.y);
	}

	public void update(int dt) {

	}

	public void spawn(int x, int y) {
		this.active = true;
		this.rect.setPosition(x, y);
		this.onSpawn();
	}

	public void hit(int damage) {
		this.modifyHp(Math.max(-1, -damage + defense / 2));
	}

	public void modifyHp(int amount) {
		if(!this.invulnerable) {
			this.hp = Math.min(Math.max(0, this.hp + amount), this.maxHp);

			if(this.hp == 0) {
				this.die();
			}
		}
	}

	public void modifyMaxHp(int amount) {
		this.maxHp = Math.max(0, this.maxHp + amount);
	}

	public void modifyDefense(int amount) {
		this.defense = Math.max(0, this.defense + amount);
	}

	public void die() {
		this.active = false;
	}

	public void onSpawn() {

	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDefense() {
		return this.defense;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return this.hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public String getName() {
		return this.name;
	}
}