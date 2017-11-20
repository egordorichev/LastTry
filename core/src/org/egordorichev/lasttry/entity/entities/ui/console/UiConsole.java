package org.egordorichev.lasttry.entity.entities.ui.console;

import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Super useful command line debug thingy
 */
public class UiConsole extends UiElement {
	public UiConsole(Rectangle rect) {
		super(rect, ConsoleCommandsComponent.class);
	}

	/**
	 * Renders the console
	 */
	@Override
	public void renderUi() {
		PositionComponent position = this.getComponent(PositionComponent.class);

		Assets.f14.draw(Graphics.batch, "> Help!", position.x, position.y + 14);
	}
}