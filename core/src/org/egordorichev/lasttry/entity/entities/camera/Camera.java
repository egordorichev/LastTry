package org.egordorichev.lasttry.entity.entities.camera;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.TargetComponent;

/**
 * In-game camera
 */
public class Camera extends Entity {
	public Camera() {
		super(TargetComponent.class, CameraComponent.class);
	}
}