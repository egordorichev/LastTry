package org.egordorichev.lasttry.world.biome.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeAnimationComponent implements Component {
	private Biome biome;
	private TextureRegion backgroundTextureRegion;
	private float alpha = 0;

	public BiomeAnimationComponent(Biome biome, TextureRegion backgroundTextureRegion) {
		this.biome = biome;
		this.backgroundTextureRegion = backgroundTextureRegion;
	}

	public void fadeIn() {
		this.alpha = (Math.min(1, this.alpha + 0.01f));
	}

	public void fadeInFast() {
		this.alpha = 1f;
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

	@Override
	public void render() {
		int i = 0;
		int w = 0;

		do {
			draw(i);
			w += this.backgroundTextureRegion.getRegionWidth();
			i++;
		} while (Globals.resolution.x > w);
	}

	public void draw(int offset) {
		float c = Globals.environment.time.isNight() ? 0.2f : 1.f;
		Graphics.batch.setColor(c, c, c, this.alpha);
		Graphics.batch.draw(this.backgroundTextureRegion, offset * this.backgroundTextureRegion.getRegionWidth(), 0);
		Graphics.batch.setColor(1, 1, 1, 1);
	}
}