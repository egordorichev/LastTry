package org.egordorichev.lasttry.ui;

import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class UiPanel extends UiComponent {
	/** Panel children **/
	private List<UiComponent> children = new ArrayList<>();

	public UiPanel(Rectangle rectangle, Origin origin) {
		super(rectangle, origin);
		this.addComponents();
	}

	public UiPanel(Rectangle rectangle) {
		this(rectangle, Origin.TOP_LEFT);
	}

	/** Used for overriding from instances */
	public void addComponents() {

	}

	/**
	 * Adds given component to the panel
	 * 
	 * @param component
	 *            component to add
	 */
	public void add(UiComponent component) {
		this.children.add(component);
	}

	/** Render children */
	public void render() {
		if (this.hidden) {
			return;
		}

		super.render();

		for (UiComponent component : this.children) {
			component.render();
		}
	}
}
