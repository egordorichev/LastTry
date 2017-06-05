package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.world.biome.components.BiomeAnimationComponent;

public class Biome {
	/*
	 *TODO: Have biomes as JSON files
	 * Allow users to create custom biomes
	 */

	public static Biome forest = new ForestBiome();
	public static Biome desert = new DesertBiome();
	public static Biome corruption = new CorruptionBiome();
	public static Biome crimson = new CrimsonBiome();
	public static Biome corruptDesert = new CorruptDesertBiome();
	public static Biome crimsonDesert = new CrimsonDesertBiome();

	public BiomeAnimationComponent animation;

	protected String name;

	private SpawnInfo spawnInfo;
	private Vector2 biomeVec;

	public Biome(String name, SpawnInfo spawnInfo, TextureRegion backgroundTextureRegion, int temperature,
			int humidity) {
		this.name = name;
		this.spawnInfo = spawnInfo;
		this.biomeVec = new Vector2(temperature, humidity);
		this.animation = new BiomeAnimationComponent(this, backgroundTextureRegion);
		Globals.biomeMap.register(this);
	}

	public Vector2 getBiomeVector() {
		return biomeVec;
	}

	public String getName() {
		return this.name;
	}

	public int getSpawnRate() {
		return spawnInfo.spawnRate;
	}

	public int getSpawnMax() {
		return spawnInfo.spawnMax;
	}

	public static void preload() {

	}
}