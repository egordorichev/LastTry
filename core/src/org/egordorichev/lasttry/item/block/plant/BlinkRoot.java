package org.egordorichev.lasttry.item.block.plant;

import org.egordorichev.lasttry.LastTry;

public class BlinkRoot extends Plant {
	public BlinkRoot(String id) {
		super(id, "lt:dirt", "lt:mud");
	}
	
	@Override
	protected boolean canBloom() {
		return LastTry.random.nextInt(3) == 0;
	}
}