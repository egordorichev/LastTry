package org.egordorichev.lasttry.entity;

public class Boss extends Enemy {
	protected String name;

	public Boss(int id, String name, int maxHp, int defense, int damage) {
		super(id, maxHp, defense, damage);
		this.name = name;
	}

	@Override
	public void updateAI() {
		super.updateAI();
	}

	public String getName() {
		return this.name;
	}
}