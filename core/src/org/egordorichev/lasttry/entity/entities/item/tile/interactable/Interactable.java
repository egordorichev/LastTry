package org.egordorichev.lasttry.entity.entities.item.tile.interactable;

/**
 * Basic interactable object
 */
public interface Interactable {
	/**
	 * Handles mouse click
	 *
	 * @param x Mouse X
	 * @param y Mouse Y
	 */
	default void onClick(int x, int y) {

	}

	/** Checks if mouse overlaps this object
	 *
	 * @param x Mouse X
	 * @param y Mouse Y
	 * @return If object overlaps
	 */
	default boolean checkOverlap(int x, int y) {
		return false;
	}
}