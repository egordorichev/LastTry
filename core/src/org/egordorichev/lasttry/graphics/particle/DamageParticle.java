package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.util.Util;

/**
 * Damage text handler TODO: rework it as a text particle
 */
public class DamageParticle extends Particle {
	/**
	 * Particle text
	 */
	private int damage;
	/**
	 * Hit was critical
	 */
	private boolean crit;
	/**
	 * Particle alpha
	 */
	private float alpha = 1;

	public DamageParticle(boolean crit, int damage) {
		super();
		this.damage = damage;
		this.crit = crit;
	}

	@Override
	public void setupComponents() {
		super.setupComponents();
		this.physics = new PhysicsComponent<DamageParticle>(this) {
			@Override
			public void update(int dt) {
				this.velocity.y += 0.1;
				super.update(dt);
			}
		};
		this.physics.setSolid(false);
		this.graphics = new GraphicsComponent<DamageParticle>(this) {
			@Override
			public void render() {
				alpha -= 0.01;

				if (crit) {
					Util.drawWithShadow(Assets.f24, String.valueOf(damage), physics.getX(), physics.getY() + 24, 0.92f,
							0.58f, 0.29f);
				} else {
					Util.drawWithShadow(Assets.f24, String.valueOf(damage), physics.getX(), physics.getY() + 24, 0.86f,
							0.33f, 0.10f);
				}

				if (alpha <= 0) {
					die();
				}
			}
		};
	}
}