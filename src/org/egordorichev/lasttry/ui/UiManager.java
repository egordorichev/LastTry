package org.egordorichev.lasttry.ui;

public class UiManager {
	private Map<String, UiComponent> componets;

	public UiManager() {
		this.componets = new HashMap<String, UiComponent>();
	}
	
	public <T extends UiComponent> T add(String id, Rectangle rect, Object ... args) {
		T component = new T(rect, args);
		this.components.get(id) = component;
		return component;
	}
	
	public <T extends UiComponent> T get(String id) {
		return (T) this.components.get(id);
	}
}
