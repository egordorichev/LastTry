package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;

public class EndlessBucket extends Tool {
	private byte type;

	public EndlessBucket(String id) {
		super(id);

		this.useDelayMax = 1;
		this.autoSwing = true;
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		String type = root.getString("liquid", "water");

		switch (type) {
			case "water": default:
				this.type = 0;
				break;
			case "lava":
				this.type = 1;
				break;
			case "honey":
				this.type = 2;
				break;
			case "acid":
				this.type = 3;
				break;
		}
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		if (Globals.getWorld().blocks.get(x, y) == null) {
			byte hp = Globals.getWorld().blocks.getHP(x, y);
			hp = BlockHelper.empty.setLiquidLevel(hp, (byte) 15);
			hp = BlockHelper.empty.setLiquidType(hp, this.type);
			Globals.getWorld().blocks.setHP(hp, x, y);
		}

		return false;
	}
}