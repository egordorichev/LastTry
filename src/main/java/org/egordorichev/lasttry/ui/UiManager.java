package org.egordorichev.lasttry.ui;

import java.util.ArrayList;

public class UiManager {
	private ArrayList<UiComponent> components;

	public UiManager() {
		this.components = new ArrayList<>();
	}

	public void render() {
		for (int i = this.components.size() - 1; i >= 0; i--) {
			this.components.get(i).render();
		}
	}

	public void add(UiComponent component) {
		this.components.add(component);
	}
}
