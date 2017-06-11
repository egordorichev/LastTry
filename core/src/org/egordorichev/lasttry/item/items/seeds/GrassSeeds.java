package org.egordorichev.lasttry.item.items.seeds;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.plant.Grass;
import org.egordorichev.lasttry.util.Rectangle;

public class GrassSeeds extends Item {
	private Grass grass;

	public GrassSeeds(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		try {
			this.grass = (Grass) Item.createInstance(root, root.getString("spreads", "org.egordorichev.lasttry.item.block.plant.Grass"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public boolean use(short x, short y) {
		String id = Globals.getWorld().blocks.getID(x, y);

		if (this.grass.canBeGrownAt(id)) {
			Globals.getWorld().blocks.set(this.grass.getID(), x, y);
			return true;
		}

		return false;
	}
}