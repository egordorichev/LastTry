package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.world.biome.Biome;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Environment {
	/** Current biome, player is */
	private Biome currentBiome = null;

	/** Previous biome */
	private Biome lastBiome = null;

	public int[] blockCount;

	public Environment() {
		this.addBiomeChecker();

		this.blockCount = new int[ItemID.count];
	}

	/** Renders current biome */
	public void render() {
		if (this.currentBiome != null) {
			this.currentBiome.renderBackground();
		}

		if (this.lastBiome != null) {
			this.lastBiome.renderBackground();
		}
	}

	/**
	 * Updates biomes and spawns mobs
	 * @param dt delta from last update
	 */
	public void update(int dt) {
		if (this.currentBiome != null && !this.currentBiome.fadeInIsDone()) {
			this.currentBiome.fadeIn();
		}

		if (this.lastBiome != null && this.lastBiome != this.currentBiome &&
				!this.lastBiome.fadeOutIsDone()) {

			this.lastBiome.fadeOut();
		}
	}

	/** Checks in a thread current biome every second */
	private void addBiomeChecker() {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int windowWidth = LastTry.getWindowWidth();
				int windowHeight = LastTry.getWindowHeight();
				int tww = windowWidth / Block.TEX_SIZE;
				int twh = windowHeight / Block.TEX_SIZE;
				int tcx = (int) LastTry.camera.getX() / Block.TEX_SIZE;
				int tcy = (int) LastTry.camera.getY() / Block.TEX_SIZE;

				int minY = Math.max(0, tcy - 10);
				int maxY = Math.min(LastTry.world.getHeight() - 1, tcy + twh + 10);
				int minX = Math.max(0, tcx - 10);
				int maxX = Math.min(LastTry.world.getWidth() - 1, tcx + tww + 10);

				Arrays.fill(blockCount, 0);

				for (int y = minY; y < maxY; y++) {
					for (int x = minX; x < maxX; x++) {
						blockCount[LastTry.world.getBlockID(x, y)] += 1;
					}
				}

				lastBiome = currentBiome;

				if (blockCount[ItemID.ebonstoneBlock] + blockCount[ItemID.vileMushroom] >= 200) {
					currentBiome = Biome.corruption;
				} else if (blockCount[ItemID.crimstoneBlock] + blockCount[ItemID.viciousMushroom] >= 200) {
					currentBiome = Biome.crimson;
				} else if (blockCount[ItemID.ebonsandBlock] >= 1000) {
					currentBiome = Biome.corruptDesert;
				} else if (blockCount[ItemID.crimsandBlock] >= 1000) {
					currentBiome = Biome.crimsonDesert;
				} else if (blockCount[ItemID.sandBlock] >= 1000) {
					currentBiome = Biome.desert;
				} else {
					currentBiome = Biome.forest;
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public Biome getCurrentBiome() {
		return this.currentBiome;
	}
}