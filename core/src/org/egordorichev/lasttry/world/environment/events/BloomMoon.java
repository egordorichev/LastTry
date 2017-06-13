package org.egordorichev.lasttry.world.environment.events;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.world.environment.Event;

public class BloomMoon extends Event {
	public BloomMoon() {
		super("Blood Moon");
	}

	@Override
	public void onStart() {
		// TODO: modify spawn and spawn rate
	}

	@Override
	public void update(int dt) {
		if (!Globals.environment.time.isNight()) {
			this.end();
		}
	}

	@Override
	public void onEnd() {
		// TODO: modify spawn and spawn rate
	}

	@Override
	public boolean canHappen() {
		if (Globals.environment.time.isNight()) {
			return true;
		}

		return false;
	}
}