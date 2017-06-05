package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.Item;

public class Coin extends Item {
	protected int value;

	public Coin(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);
		this.value = root.getInt("value", 1);
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}

	@Override
	public int getMaxInStack() {
		return 100;
	}
}