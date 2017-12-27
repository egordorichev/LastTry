package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.biome.Biome;

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

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				checkBiome();
			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	public void checkBiome() {

	}

	public Biome getCurrentBiome() {
		return this.current;
	}
}