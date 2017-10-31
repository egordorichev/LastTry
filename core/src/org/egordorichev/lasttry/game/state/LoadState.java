package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.entity.asset.Assets;

public class LoadState extends State {
	public LoadState() {
		super("load");

		Assets.load();
	}

	@Override
	public void render(float delta) {

	}
}