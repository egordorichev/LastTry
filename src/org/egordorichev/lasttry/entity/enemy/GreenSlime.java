package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.EntityID;
import org.egordorichev.lasttry.util.Assets;

public class GreenSlime extends Slime {
	public GreenSlime() {
		super(EntityID.greenSlime, Assets.greenSlimeTexture);
	}
}
