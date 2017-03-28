package org.egordorichev.lasttry.entity.player;

public enum PlayerType {
	SOFTCORE("Softcore"),
	MEDIUMCORE("Mediumcore"),
	HARDCORE("Hardcore");

	private String name;

	PlayerType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}