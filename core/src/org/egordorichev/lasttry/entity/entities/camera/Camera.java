package org.egordorichev.lasttry.entity.entities.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;

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

		this.camera = new OrthographicCamera();
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
		Globals.batch.setProjectionMatrix(this.camera.combined);
	}
}