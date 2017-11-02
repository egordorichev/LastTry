package org.egordorichev.lasttry.entity.system;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.RenderComponent;

public class UpdateSystem implements System {
	/**
	 * Updates the game
	 */
	public void update(float delta) {
		for (Entity entity : Globals.entitySystem.getEntities()) {
			RenderComponent component = (RenderComponent) entity.getComponent(RenderComponent.class);

			if (component != null) {
				component.render();
			}
		}
	}
}