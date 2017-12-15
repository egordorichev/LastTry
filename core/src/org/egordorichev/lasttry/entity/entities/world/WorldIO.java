package org.egordorichev.lasttry.entity.entities.world;

import com.badlogic.gdx.files.FileHandle;
import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.core.io.IO;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SaveableComponents;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.creature.SaveComponent;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.entity.entities.world.chunk.ChunkIO;
import org.egordorichev.lasttry.entity.entities.world.generator.WorldGenerator;
import org.egordorichev.lasttry.util.log.Log;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class WorldIO extends IO<World> {
	/**
	 * Current save format version
	 */
	public static byte VERSION = 0;

	/**
	 * Saves world
	 *
	 * @param world World to save
	 */
	public static void write(World world) {
		String id = world.getComponent(IdComponent.class).id;
		String[] pieces = id.split(":");

		String name = pieces[0];
		String type = pieces[1];
		String root = "data/worlds/" + name + "/" + type;

		Log.info("Saving world " + name + ":" + type);

		try {
			FileWriter writer = new FileWriter(root + "/world.wld");
			HashSet<Entity> entities = Engine.getWithAllTypes(SaveComponent.class);

			writer.writeInt32(entities.size());
			entities.add(world);

			// Save entities and components
			for (Entity entity : entities) {
				if (entity != world) {
					IdComponent idComponent = entity.getComponent(IdComponent.class);
					writer.writeString(idComponent.id);
				}

				for (String saveable : SaveableComponents.list) {
					Component component = entity.getComponent(SaveableComponents.map.get(saveable));

					if (component != null) {
						component.write(writer);
					}
				}
			}

			writer.close();
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to write world " + name + ":" + type);
		}

		for (Chunk chunk : world.getComponent(ChunksComponent.class).chunks) {
			if (chunk != null) {
				ChunkIO.write(root, chunk);
			}
		}
	}

	/**
	 * Loads world with given name
	 *
	 * @param name World name
	 * @param type World type
	 * @return Loaded world
	 */
	public static World load(String name, String type) {
		Log.info("Loading world " + name + ":" + type);
		createFolders(name);

		try {
			FileReader reader = new FileReader("data/worlds/" + name + "/" + type + "/world.wld");
			World world = new World(name, type);

			int count = reader.readInt32();

			for (int i = 0; i < count + 1; i++) {
				Entity entity = null;

				if (i < count) {
					String id = reader.readString();
					entity = Assets.creatures.create(id);
				} else {
					entity = world;
				}

				for (String saveable : SaveableComponents.list) {
					Component component = entity.getComponent(SaveableComponents.map.get(saveable));

					if (component != null) {
						component.load(reader);
					}
				}

				if (entity != world) {
					Engine.addEntity(entity);
				}
			}

			reader.close();
			return world;
		} catch (FileNotFoundException exception) {
			World world = generate(name, type);
			write(world);

			return world;
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load world");
			return new World(name, type);
		}
	}

	/**
	 * Generates a new world
	 *
	 * @param name World name
	 * @param type World type
	 * @return New world
	 */
	public static World generate(String name, String type) {
		Log.info("Generating world " + name + ":" + type);
		World world = WorldGenerator.forType(name, type).generate();

		// Manipulations, if needed

		return world;
	}

	/**
	 * Creates folders for all world types
	 *
	 * @param name World hub name
	 */
	private static void createFolders(String name) {
		String root = "data/worlds/" + name;
		FileHandle dir = new FileHandle(root);

		if (!dir.exists()) {
			Log.debug("Creating save folders for world hub " + name);
			createFolder(root);

			for (String world : Worlds.names) {
				createFolder(root + "/" + world);
			}
		}
	}

	/**
	 * Creates folder
	 *
	 * @param name Folder name
	 */
	private static void createFolder(String name) {
		try {
			FileHandle dir = new FileHandle(name);
			dir.mkdirs();
		} catch (Exception exception) {
			Log.error("Failed to create folder " + name);
		}
	}
}