package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;

public class DeathWeed extends Plant {
	public DeathWeed() {
		super(ItemID.deathWeed, "Death Weed", Assets.getTexture(Textures.deathWeedIcon), Assets.getTexture(Textures.deathWeed));
	}

	@Override
	public boolean canBeGrownAt(int x, int y) {
		if (!super.canBeGrownAt(x, y)) {
			return false;
		}

		short id = Globals.getWorld().getBlockID(x, y + 1);

		if (id == ItemID.ebonstoneBlock || id == ItemID.crimstoneBlock) {
			return true; // TODO: add corrupt and crimson grass
		}

		return false;
	}

	@Override
	protected boolean canBloom() {
		return Globals.environment.isBloodMoon();
	}
}