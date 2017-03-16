package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.ItemHolder;

public class Inventory {
	protected ItemHolder[] items;
	protected int size;

	public Inventory(int size) {
		this.size = size;
		this.items = new ItemHolder[size];
	}

	public void clear() {
		for(int i = 0; i < this.size; i++) {
			this.items[i] = null;
		}
	}

	public ItemHolder getItem(int index) {
		if(index > this.items.length - 1 || index < 0) {
			return null;
		}

		return this.items[index];
	}

	public int getSize() {
		return this.size;
	}
}