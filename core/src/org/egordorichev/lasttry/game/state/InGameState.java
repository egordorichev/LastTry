package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.Globals;

public class InGameState extends State {
	public InGameState() {
		super("in game");
	}

	public void update(float delta) {
		Globals.entitySystem.update(delta);
	}

	public void render() {
		Globals.camera.set();
		Globals.entitySystem.render();
	}
}