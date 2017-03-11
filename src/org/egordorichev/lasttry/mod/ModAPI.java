package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.entity*;
import org.egordorichev.lasttry.*;

public class ModAPI { // TODO: add more
	public ModAPI() {
	
	}
	
	public Player getPlayer() {
		return Lasttry.player;
	}
	
	public Enemy createEnemy(String name) {
		return Enemy.create(name);
	}
}
