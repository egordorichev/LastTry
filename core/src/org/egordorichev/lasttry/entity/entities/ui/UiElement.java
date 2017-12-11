package org.egordorichev.lasttry.entity.entities.ui;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Simple ui element
 */
public class UiElement extends Entity {
	public UiElement(Rectangle rect, Class<? extends Component> ... components) {
		super(PositionComponent.class, SizeComponent.class);

		this.setZIndex((byte) 10);
		this.addComponent(components);

		PositionComponent position = this.getComponent(PositionComponent.class);

		position.x = rect.x;
		position.y = rect.y;

		SizeComponent size = this.getComponent(SizeComponent.class);

		size.width = rect.w;
		size.height = rect.h;
	}

	/**
	 * Renders a 9-part texture
	 * As own BG
	 */
	protected void renderNinePartTexture() {
		TextureComponent texture = this.getComponent(TextureComponent.class);

		// No texture, no work
		if (texture == null) {
			return;
		}

		PositionComponent position = this.getComponent(PositionComponent.class);
		SizeComponent size = this.getComponent(SizeComponent.class);

		// TODO
		// For hint look up this: https://docs.unity3d.com/Manual/9SliceSprites.html

		Graphics.batch.draw(texture.texture, position.x, position.y, size.width, size.height);
	}
}