package org.egordorichev.lasttry.entity.engine;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.TargetComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.*;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.world.WorldIO;

import java.util.ArrayList;

/**
 * Handles systems
 */
public class Engine {
	/**
	 * All systems in the engine
	 */
	private static ArrayList<System> systems = new ArrayList<>();
	/**
	 * All entities in the game
	 */
	private static ArrayList<Entity> entities = new ArrayList<>();


	/**
	 * Adds all needed systems
	 */
	public static void init() {
		addSystem(new CameraSystem());
		addSystem(new SaveSystem());
		addSystem(new AnimationSystem());
		addSystem(new InputSystem());
		addSystem(new MovementSystem());

		Entity camera = new Camera();
		TargetComponent target = camera.getComponent(TargetComponent.class);
		CameraComponent cam = camera.getComponent(CameraComponent.class);

		addEntity(camera);
		addEntity(WorldIO.load("test", "forest"));

		Creature player = Assets.creatures.create("lt:player");
		PositionComponent position = player.getComponent(PositionComponent.class);
		SizeComponent size = player.getComponent(SizeComponent.class);

		position.x = 1256;
		position.y = 2256;

		target.target = player;

		cam.camera.position.x = position.x + size.width / 2;
		cam.camera.position.y = position.y + size.height / 2;

		addEntity(player);
	}

	/**
	 * Sends a message, that any system can handle
	 *
	 * @param message Message, to send
	 */
	public static void sendMessage(String message) {
		for (System system : systems) {
			system.handleMessage(message);
		}
	}

	/**
	 * Updates all systems
	 *
	 * @param delta Time, since the last frame
	 */
	public static void update(float delta) {
		for (System system : systems) {
			system.update(delta);
		}
	}

	/**
	 * Adds a system to the engine
	 *
	 * @param system System to add
	 */
	public static void addSystem(System system) {
		systems.add(system);
	}

	/**
	 * Returns all entities with given components
	 *
	 * @param types Component types
	 * @return Entities list
	 */
	public static ArrayList<Entity> getEntitiesFor(Class<? extends Component>... types) {
		ArrayList<Entity> list = new ArrayList<>();

		for (Entity entity : entities) {
			boolean has = true;

			for (Class<? extends Component> type : types) {
				if (!entity.hasComponent(type)) {
					has = false;
					break;
				}
			}

			if (has) {
				list.add(entity);
			}
		}

		return list;
	}

	/**
	 * Adds an entity
	 *
	 * @param entity Entity to add
	 */
	public static void addEntity(Entity entity) {
		entities.add(entity);
		Engine.sendMessage("entity_added");
	}

	/**
	 * Removes an entity
	 *
	 * @param entity Entity to remove
	 */
	public static void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	/**
	 * @return All entities
	 */
	public static ArrayList<Entity> getEntities() {
		return entities;
	}
}