package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.EntityID;
import org.egordorichev.lasttry.util.Assets;

public class BlueSlime extends Slime {
	public BlueSlime() {
		super(EntityID.blueSlime, Assets.blueSlimeTexture);
	}
}
