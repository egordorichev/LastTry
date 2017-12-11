package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.TargetComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.PerfectViewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Keeps track of all UI elements
 */
public class UiSystem implements System {
	/**
	 * The main instance
	 */
	public static UiSystem instance;
	/**
	 * All in-game cameras
	 */
	private HashMap<String, Entity> elements;

	public UiSystem() {
		instance = this;
		this.elements = new HashMap<>();
	}

	/**
	 * Handles adding entities
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			ArrayList<Entity> entities = Engine.getEntitiesFor(IdComponent.class);
			this.elements.clear();

			for (Entity entity : entities) {
				this.elements.put(entity.getComponent(IdComponent.class).id, entity);
			}
		}
	}

	/**
	 * Returns ui element with that id
	 *
	 * @param id Element id
	 * @return Element with that id
	 */
	public UiElement get(String id) {
		return (UiElement) this.elements.get(id);
	}
}