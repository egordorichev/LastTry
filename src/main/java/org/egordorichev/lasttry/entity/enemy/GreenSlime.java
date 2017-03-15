package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.Enemy;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.util.Assets;

public class GreenSlime extends Slime {
	public GreenSlime() {
		super(EnemyID.greenSlime, Assets.greenSlimeTexture);
	}
}
