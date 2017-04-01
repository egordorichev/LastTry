package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;

public class UiImage extends UiComponent {
	private Texture texture;

	public UiImage(Rectangle rectangle, Origin origin, Texture texture) {
		super(rectangle, origin);

		this.texture = texture;
	}

	public UiImage(Rectangle rectangle, Texture texture) {
		super(rectangle);

		this.texture = texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void render() {
		super.render();

		LastTry.batch.draw(this.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}