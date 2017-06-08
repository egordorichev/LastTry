package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.biome.components.BiomeAnimationComponent;

public class Biome {
	public BiomeAnimationComponent animation;

	private String id;
	private SpawnInfo spawnInfo;
	private Vector2 biomeVector;

	public Biome(String id) {
		this.id = id;
		this.animation = new BiomeAnimationComponent(this, Assets.getTexture(this.id));
	}

	public void loadFields(JsonValue root) {
		this.spawnInfo = new SpawnInfo(root.getShort("spawnRate", (short) 700),
			root.getShort("maxSpawns", (short) 5));
		this.biomeVector = new Vector2(root.getShort("temperature", (short) 20),
			root.getShort("humidity", (short) 30));
	}

	public Vector2 getBiomeVector() {
		return this.biomeVector;
	}

	public String getID() {
		return this.id;
	}

	public int getSpawnRate() {
		return spawnInfo.spawnRate;
	}

	public int getSpawnMax() {
		return spawnInfo.spawnMax;
	}
}