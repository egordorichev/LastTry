package org.egordorichev.lasttry.entity.engine;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.*;
import org.egordorichev.lasttry.entity.engine.system.systems.*;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.world.WorldIO;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.log.Log;

import java.util.ArrayList;
import java.util.Comparator;

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
		initSystems();

		Entity camera = new Camera();
		TargetComponent target = camera.getComponent(TargetComponent.class);
		CameraComponent cam = camera.getComponent(CameraComponent.class);
		IdComponent id = camera.getComponent(IdComponent.class);

		id.id = "main";

		Entity uiCamera = new Camera();
		CameraComponent uiCam = uiCamera.getComponent(CameraComponent.class);
		IdComponent uiId = uiCamera.getComponent(IdComponent.class);

		uiCam.camera.position.x = Gdx.graphics.getWidth() / 2;
		uiCam.camera.position.y = Gdx.graphics.getHeight() / 2;
		uiId.id = "ui";

		addEntity(camera);
		addEntity(uiCamera);
		addEntity(WorldIO.load("test", "forest"));

		ArrayList<Entity> players = Engine.getEntitiesFor(InputComponent.class);
		Creature player;

		if (players.size() == 0) {
			player = Assets.creatures.create("lt:player");

			PositionComponent position = player.getComponent(PositionComponent.class);

			position.x = 1256;
			position.y = 2056;

			Engine.addEntity(player);
		} else {
			Log.info("Loaded player");
			player = (Creature) players.get(0);
		}

		target.target = player;

		PositionComponent position = player.getComponent(PositionComponent.class);
		SizeComponent size = player.getComponent(SizeComponent.class);

		InventoryComponent inventory = player.getComponent(InventoryComponent.class);
		inventory.inventory[0].item = Assets.items.get("lt:stone_pickaxe");
		inventory.inventory[0].count = 1;

		cam.camera.position.x = position.x + size.width / 2;
		cam.camera.position.y = position.y + size.height / 2;
	}

	private static void initSystems() {
		addSystem(new CameraSystem());
		addSystem(new SaveSystem());
		addSystem(new AnimationSystem());
		addSystem(new InputSystem());
		addSystem(new MovementSystem());
		addSystem(new CollisionSystem());
		addSystem(new InventorySystem());
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
		Engine.recalculateZIndexes();
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
	 * Sorts entities by z-index
	 */
	public static void recalculateZIndexes() {
		entities.sort(new Comparator<Entity>() {
			@Override
			public int compare(Entity entity1, Entity entity2) {
				byte z1 = entity1.getZIndex();
				byte z2 = entity2.getZIndex();

				if (z1 > z2) {
					return 1;
				} else if (z1 < z2) {
					return -1;
				}

				return 0;
			}
		});
	}

	/**
	 * @return All entities
	 */
	public static ArrayList<Entity> getEntities() {
		return entities;
	}
}