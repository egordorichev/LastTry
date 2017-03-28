package org.egordorichev.lasttry.entity.player;

public class PlayerInfo {
	/** Player's name */
	public String name;

	/** Player's max hp */
	public int maxHp;

	/** Player's max mana */
	public int maxMana;

	/** Player type */
	public PlayerType type;

	/** Player info file version */
	public int version;

	public PlayerInfo(String name, int maxHp, int maxMana, PlayerType type, int version) {
		this.name = name;
		this.maxHp = maxHp;
		this.maxMana = maxMana;
		this.type = type;
		this.version = version;
	}
}