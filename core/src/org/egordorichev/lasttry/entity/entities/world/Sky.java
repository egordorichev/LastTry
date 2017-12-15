package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.graphics.Display;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Simple sky
 */
public class Sky extends Entity {
	/**
	 * Starting position of the sky region
	 */
	private int startX;

	public Sky() {
		super(TextureComponent.class);

		TextureComponent texture = this.getComponent(TextureComponent.class);

		texture.texture = Assets.getTexture("bg/sky");

		this.startX = texture.texture.getRegionX();
		this.setZIndex((byte) -1);
	}

	/**
	 * Renders the sky
	 */
	@Override
	public void render() {
		CameraComponent cam = CameraSystem.instance.get("main").getComponent(CameraComponent.class);

		int xStart = (int) (cam.camera.position.x - Display.WIDTH / 2);
		int yStart = (int) (cam.camera.position.y - Display.HEIGHT / 2);

		TextureComponent texture = this.getComponent(TextureComponent.class);
		ClockComponent clock = World.instance.getComponent(ClockComponent.class);

		int x = (int) (Math.min(1439, clock.minute + clock.hour * 60));

		texture.texture.setRegionX(this.startX + x);
		// Render the slice O_o
		Graphics.batch.draw(texture.texture.getTexture(), xStart, yStart, 0, 0, Display.WIDTH, Display.HEIGHT, 1, 1, 0, texture.texture.getRegionX(), texture.texture.getRegionY(), 1, 	Display.HEIGHT, false, false);
	}
}