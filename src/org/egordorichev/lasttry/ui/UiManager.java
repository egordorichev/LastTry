package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.util.Rectangle;

import java.util.ArrayList;

public class UiManager {
	private ArrayList<UiComponent> components;

	public UiManager() {
		this.components = new ArrayList<>();
	}

	public void render() {
		for(UiComponent component : this.components) {
			component.render();
		}
	}

	public void add(UiComponent component) {
		this.components.add(component);
	}
}
