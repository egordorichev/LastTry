package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.utils.JsonValue;

public class BiomeMaterials {
	private final String topSoil;
	private final String soil;
	private final String soilWall;
	private final String stone;
	private final String stoneWall;

	private BiomeMaterials(String topsoil, String soil, String soilWall, String stone, String stoneWall) {
		this.topSoil = topsoil;
		this.soil = soil;
		this.soilWall = soilWall;
		this.stone = stone;
		this.stoneWall = stoneWall;
	}

	public static BiomeMaterials read(JsonValue root) {
		String topSoil = root.getString("topSoil");
		String soil = root.getString("soil");
		String soilWall = root.getString("soilWall");
		String stone = root.getString("stone");
		String stoneWall = root.getString("stoneWall");
		return new BiomeMaterials(topSoil, soil, soilWall, stone, stoneWall);
	}

	public String getTopSoil() {
		return topSoil;
	}

	public String getSoil() {
		return soil;
	}

	public String getSoilWall() {
		return soilWall;
	}

	public String getStone() {
		return stone;
	}

	public String getStoneWall() {
		return stoneWall;
	}
}
