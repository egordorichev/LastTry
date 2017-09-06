package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.biome.components.BiomeAnimationComponent;

public class Biome {
	public BiomeAnimationComponent animation;
	/**
	 * Indicates the biome is not naturally occurring and should not be
	 * accessible via the biome cache.
	 */
	public boolean ignoreCache;

	/**
	 * Biome id
	 */
	private String id;
	/**
	 * Info, about spawn max and spawn rate
	 */
	private SpawnInfo spawnInfo;
	/**
	 * Temperature / humidity level
	 */
	private Vector2 biomeVector;
	/**
	 * Biome priority (bigger - higher)
	 */
	private byte level = 0;
	/**
	 * Biome required items
	 */
	private Holder[] required;
	/**
	 * Tile and wall types used by the biome in generation.
	 */
	private BiomeMaterials materials;

	public static class Holder {
		public String[] items;
		public short count;
	}

	public Biome(String id) {
		this.id = id;
		this.animation = new BiomeAnimationComponent(this, Assets.getTexture(this.id.replace(':', '_')));
	}

	/**
	 * Loads fields from json
	 * 
	 * @param root
	 *            Biome node
	 */
	public void loadFields(JsonValue root) {
		this.spawnInfo = new SpawnInfo(root.getShort("spawnRate", (short) 700), root.getShort("maxSpawns", (short) 5));
		this.biomeVector = new Vector2(root.getShort("temperature", (short) 50), root.getShort("humidity", (short) 50));
		this.level = root.getByte("level", (byte) 0);
		this.materials = BiomeMaterials.read(root.get("materials"));
		if (root.has("ignoreCache")) {
			this.ignoreCache = root.getBoolean("ignoreCache");
		}
		if (root.has("required")) {
			JsonValue required = root.get("required");
			this.required = new Holder[required.size];

			for (int i = 0; i < this.required.length; i++) {
				JsonValue holder = required.get(i);

				this.required[i] = new Holder();

				if (holder.has("id")) {
					JsonValue ids = holder.get("id");
					this.required[i].items = new String[ids.size];

					for (int j = 0; j < ids.size; j++) {
						this.required[i].items[j] = ids.getString(j);
					}
				} else {
					this.required[i].items = new String[0];
				}

				this.required[i].count = holder.getShort("count", (short) 1);
			}
		} else {
			this.required = new Holder[0];
		}
	}

	/**
	 * @return Biome vector
	 */
	public Vector2 getBiomeVector() {
		return this.biomeVector;
	}

	/**
	 * @return Biome ID
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * @return Spawn rate in biome
	 */
	public int getSpawnRate() {
		return this.spawnInfo.spawnRate;
	}

	/**
	 * @return Max spawns in biome
	 */
	public int getSpawnMax() {
		return this.spawnInfo.spawnMax;
	}

	/**
	 * @return Biome level
	 */
	public byte getLevel() {
		return this.level;
	}

	/**
	 * @return Required items
	 */
	public Holder[] getRequired() {
		return this.required;
	}

	/**
	 * @return Materials used in generation.
	 * @return
	 */
	public BiomeMaterials getMaterials() {
		return this.materials;
	}
}