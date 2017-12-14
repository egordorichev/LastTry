package org.egordorichev.lasttry.entity.entities.ui.window;

import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Represents a simple UI window
 */
public class UiWindow extends UiElement {
	public UiWindow(Rectangle rect) {
		super(rect, IdComponent.class);

		TextureComponent texture = (TextureComponent) this.addComponent(TextureComponent.class);
		texture.texture = Assets.getTexture("ui/bg");
	}

	/**
	 * Renders the window frame
	 */
	@Override
	public void renderUi() {
		this.renderNinePartTexture();
	}
}