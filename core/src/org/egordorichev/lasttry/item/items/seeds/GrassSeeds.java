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
	public boolean use() {
		// Get world position to place block at
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;
		// TODO: Distance checks from cursor coordinates to player coordinates

		String id = Globals.getWorld().blocks.getID(x, y);

		// Check if the plant can be placed.
		if (this.grass.canBeGrownAt(id)) {
			// Check if the plant intersects the player's hitbox
			// TODO: Check other entities in the world
			Rectangle rectangle = Globals.getPlayer().physics.getHitbox();

			if (rectangle.intersects(new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE))) {
				return false;
			}

			Globals.getWorld().blocks.set(this.grass.getID(), x, y);
			return true;
		}

		return false;
	}
}