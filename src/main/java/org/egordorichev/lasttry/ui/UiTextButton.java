package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.FontWrapper;
import org.newdawn.slick.Font;
import org.newdawn.slick.geom.Rectangle;

public class UiTextButton extends UiComponent {
	/** Text, to be drawn on the button */
	protected String label;

	/** Font, to be used when rendering text */
	protected FontWrapper font;

	/** Label width in pixels */
	protected int labelWidth;

	public UiTextButton(Rectangle rectangle, Origin origin, String label) {
		super(rectangle, origin);

		this.label = label;
		this.setFont(Assets.font);
	}

	public UiTextButton(Rectangle rectangle, String label) {
		this(rectangle, Origin.TOP_LEFT, label);
	}

	/**
	 * Sets label font
	 * @param font new label font
	 */
	public void setFont(FontWrapper font) {
		this.font = font;
		this.labelWidth = this.font.getFont().getWidth(this.label);
	}

	@Override
	/** Renders the element */
	public void render() {
		if (this.hidden) {
			return;
		}

		super.render();

		this.font.drawString(this.label, this.getX() + (this.getWidth() - this.labelWidth) / 2,
			this.getY() + (this.getHeight() - this.font.getFont().getLineHeight()) / 2);
	}

	@Override
	/** Handles state change */
	protected void onStateChange() {
		if (this.state == State.MOUSE_IN) {

		} else if (this.state == State.NORMAL) {

		}
	}
}