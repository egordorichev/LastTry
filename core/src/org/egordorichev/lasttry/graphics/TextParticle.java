package org.egordorichev.lasttry.graphics;

import org.egordorichev.lasttry.LastTry;

public class TextParticle extends Particle {
	private String text;

	public TextParticle(String text) {
		this.text = text;
	}

	@Override
	public void render() {
		Assets.f22.draw(LastTry.batch, this.text, this.getX(), this.getY());
	}
}