package org.egordorichev.lasttry.entity.engine;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.*;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.engine.system.systems.*;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.world.Sky;
import org.egordorichev.lasttry.entity.entities.world.WorldIO;
import org.egordorichev.lasttry.util.log.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Handles systems
 */
public class Engine {
	/**
	 * All systems in the engine
	 */
	private static final ArrayList<System> systems = new ArrayList<>();
	/**
	 * All entities in the game
	 */
	private static final ArrayList<Entity> entities = new ArrayList<>();
	private static final Map<Class<?>, HashSet<Entity>> componentToEntity = new HashMap<>();

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

		uiCam.camera.position.x = uiCam.camera.viewportWidth / 2;
		uiCam.camera.position.y = uiCam.camera.viewportHeight / 2;
		uiId.id = "ui";

		addEntity(camera);
		addEntity(uiCamera);
		addEntity(WorldIO.load("test", "forest"));

		Players.players = Engine.getWithAllTypes(InventoryComponent.class);
		Creature player;

		if (Players.players.size() == 0) {
			player = Assets.creatures.create("lt:player");

			PositionComponent position = player.getComponent(PositionComponent.class);

			position.x = 1256;
			position.y = 2056;

			Engine.addEntity(player);
			Players.players.add(player);
		} else {
			Log.info("Loaded player");
			player = (Creature) Players.players.stream().findFirst().get();
		}
		Players.clientPlayer = player;

		target.target = player;

		PositionComponent position = player.getComponent(PositionComponent.class);
		SizeComponent size = player.getComponent(SizeComponent.class);

		InventoryComponent inventory = player.getComponent(InventoryComponent.class);
		inventory.inventory[0].item = Assets.items.get("lt:stone_pickaxe");
		inventory.inventory[0].count = 1;

		cam.camera.position.x = position.x + size.width / 2;
		cam.camera.position.y = position.y + size.height / 2;

		addEntity(new Sky());
	}

	private static void initSystems() {
		addSystem(new CameraSystem());
		addSystem(new SaveSystem());
		addSystem(new AnimationSystem());
		addSystem(new MovementSystem());
		addSystem(new CollisionSystem());
		addSystem(new InventorySystem());
		addSystem(new LightSystem());
		addSystem(new ClockSystem());
		addSystem(new InteractionSystem());
		addSystem(new UiSystem());
		addSystem(new AiSystem());
	}

	/**
	 * Sends a message, that any system can handle
	 *
	 * @param message
	 *            Message, to send
	 */
	public static void sendMessage(String message) {
		for (System system : systems) {
			system.handleMessage(message);
		}
	}

	/**
	 * Updates all systems
	 *
	 * @param delta
	 *            Time, since the last frame
	 */
	public static void update(float delta) {
		for (System system : systems) {
			system.update(delta);
		}
	}

	/**
	 * Adds a system to the engine
	 *
	 * @param system
	 *            System to add
	 */
	public static void addSystem(System system) {
		systems.add(system);
	}

	/**
	 * Returns all entities with all of the given components.
	 *
	 * @param types
	 *            Component types
	 * @return Entities list
	 */
	public static HashSet<Entity> getWithAllTypes(Class<? extends Component>... types) {
		HashSet<Entity> list = new HashSet<>();
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
	 * Returns all entities with any of the given components.
	 *
	 * @param types
	 *            Component types
	 * @return Entities list
	 */
	public static HashSet<Entity> getWithAnyTypes(Class<? extends Component>... types) {
		HashSet<Entity> set = new HashSet<>();
		for (Class<? extends Component> type : types) {
			HashSet<Entity> compSet = componentToEntity.get(type);
			if (compSet != null) {
				set.addAll(componentToEntity.get(type));
			}
		}
		return set;
	}

	/**
	 * Adds an entity
	 *
	 * @param entity
	 *            Entity to add
	 */
	public static void addEntity(Entity entity) {
		// Find position to insert entity at. Searches where to insert by
		// comparing entity z-indices
		int pos = Collections.binarySearch(entities, entity);
		// Adjust for correct insertion point if necessary
		if (pos < 0) {
			pos = -pos - 1;
		}
		// Insert at position
		entities.add(pos, entity);
		// Update caches
		for (Class<? extends Component> componentClass : entity.getComponents().keySet()) {
			HashSet<Entity> set = componentToEntity.getOrDefault(componentClass, new HashSet<>());
			set.add(entity);
			componentToEntity.putIfAbsent(componentClass, set);
		}
		sendMessage(SystemMessages.ENTITIES_UPDATED);
	}

	/**
	 * Removes an entity
	 *
	 * @param entity
	 *            Entity to remove
	 */
	public static void removeEntity(Entity entity) {
		entities.remove(entity);

		// Remove from caches
		for (Class<?> componentClass : entity.getComponents().keySet()) {
			HashSet<Entity> set = componentToEntity.getOrDefault(componentClass, new HashSet<>());
			set.remove(entity);
			componentToEntity.putIfAbsent(componentClass, set);
		}
		sendMessage(SystemMessages.ENTITIES_UPDATED);
	}

	/**
	 * @return All entities
	 */
	public static ArrayList<Entity> getEntities() {
		return entities;
	}
}