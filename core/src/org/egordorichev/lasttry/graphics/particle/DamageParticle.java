package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class DamageParticle extends Particle {
	private int damage;
	private boolean crit;
	private float alpha = 1;

	public DamageParticle(boolean crit, int damage) {
		super();

		this.physics = new PhysicsComponent(this) {
			@Override
			public void update(int dt) {
				this.velocity.y += 0.1;
				super.update(dt);
			}
		};

		this.damage = damage;
		this.crit = crit;
		this.physics.setSolid(false);

		this.graphics = new GraphicsComponent() {
			@Override
			public void render() {
				alpha -= 0.01;


				if (crit) {
					Assets.f24.setColor(0.92f, 0.58f, 0.29f, alpha);
				} else {
					Assets.f24.setColor(0.86f, 0.33f, 0.10f, alpha);
				}

				Assets.f24.draw(Graphics.batch, String.valueOf(damage), physics.getX(), physics.getY() + 24);
				Assets.f24.setColor(1, 1, 1, 1);

				if (alpha <= 0) {
					die();
				}
			}
		};

		this.graphics.setEntity(this);
	}
}