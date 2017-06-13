package org.egordorichev.lasttry.item.items.seeds;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.plant.Plant;

public class Seeds extends Item {
	private Plant plant;

	public Seeds(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		try {
			this.plant = (Plant) Item.createInstance(root, root.getString("spreads", "org.egordorichev.lasttry.item.block.plant.DayBloom"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public boolean use(short x, short y) {
		Globals.getWorld().blocks.set(this.plant.getID(), x, y);
		return true;
	}

	@Override
	public boolean canBeUsed(short x, short y) {
		if (!super.canBeUsed(x, y)) {
			return false;
		}

		return this.plant.canBeGrownAt(x, y);
	}

	@Override
	public int getMaxInStack() {
		return 99;
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}
}