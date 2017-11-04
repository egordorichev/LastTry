package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;

/**
 * Represents all mobs, players, NPCs and etc
 */
public class Creature extends Entity {
	public Creature(Class<? extends Component> ... types) {
		super(HealthComponent.class, AccelerationComponent.class,
			VelocityComponent.class, TextureComponent.class);

		this.addComponent(types);

		// TODO: animation component and other, load from JSON
	}
}