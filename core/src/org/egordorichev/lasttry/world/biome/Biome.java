package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;

public class Biome {
	public static Biome forest = new ForestBiome();
	public static Biome desert = new DesertBiome();
	public static Biome corruption = new CorruptionBiome();
	public static Biome crimson = new CrimsonBiome();
	public static Biome corruptDesert = new CorruptDesertBiome();
	public static Biome crimsonDesert = new CrimsonDesertBiome();

	/** Biome name */
	protected String name;

	/** Background texture */
	protected Texture texture;

	/** Texture alpha */
	private float alpha = 0;

	/** Spawn info */
	private SpawnInfo spawnInfo;
	
	public Biome(String name, SpawnInfo spawnInfo, Texture texture) {
		this.name = name;
		this.spawnInfo = spawnInfo;
		this.texture = texture;
	}

	/** Animates background */
	public void fadeIn() {
		this.alpha = (Math.min(1, this.alpha + 0.01f));
	}

	/** Animates background */
	public void fadeOut() {
		this.alpha = (Math.min(1, this.alpha - 0.01f));
	}

	/**
	 * Shows, if animation is done
	 * @return animation is done
	 */
	public boolean fadeInIsDone() {
		return this.alpha >= 0.99f;
	}

	/**
	 * Shows, if animation is done
	 * @return animation is done
	 */
	public boolean fadeOutIsDone() {
		return this.alpha < 0.01f;
	}

	/**
	 * Returns biome name
	 * @return biome name
	 */
	public String getName() {
		return this.name;
	}

	/** Renders backgrounds */
	public void renderBackground() {
		LastTry.batch.setColor(1, 1, 1, this.alpha);
		LastTry.batch.draw(this.texture, 0, 0);
		LastTry.batch.setColor(1, 1, 1, 1);
	}

	public static void preload() {

	}
}
