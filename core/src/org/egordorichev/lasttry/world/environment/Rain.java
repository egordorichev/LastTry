package org.egordorichev.lasttry.world.environment;

import org.egordorichev.lasttry.util.Util;

public class Rain extends Event {
	private int time = 0;
	private int totalTime = 0;

	public Rain() {
		super("Rain");
	}

	@Override
	public void onStart() {
		this.totalTime = Util.random(1000, 1400); // Around 24 game hours

		// TODO: graphic effects and spawn
	}

	@Override
	public void update(int dt) {
		this.time++;

		if (this.time >= this.totalTime) {
			this.end();
		}
	}

	@Override
	public void onEnd() {
		// TODO: graphic effects and spawn
	}

	@Override
	public boolean canHappen() {
		return true;
	}
}