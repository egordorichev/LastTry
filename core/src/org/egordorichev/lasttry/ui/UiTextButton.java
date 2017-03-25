package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class UiTextButton extends UiComponent {
	/** Text, to be drawn on the button */
	protected String label;

	/** Label width in pixels */
	protected int labelWidth;

	public UiTextButton(Rectangle rectangle, Origin origin, String label) {
		super(rectangle, origin);

		this.label = label;
	}

	public UiTextButton(Rectangle rectangle, String label) {
		this(rectangle, Origin.TOP_LEFT, label);
	}

	@Override
	/** Renders the element */
	public void render() {
		if (this.hidden) {
			return;
		}

		super.render();

		Assets.font.draw(LastTry.batch, this.label, this.getX() + (this.getWidth() - this.labelWidth) / 2,
			this.getY() + (this.getHeight() - Assets.font.getLineHeight()) / 2);
	}

	@Override
	/** Handles state change */
	protected void onStateChange() {
		// TODO: change font size
	}
}