package org.egordorichev.lasttry.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.entitySystem.component.LocationComponent;
import org.egordorichev.lasttry.graphics.Graphics;
import org.terasology.entitysystem.core.EntityRef;

import java.util.Optional;

public class Camera {
	private OrthographicCamera view;
	private EntityRef toFollow;

	public Camera(int width, int height) {
		view = new OrthographicCamera(width, height);
	}

	public void update(float dt) {
		if (this.toFollow != null) {
			Optional<LocationComponent> pos = this.toFollow.getComponent(LocationComponent.class);

			if(pos.isPresent()) {
				this.view.position.x = pos.get().location.x;
				this.view.position.y = pos.get().location.y;
			}
		}
	}

	public void apply() {
		Graphics.batch.setProjectionMatrix(this.view.combined);
	}

	public void follow(EntityRef toFollow) {
		this.toFollow = toFollow;
	}

	public float getX() {
		return this.view.position.x;
	}

	public float getY() {
		return this.view.position.y;
	}

	public OrthographicCamera getView() {
		return this.view;
	}
}