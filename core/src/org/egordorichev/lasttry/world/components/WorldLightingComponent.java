package org.egordorichev.lasttry.world.components;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;

import com.badlogic.gdx.math.Vector2;

public class WorldLightingComponent implements Component {
	public static final int MAX_LIGHT = 16;
	public static final int MOVE_TO_UPDATE = 10;
	private Point lastUpdate;
	private final World world;
	private final Map<Integer, Float> cache = new HashMap<>();

	public WorldLightingComponent(World world) {
		this.world = world;
	}

	public float get(int x, int y) {
		if (LastTry.noLight) {
			return 1f;
		}
		// Check cache if light for the tile has been calculated already.
		int key = getKey(x, y);
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		// Calculate light level
		float average = 0;
		int sampleRadius = 6;
		float max = MAX_LIGHT;
		float divisor = (float) (Math.pow(sampleRadius * 2, 2));
		for (int i = -sampleRadius; i < sampleRadius; i++) {
			for (int k = -sampleRadius; k < sampleRadius; k++) {
				float strength = LastTry.gammaStrength;
				Block block = world.blocks.get(x + i, y + k);
				boolean hasBlock = block != null;
				boolean canSeeSky = y >= world.getHighest(x);
				if (hasBlock) {
					// If block emits light
					if (block.isEmitter()) {
						// Create strength that will cause a strong bleed effect.
						// Dissapate with distance.
						float dist = new Vector2(-i, -k).len();
						if (dist <= sampleRadius - 0.125f) {
							strength = (float) Math.pow(10000, 1.75f / dist);
						}
						// TODO: If "PT(x,y) <-!-> PT(x+i,y+k)" then lessen bleed effect.
						// This will in effect create simple shadows
						
						/*
						 * Something like this (As is, does not work) will do a hacky ray-cast.
						 *
	public boolean check(Vector2 pt1, Vector2 pt2) {
		Vector2 vecDiff = pt1.cpy().sub(pt2.cpy()).scl(0.1f);
		Rectangle tmp = new Rectangle(pt1.x - 1, pt1.y - 1, 2,2);
		Direction initial = Direction.fromAngle(vecDiff.angle());
		boolean collision = isColliding(tmp);
		int i = 0;
		while (!collision && i < 100) {
			tmp = tmp.offset(vecDiff);
			Vector2 totalOffset = tmp.getPosition().cpy().sub(pt2.cpy());
			Direction current = Direction.fromAngle(totalOffset.angle());
			// Check if on opposite sides of compas (nearly),
			// if so has passed without being collided, so we can leave.
			if (!current.almostSame(initial)) {
				break;
			}
			// If collision, break and return true
			// this causes a shadow-like effect to occur in WorldLightingComponent.get(x,y)
			collision = isColliding(tmp);
			i++;
		}
		return collision;
	}
						 */
					}
				} else {
					if (canSeeSky) {
						strength += strength * 0.15f;
					} else {
						strength += -strength * 0.15f;
					}
				}
				average += (world.blocks.getLight(x + i, y + k) * strength / divisor);
			}
		}
		// Convert light value to a [0-1] range value.
		float output = Util.clamp(LastTry.gammaMinimum + (average / max), 0, 1);
		cache.put(key, output);
		return output;

	}

	/**
	 * Update blocks only near the player. Called only when lighting near the
	 * player needs to be updated.
	 */
	@Override
	public void update(int dt) {
		int px = Globals.getPlayer().physics.getGridX();
		int py = Globals.getPlayer().physics.getGridY();
		lastUpdate = new Point(px, py);
		// Range in blocks to load
		int range = 45;
		for (int y = py - range; y < py + range; y++) {
			for (int x = px - range; x < px + range; x++) {
				setLight(x, y);
			}
		}
	}

	/**
	 * Update blocks only in the direction the player is moving. More optimized
	 * than refreshing the entire area.
	 *
	 * @param dt
	 * @param px Player x-positon.
	 * @param py Player y-positon.
	 */
	public void updateByMove(int dt, int px, int py) {
		update(dt);
		// TODO: If performance becomes an issue, or caching proves to be a bad
		// idea, uncomment this and finish it up.
		/*
		 * Vector2 diffVec = new Vector2((float) (px - lastUpdate.getX()),
		 * (float) (py - lastUpdate.getY())); Direction dir =
		 * Direction.fromVector(diffVec); // int xRange = dir.isHorizontal() ?
		 * 45 : 20; int yRange = dir.isVertical() ? 45 : 20; // int xStart = px
		 * - xRange; int xEnd = px + xRange; // int yStart = py - yRange; int
		 * yEnd = py + yRange; for (int y = yStart; y < yEnd; y++) { for (int x
		 * = xStart; x < xEnd; x++) { cache.remove(getKey(x, y)); setLight(x,
		 * y); } } lastUpdate = new Point(px, py);
		 */
	}

	/**
	 * Clear all blocks light levels. Forces all blocks light values to be
	 * re-calculated.
	 */
	public void clearCache() {
		cache.clear();
	}

	/**
	 * Set light level for the given coordinates.
	 *
	 * @param x
	 * @param y
	 */
	private void setLight(int x, int y) {
		boolean hasBlock = world.blocks.getID(x, y) != null;
		boolean canSeeSky = y >= world.getHighest(x);
		byte light = canSeeSky ? MAX_LIGHT : (byte) (hasBlock ? world.blocks.get(x, y).getBrightness() : 0);
		world.blocks.setLight(x, y, light);
		cache.remove(getKey(x, y));
	}

	public boolean distanceCheck(int x, int y) {
		if (lastUpdate == null) return true;
		Point temp = new Point(x, y);
		return temp.distance(lastUpdate) > MOVE_TO_UPDATE;
	}

	private static int getKey(int x, int y) {
		return (x + y) * (x + y + 1) / 2 + x;
	}
}
