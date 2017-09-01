package org.egordorichev.lasttry.world.components;

import java.awt.Point;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.World;

public class WorldLightingComponent implements Component {
	public static final int MAX_LIGHT = 16;
	private Point lastUpdate;
	private final World world;

	public WorldLightingComponent(World world) {
		this.world = world;
	}

	public float get(int x, int y) {
		if (LastTry.noLight) {
			return 1f;
		}
		// TODO: Clean this up, but waaaayyy better than what used to be here.
		float avg = 0;
		int sampleSize = 6;
		int p = sampleSize * 2;
		float m = MAX_LIGHT;
		float ss4 = (p * p);
		for (int i = -sampleSize; i < sampleSize; i++) {
			for (int k = -sampleSize; k < sampleSize; k++) {
				avg += (world.blocks.getLight(x + i, y + k) / (ss4));
			}
		}
		return avg / m;
	}

	/**
	 * Update blocks only near the player. Called only when lighting near the
	 * player needs to be updated.
	 */
	@Override
	public void update(int dt) {
		// TODO: Only update near player
		int px = Globals.getPlayer().physics.getGridX();
		int py = Globals.getPlayer().physics.getGridY();

		lastUpdate = new Point(px, py);

		// Range in chunks to load
		int range = 30;
		for (int y = py - range; y < py + range; y++) {
			for (int x = px - range; x < px + range; x++) {
				boolean hasBlock = world.blocks.getID(x, y) == null;
				boolean canSeeSky = y >= world.getHighest(x);
				byte light = canSeeSky ? MAX_LIGHT : (byte) 0;
				world.blocks.setLight(x, y, light);
			}
		}
	}

	public boolean distanceCheck(int x, int y) {
		if (lastUpdate == null) return true;
		Point temp = new Point(x, y);
		return temp.distance(lastUpdate) < 30;
	}
}
