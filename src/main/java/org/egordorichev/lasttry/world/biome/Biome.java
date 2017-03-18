package org.egordorichev.lasttry.world.biome;

public class Biome {
	public static Biome forest = new ForestBiome();
	public static Biome desert = new ForestBiome();

	protected String name;
	// TODO: protected Image[] backgrounds;
	
	public Biome(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public static void preload() {

	}
}
