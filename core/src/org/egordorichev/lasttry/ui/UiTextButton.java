package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Fonts;

public class UiTextButton extends UiComponent {
	/** Text, to be drawn on the button */
	protected String label;

	/** Label width in pixels */
	protected int labelWidth;

	/** Current font */
	private BitmapFont font;

	public UiTextButton(Rectangle rectangle, Origin origin, String label) {
		super(rectangle, origin);

		this.label = label;

		this.setFont(Fonts.f22);
	}

	public UiTextButton(Rectangle rectangle, String label) {
		this(rectangle, Origin.TOP_LEFT, label);
	}

	/** Renders the element */
	@Override
	public void render() {
		if (this.hidden) {
			return;
		}

		super.render();

		this.font.draw(LastTry.batch, this.label, this.getX(),
			this.getY() + (this.getHeight() - this.font.getLineHeight()) / 2);
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

	/** Sets font and calculates new size */
	private void setFont(BitmapFont font) {
		this.font = font;

		GlyphLayout glyphLayout = new GlyphLayout();
		glyphLayout.setText(this.font, this.label);

		this.labelWidth = (int) glyphLayout.width;
		this.rect.width = this.labelWidth;
		this.rect.height = this.font.getLineHeight() * 1.f;
	}
}