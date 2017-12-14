package org.egordorichev.lasttry.util.camera;

import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.graphics.Display;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Simple public class for manipulating cameras
 */
public class CameraHelper {
	/**
	 * @return Camera rectangle
	 */
	public static Rectangle getCameraRect() {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);

		int x = (int) (camera.camera.position.x - Display.WIDTH / 2);
		int y = (int) (camera.camera.position.y - Display.HEIGHT / 2);

		return new Rectangle(x, y, Display.WIDTH, Display.HEIGHT);
	}

	/**
	 * @return Camera rectangle in blocks
	 */
	public static Rectangle getCameraRectInBlocks() {
		Rectangle rect = getCameraRect();

		rect.x = (float) Math.floor(rect.x / Block.SIZE) - 1;
		rect.y = (float) Math.floor(rect.y / Block.SIZE) - 1;
		rect.w = (float) Math.ceil(rect.w / Block.SIZE) + 2;
		rect.h = (float) Math.ceil(rect.h / Block.SIZE) + 2;

		return rect;
	}
}