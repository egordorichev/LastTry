package org.egordorichev.lasttry.entity.creature;

public class CreatureStatsComponent extends CreatureComponent {
	/**
	 * Creature HP
	 */
	private int hp;
	/**
	 * Creature maximum HP
	 */
	private int maxHp;
	/**
	 * How long creature is invulnerable
	 */
	private int invulnerableTime;
	/**
	 * Creature mana
	 */
	private int mana;
	/**
	 * Creature maximum mana
	 */
	private int maxMana;
	/**
	 * Creature defense
	 */
	private int defense;
	/**
	 * Creature damage
	 */
	private int damage;

	public CreatureStatsComponent(Creature creature) {
		super(creature);
	}

	@Override
	public void update(int dt) {
		// TODO: health regen http://terraria.gamepedia.com/Health_regeneration

		if (this.invulnerableTime > 0) {
			this.invulnerableTime--;
		}
	}

	/**
	 * Sets stats
	 *
	 * @param maxHp Maximum hp
	 * @param maxMana Maximum mana
	 * @param defense Defense
	 * @param damage Damage
	 */
	public void set(int maxHp, int maxMana, int defense, int damage) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.defense = defense;
		this.damage = damage;
	}

	/**
	 * Modifies maximum HP
	 *
	 * @param amount How much HP is changed
	 * @return New HP
	 */
	public int modifyHP(int amount) {
		this.hp = Math.max(0, Math.min(this.maxHp, this.hp + amount));
		return this.hp;
	}

	/**
	 * Modifies maximum HP
	 *
	 * @param amount How much maximum HP is changed
	 * @return New maximum HP
	 */
	public int modifyMaxHP(int amount) {
		this.maxHp = Math.max(0, this.maxHp + amount);

		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}

		return this.maxHp;
	}

	/**
	 * Modifies mana
	 *
	 * @param amount How much mana is changed
	 * @return New mana
	 */
	public int modifyMana(int amount) {
		this.mana = Math.max(0, Math.min(this.maxMana, this.mana + amount));
		return this.mana;
	}

	/**
	 * Modifies maximum mana
	 *
	 * @param amount How much maximum mana is changed
	 * @return New maximum mana
	 */
	public int modifyMaxMana(int amount) {
		this.maxMana = Math.max(0, this.maxMana + amount);

		if (this.mana > this.maxMana) {
			this.mana = this.maxMana;
		}

		return this.maxMana;
	}

	/**
	 * Modifies defense
	 *
	 * @param amount How much defense is changed
	 * @return New defense
	 */
	public int modifyDefense(int amount) {
		this.defense = Math.max(0, this.defense + amount);
		return this.defense;
	}

	/**
	 * Modifies damage
	 *
	 * @param amount How much damage is changed
	 * @return New damage
	 */
	public int modifyDamage(int amount) {
		this.damage = Math.max(0, this.damage + amount);
		return this.damage;
	}

	/**
	 * @return Creature HP
	 */
	public int getHP() {
		return this.hp;
	}

	/**
	 * @return Creature maximum HP
	 */
	public int getMaxHP() {
		return this.maxHp;
	}

	/**
	 * @return Creature invulnerable time
	 */
	public int getInvulnerableTime() {
		return this.invulnerableTime;
	}

	/**
	 * Sets creature invulnerable time
	 * @param invulnerableTime New invulnerable time
	 */
	public void setInvulnerableTime(int invulnerableTime) {
		this.invulnerableTime = invulnerableTime;
	}

	/**
	 * @return Creature defense
	 */
	public int getDefense() {
		return this.defense;
	}

	/**
	 * @return Creature damage
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * @return Creature mana
	 */
	public int getMana() {
		return this.mana;
	}

	/**
	 * @return Creature maximum mana
	 */
	public int getMaxMana() {
		return this.maxMana;
	}
}