package org.egordorichev.lasttry.world.components;

import java.awt.Rectangle;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;

public class WorldLightingComponent implements Component {
	public static final int MAX_LIGHT = 16;
	private final World world;

	public WorldLightingComponent(World world) {
		this.world = world;
	}

	/**
	 * Setup the light values for the entire map.
	 */
	public void updateNearPlayer() {
		// TODO: Only init near player
		int px = Globals.getPlayer().physics.getGridX();
		int py = Globals.getPlayer().physics.getGridY();
		// Range in chunks to load
		int range = 3;
		for (int y = py - range; y < py + range; y++) {
			for (int x = px - range; x < px + range; x++) {
				boolean hasBlock = world.blocks.getID(x, y) == null;
				byte light = MAX_LIGHT;

				if (hasBlock) {
					light -= calculateNeighbors(x, y);
				}

				world.blocks.setLight(x, y, light);
			}
		}

		update(1);
	}

	/**
	 * Update blocks only near the player. Called only when lighting near the
	 * player needs to be updated.
	 */
	@Override
	public void update(int dt) {
		// TODO: Better system, have prebacked light from init() then update the
		// blocks as neccesary + a radius around them, regardless of how far
		// they are from the player.
		// It shouldn't be too bad of a performance hit and will make lighting
		// more consistent.
		//
		//
		// Rect containing range of blocks on screen.
		Rectangle blocksRect = Camera.getBlocksOnScreen();
		// Expand beyond camera so the player doesn't run into non-updated areas
		try {
			Util.expand(blocksRect, (int) (Globals.getPlayer().physics.getVelocity().len() * 10));
		} catch (Exception e) {
			Util.expand(blocksRect, 20);
		}
		// Calculate light for blocks on the screen
		for (int y = blocksRect.y; y < blocksRect.y + blocksRect.height; y++) {
			for (int x = blocksRect.x; x < blocksRect.x + blocksRect.width; x++) {
				boolean hasBlock = world.blocks.getID(x, y) != null;
				boolean hasWall = world.walls.getID(x, y) != null;
				byte light = MAX_LIGHT;
				if (hasBlock || hasWall) {
					light -= calculateNeighbors(x, y);
					world.blocks.setLight(x, y, light);
				}
			}
		}
	}

	private int calculateNeighbors(int x, int y) {
		float neighbors = 0;
		int w = world.getWidth();
		int h = world.getHeight();
		for (int j = y - 1; j < y + 2; j++) {
			for (int i = x - 1; i < x + 2; i++) {
				if (i == x && j == y) {
					continue;
				}

				if (i < 0 || j < 0 || i >= w || j >= h) {
					neighbors++;
					continue;
				}

				if (world.blocks.getID(i, j) != null || world.walls.getID(i, j) != null) {
					neighbors += 1.5f;
				} else if (world.blocks.getID(i, j) != null) {
					neighbors += 1.0f;
				} else if (world.walls.getID(i, j) != null) {
					neighbors += 0.0f;
				}
			}
		}

		return (int) neighbors;
	}
}
