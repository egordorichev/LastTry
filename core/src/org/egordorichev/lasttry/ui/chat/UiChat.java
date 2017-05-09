package org.egordorichev.lasttry.ui.chat;

import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.ui.UiComponent;

public class UiChat extends UiComponent {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private boolean open = false;

	public UiChat() {
		super(new Rectangle(0, 0, WIDTH, HEIGHT), Origin.BOTTOM_LEFT);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	protected void onStateChange() {
		super.onStateChange();
	}

	public void open() {
		this.open = true;
	}

	public void close() {
		this.open = false;
	}

	public boolean isOpen() {
		return this.open;
	}
}