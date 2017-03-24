package org.egordorichev.lasttry.ui;

import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class UiPanel extends UiComponent {
	/** Panel children **/
	private ArrayList<UiComponent> children;

	public UiPanel(Rectangle rectangle, Origin origin) {
		super(rectangle, origin);

		this.children = new ArrayList<>();
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
	 * @param component component to add
	 */
	public void add(UiComponent component) {
		this.children.add(component);
	}

	/** Render children */
	public void render() {
		if(this.hidden) {
			return;
		}

		super.render();

		for (UiComponent component : this.children) {
			component.render();
		}
	}
}
