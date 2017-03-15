package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.Enemy;

public class Boss extends Enemy {
	protected String name;

	protected Phase[] phases;
	protected Phase currentPhase;

	public Boss(int id, String name, int maxHp) {
		super(id);
		this.name = name;
	}

	@Override
	public void updateAI() {
		super.updateAI();
	}

	public String getName() {
		return this.name;
	}

	// TODO: set when it enter the phase
	public abstract class Phase {
		private Boss boss;
		private int damage;
		private int maxHp;
		private int defense;

		public Phase(Boss boss, int maxHp, int damage, int defense) {
			this.boss = boss;
			this.maxHp = maxHp;
			this.damage = damage;
			this.defense = defense;
		}

		public void enter() {
			if (this.boss.getMaxHp() != this.maxHp) {
				this.boss.setMaxHp(this.maxHp);
				this.boss.setHp(this.maxHp);
			}

			this.boss.setDefense(this.defense);
			this.boss.setDamage(this.damage);

			this.onEnter();
		}

		public abstract void onEnter();

		public int getDamage() {
			return this.damage;
		}

		public int getDefense() {
			return this.defense;
		}
	}
}