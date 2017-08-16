package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.Item;

public class Coin extends Item {
	protected int cost;

	public Coin(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);
		this.cost = root.getInt("cost", 1);
	}

	public int getCost() {
		return cost;
	}

	@Override
	public boolean canBeUsed(short x, short y) {
		return false;
	}

	@Override
	public int getMaxInStack() {
		return 100;
	}
}