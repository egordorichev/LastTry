package org.egordorichev.lasttry.entity;

public class Player extends Entity {
	public Player(String name) {
		super(name, true, 100, 0, 0);
	}

	@Override
	public void render() {
		this.image.draw(this.rect.x, this.rect.y);
	}

	@Override
	public void update(int dt) {

	}

	@Override
	public void onSpawn() {

	}
}