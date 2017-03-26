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
	
	public Biome(String name) {
		this.name = name;
	}

	public void fadeIn() {
		this.alpha = (Math.min(1, this.alpha + 0.01f));
	}

	public void fadeOut() {
		this.alpha = (Math.min(1, this.alpha - 0.01f));
	}

	public boolean fadeInIsDone() {
		return this.alpha >= 0.99f;
	}

	public boolean fadeOutIsDone() {
		return this.alpha < 0.01f;
	}
	
	public String getName() {
		return this.name;
	}

	public void renderBackground() {
		LastTry.batch.setColor(1, 1, 1, this.alpha);
		LastTry.batch.draw(this.texture, 0, 0);
		LastTry.batch.setColor(1, 1, 1, 1);
	}

	public static void preload() {

	}
}
