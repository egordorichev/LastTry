package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.util.Assets;

public class BlueSlime extends Slime {
	public BlueSlime() {
		super(EnemyID.blueSlime, Assets.blueSlimeTexture);
	}
}
