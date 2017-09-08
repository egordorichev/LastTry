package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.inventory.ItemHolder;

public class ChestRegistry {
	private int x;
	private int y;
	private ItemHolder[] items;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public ItemHolder[] getItems() {
		return this.items;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setItems(ItemHolder[] items) {
		this.items = items;
	}
}