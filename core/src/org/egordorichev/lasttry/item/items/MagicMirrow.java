package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;

public class MagicMirrow extends Item {
	public MagicMirrow(String id) {
		super(id);
	}

	@Override
	public boolean use(short x, short y) {
		Globals.getPlayer().tpToSpawn();
		return false;
	}

	@Override
	public boolean canBeUsed(short x, short y) {
		return true;
	}
}
