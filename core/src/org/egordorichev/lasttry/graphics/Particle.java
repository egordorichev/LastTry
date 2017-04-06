package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.PhysicBody;
import org.egordorichev.lasttry.item.block.Block;

public class Particle extends PhysicBody {
	public Particle(Texture texture) {
		this.texture = texture;
	}

	public Particle() {
		// Must set texture or change render method from child
	}

	@Override
	protected void onBlockHit() {
		this.die();
	}

	@Override
	public void render() {
		LastTry.batch.draw(this.texture, this.getX(), LastTry.world.getHeight() * Block.TEX_SIZE - this.getY());
	}
}