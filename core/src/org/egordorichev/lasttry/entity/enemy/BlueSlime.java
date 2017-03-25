package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.graphics.Assets;

public class BlueSlime extends Slime {
	public BlueSlime() {
		super(EnemyID.blueSlime, LastTry.world.isExpertMode() ? 50 : 25, 2, LastTry.world.isExpertMode() ? 7 : 14,
			Textures.blueSlime);

		this.drops.add(new Drop(Item.copperCoin, 25, 25));
	}
}
