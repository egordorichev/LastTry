package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.biome.components.BiomeAnimationComponent;

public class Biome {
	public BiomeAnimationComponent animation;
	/**
	 * Biome id
	 */
	private String id;
	/**
	 * Info, about spawn max and spawn rate
	 */
	private SpawnInfo spawnInfo;
	/**
	 * Temperature / humidity level
	 */
	private Vector2 biomeVector;
	/**
	 * Biome priority (bigger - higher)
	 */
	private byte level = 0;

	public Biome(String id) {
		this.id = id;
		this.animation = new BiomeAnimationComponent(this, Assets.getTexture(this.id));
	}

	/**
	 * Loads fields from json
	 * @param root Biome node
	 */
	public void loadFields(JsonValue root) {
		this.spawnInfo = new SpawnInfo(root.getShort("spawnRate", (short) 700),
			root.getShort("maxSpawns", (short) 5));
		this.biomeVector = new Vector2(root.getShort("temperature", (short) 20),
			root.getShort("humidity", (short) 30));
		this.level = root.getByte("level", (byte) 0);
	}

	/**
	 * @return Biome vector
	 */
	public Vector2 getBiomeVector() {
		return this.biomeVector;
	}

	/**
	 * @return Biome ID
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * @return Spawn rate in biome
	 */
	public int getSpawnRate() {
		return this.spawnInfo.spawnRate;
	}

	/**
	 * @return Max spawns in biome
	 */
	public int getSpawnMax() {
		return this.spawnInfo.spawnMax;
	}

	/**
	 * @return Biome level
	 */
	public byte getLevel() {
		return this.level;
	}
}