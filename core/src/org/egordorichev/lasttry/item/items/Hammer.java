package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.item.wall.helpers.WallHelper;

public class Hammer extends DigTool {
	public Hammer(String id) {
		super(id);
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		Wall wall = Globals.getWorld().walls.get(x, y);

		if (wall == null) {
			return false;
		}

		ToolPower power = wall.getRequiredPower();

		if (this.power.isEnoughFor(power)) {
			byte hp = Globals.getWorld().walls.getHP(x, y);

			if (hp > 0) {
				Globals.getWorld().walls.setHP(WallHelper.setHP(hp, (byte) (WallHelper.getHP(hp) - 1)), x, y);
			}
		}

		return false;
	}
}