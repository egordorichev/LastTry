package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Fonts;

public class UiTextButton extends UiTextLabel {
	public UiTextButton(Rectangle rectangle, Origin origin, String label) {
		super(rectangle, origin, label);
	}

	public UiTextButton(Rectangle rectangle, String label) {
		this(rectangle, Origin.TOP_LEFT, label);
	}
	
	/** Handles state change */
	@Override
	protected void onStateChange() {
		if (this.state == State.MOUSE_IN) {
			this.setFont(Fonts.f24);
		} else if (this.state == State.NORMAL) {
			this.setFont(Fonts.f22);
		}
	}
}
