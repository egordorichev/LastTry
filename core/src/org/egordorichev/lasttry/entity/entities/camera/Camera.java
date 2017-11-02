package org.egordorichev.lasttry.entity.entities.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.UpdateComponent;

/**
 * In-game camera
 */
public class Camera extends Entity {
	/**
	 * The entity to follow
	 */
	private Entity target;
	/**
	 * The actual camera
	 */
	private OrthographicCamera camera;
	public Camera(Entity target) {
		if (target != null) {
			this.target = target;
		}


		this.camera = new OrthographicCamera(800, 600);
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.addComponent(UpdateComponent.class);
	}

	public Camera() {
		this(null);
	}

	/**
	 * Updates the camera
	 *
	 * @param delta Time, since the last frame
	 */
	public void update(float delta) {
		if (this.target != null) {
			PositionComponent position = (PositionComponent) this.target.getComponent(PositionComponent.class);

			if (position != null) {
				this.camera.position.set(position.getX(), position.getY(), 0);
			}
		}
	}

	/**
	 * Handles window resizing
	 *
	 * @param width Window width
	 * @param height Window height
	 */
	public void resize(int width, int height) {
		// TODO: resize the view
		this.camera.update();
	}

	/**
	 * Sets the follow target of the camera
	 *
	 * @param target Entity to follow
	 */
	public void setTarget(Entity target) {
		this.target = target;
	}

	/**
	 * Sets the view to camera position
	 */
	public void set() {
		// Globals.batch.setProjectionMatrix(this.camera.combined);
	}

	/**
	 * @return Camera X
	 */
	public int getX() {
		return (int) this.camera.position.x;
	}

	/**
	 * @return Camera Y in pixels
	 */
	public int getY() {
		return (int) this.camera.position.y;
	}
}