package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.util.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class UiManager {
	private Map<String, UiComponent> components;

	public UiManager() {
		this.components = new HashMap<String, UiComponent>();
	}
	
	public void add(String id, UiComponent component) {
		this.components.put(id, component);
	}
	
	public <T extends UiComponent> T get(String id) {
		return (T) this.components.get(id);
	}
}
