package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.entities.world.biome.Biome;
import org.egordorichev.lasttry.entity.entities.world.biome.BiomeGeneratorDataComponent;
import org.egordorichev.lasttry.entity.entities.world.biome.BiomeIndexComponent;
import org.egordorichev.lasttry.entity.entities.world.biome.BiomeSpawnComponent;

import java.util.Objects;

/**
 * Loads biomes
 */
public class BiomeFactory extends AssetFactory<Biome> {
	/**
	 * Parses a biome
	 *
	 * @param asset Biome to parse
	 * @return New biome
	 */
	@Override
	public Biome parse(JsonValue asset) {
		Biome biome = new Biome();

		IdComponent id = biome.getComponent(IdComponent.class);
		id.id = asset.name();

		if (asset.has("generation")) {
			BiomeGeneratorDataComponent generator = biome.getComponent(BiomeGeneratorDataComponent.class);
			JsonValue root = asset.get("generation");

			generator.soil = root.getString("soil", "lt:dirt");
			generator.soil_wall = root.getString("soil_wall", "lt:dirt_wall");
			generator.grass = root.getString("grass", "lt:grass");
			generator.grass_wall = root.getString("grass_wall", "lt:grass_wall");
		}

		if (asset.has("spawns")) {
			BiomeSpawnComponent spawns = biome.getComponent(BiomeSpawnComponent.class);
			JsonValue root = asset.get("spawns");

			spawns.maxSpawns = root.getInt("max_spawn", 1);

			if (root.has("spawn_rate")) {
				JsonValue rate = root.get("spawn_rate");

				if (rate.isNumber()) {
					spawns.spawnRate.put("any", rate.asFloat());
				} else {
					for (JsonValue event : rate) {
						spawns.spawnRate.put(
							event.name(), event.getFloat(0)
						);
					}
				}
			} else {
				spawns.spawnRate.put("any", 1.0f);
			}
		}

		if (asset.has("index")) {
			BiomeIndexComponent index = biome.getComponent(BiomeIndexComponent.class);
			JsonValue root = asset.get("index");

			for (JsonValue type : root) {
				if (Objects.equals(type.name, "any")) {
					String[] types = new String[type.size];

					for (int i = 0; i < type.size; i++) {
						types[i] = type.getString(i);
					}

					index.needed.put(types, (int) type.getFloat("count"));
				} else {
					index.needed.put(new String[] { type.name() }, (int) type.asFloat());
				}
			}
		}

		Assets.biomes.add(asset.name(), biome);

		return biome;
	}
}