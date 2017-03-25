package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.Texture;

public class Biome {
	public static Biome forest = new ForestBiome();
	public static Biome desert = new DesertBiome();
	public static Biome corruption = new CorruptionBiome();
	public static Biome crimson = new CrimsonBiome();
	public static Biome corruptDesert = new CorruptDesertBiome();
	public static Biome crimsonDesert = new CrimsonDesertBiome();

	protected String name;
	protected Texture texture;
	
	public Biome(String name) {
		this.name = name;
	}

	public void fadeIn() {
		// this.texture.setAlpha(Math.min(1, this.texture.getAlpha() + 0.01f));
	}

	public void fadeOut() {
		// this.texture.setAlpha(Math.max(0, this.texture.getAlpha() - 0.01f));
	}

	public boolean fadeInIsDone() {
		// return this.texture.getAlpha() >= 0.99f;
		return true;
	}

	public boolean fadeOutIsDone() {
		// return this.texture.getAlpha() < 0.01f;
		return true;
	}
	
	public String getName() {
		return this.name;
	}

	public void renderBackground() {
		// this.texture.draw(0, 0);
	}

	public static void preload() {

	}
}
