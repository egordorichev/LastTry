package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.biome.Biome;
import org.egordorichev.lasttry.entity.entities.world.biome.BiomeIndexComponent;
import org.egordorichev.lasttry.util.camera.CameraHelper;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Handles biome logic
 */
public class BiomeSystem implements System {
	/**
	 * Current biome
	 */
	private Biome current;
	/**
	 * Self
	 */
	public BiomeSystem instance;

	public BiomeSystem() {
		instance = this;
		this.current = Assets.biomes.get("lt:forest");

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				checkBiome();
			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	public void checkBiome() {
		HashMap<String, MutableInt> blocks = new HashMap<>();
		Rectangle screen = CameraHelper.getCameraRectInBlocks();

		for (int x = (int) screen.x; x < screen.x + screen.w; x++) {
			for (int y = (int) screen.y; y < screen.y + screen.h; y++) {
				String id = World.instance.getBlock(x, y);

				if (id != null) {
					MutableInt value = blocks.get(id);

					if (value == null) {
						blocks.put(id, new MutableInt());
					} else {
						value.increment();
					}
				}
			}
		}

		for (Map.Entry<String, Biome> pair : Assets.biomes.getAll().entrySet()) {
			BiomeIndexComponent index = pair.getValue().getComponent(BiomeIndexComponent.class);
			boolean match = true;

			for (Map.Entry<String[], Integer> types : index.needed.entrySet()) {
				boolean found = false;

				for (String type : types.getKey()) {
					if (blocks.get(type).get() >= types.getValue()) {
						found = true;
						break;
					}
				}

				if (!found) {
					match = false;
					break;
				}
			}

			if (match) {
				Log.debug("Found biome: " + pair.getValue().getComponent(IdComponent.class).id);
				this.current = pair.getValue();

				break;
			}
		}
	}

	public Biome getCurrentBiome() {
		return this.current;
	}

	/**
	 * Simple optimization for counting blocks
	 */
	private class MutableInt {
		/**
		 * The value
		 */
		private int value = 1;

		/**
		 * Adds one to current value
		 */
		public void increment() {
			this.value++;
		}

		/**
		 * @return The value
		 */
		public int get() {
			return this.value;
		}
	}
}