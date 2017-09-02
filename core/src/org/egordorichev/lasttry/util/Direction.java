package org.egordorichev.lasttry.util;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
	EAST, NORTH_EAST, NORTH, NORTH_WEST, WEST, SOUTH_WEST, SOUTH, SOUTH_EAST;
	private static final float SECTION_SIZE = 45; // 360 / 8
	private static final float ROTATION_OFFSET = 23; // Math.cail(45f / 2f)

	/**
	 * Direction from velocity vector.
	 * 
	 * @param velocity
	 * @return Cardinal direction of the velocity vector.
	 */
	public static Direction fromVector(Vector2 velocity) {
		return fromAngle(velocity.angle());
	}

	/**
	 * Direction from angle.
	 * 
	 * @param angle
	 * @return Cardinal direction of the angle.
	 */
	public static Direction fromAngle(float angle) {
		float offsettedAngle = angle - ROTATION_OFFSET;
		for (int i = 1; i <= 8; i++) {
			float angleSectionBound = (i * SECTION_SIZE) - ROTATION_OFFSET;
			if (offsettedAngle < angleSectionBound) {
				return Direction.values()[i - 1];
			}
		}
		return null;
	}

	/**
	 * Check if direction has no vertical aspect.
	 * 
	 * @return
	 */
	public boolean isHorizontal() {
		return this == EAST || this == WEST;
	}

	/**
	 * Check if direction has no horizontal aspect.
	 * 
	 * @return
	 */
	public boolean isVertical() {
		return this == NORTH || this == SOUTH;
	}

	/**
	 * Check if direction is facing north.
	 * 
	 * @return
	 */
	public boolean isNorth() {
		return this == NORTH || this == NORTH_EAST || this == NORTH_WEST;
	}

	/**
	 * Check if direction is facing east.
	 * 
	 * @return
	 */
	public boolean isEast() {
		return this == EAST || this == NORTH_EAST || this == SOUTH_EAST;
	}

	/**
	 * Check if direction is facing south.
	 * 
	 * @return
	 */
	public boolean isSouth() {
		return this == SOUTH || this == SOUTH_EAST || this == SOUTH_WEST;
	}

	/**
	 * Check if direction is facing west.
	 * 
	 * @return
	 */
	public boolean isWest() {
		return this == WEST || this == NORTH_WEST || this == SOUTH_WEST;
	}
}