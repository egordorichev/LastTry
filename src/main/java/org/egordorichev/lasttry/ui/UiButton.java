package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.geom.Rectangle;

public class UiButton extends UiComponent {
	protected String label;
	protected int textWidth;

	public UiButton(Rectangle rectangle, String label, Origin origin) {
		super(rectangle, origin);

		this.setLabel(label);
	}

	public UiButton(Rectangle rectangle, String label) {
		this(rectangle, label, Origin.TOP_LEFT);
	}

	public UiButton(Rectangle rectangle) {
		this(rectangle, "");
	}

	@Override
	public void render() {
		if (this.hidden) {
			return;
		}


		super.render();
		LastTry.graphics.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		LastTry.graphics.drawString(this.label, this.getX() + (this.getWidth() - this.textWidth) / 2, this.getY() + (this.getHeight() - LastTry.graphics.getFont().getLineHeight()) / 2);
	}

	public void setLabel(String label) {
		this.label = label;
		this.textWidth = LastTry.graphics.getFont().getWidth(label);
	}

	@Override
	protected void onStateChange() {
		super.onStateChange();

		if (this.state == State.MOUSE_DOWN) {
			this.onClick();
		}
	}

	public void onClick() {

	}
}