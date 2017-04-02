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

	/** Player render info (textures and colors) */
	public PlayerRenderInfo renderInfo;

	public PlayerInfo(String name, int maxHp, int maxMana, PlayerType type, int version,
			PlayerRenderInfo info) {

		this.name = name;
		this.maxHp = maxHp;
		this.maxMana = maxMana;
		this.type = type;
		this.version = version;
		this.renderInfo = info;
	}

	public PlayerInfo() {
		this.maxHp = 100;
		this.maxMana = 20;
		this.version = PlayerProvider.CURRENT_VERSION;
	}
}