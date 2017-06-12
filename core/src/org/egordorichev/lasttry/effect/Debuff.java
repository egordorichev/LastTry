package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.Creature;

public abstract class Debuff extends Effect {
	public Debuff(String name, String description, TextureRegion texture) {
		super(name, description, texture);

		this.canBeRemoved = false;
	}
}