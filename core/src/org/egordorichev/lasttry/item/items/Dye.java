package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.Item;

public class Dye extends Item {
	protected Color color;

	public Dye(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		JsonValue color = root.get("color");

		if (color != null) {
			this.color = new Color(color.getByte(0), color.getByte(1),
				color.getByte(2), color.getByte(3));
		}
	}

	public Color getColor() {
		return this.color;
	}
}
