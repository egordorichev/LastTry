package org.egordorichev.lasttry.entity.entities.item;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Handles item in the world
 */
public class ItemEntity extends Entity {
	public ItemEntity(Item item, int count) {
		super(ItemComponent.class, PositionComponent.class, SizeComponent.class, CollisionComponent.class,
			VelocityComponent.class, AccelerationComponent.class);

		ItemComponent itemComponent = this.getComponent(ItemComponent.class);

		itemComponent.item = item;
		itemComponent.count = (short) count;

		TextureComponent texture = this.getComponent(ItemComponent.class).item.getComponent(TextureComponent.class);
		SizeComponent size = this.getComponent(SizeComponent.class);

		size.width = texture.texture.getRegionWidth();
		size.height = texture.texture.getRegionHeight();
	}

	public ItemEntity(Item item) {
		this(item, 1);
	}

	/**
	 * Renders item
	 */
	@Override
	public void render() {
		TextureComponent texture = this.getComponent(ItemComponent.class).item.getComponent(TextureComponent.class);
		PositionComponent position = this.getComponent(PositionComponent.class);

		Graphics.batch.draw(texture.texture, position.x, position.y);
	}

	/**
	 * Helper method for quick position setting
	 *
	 * @param x Position X
	 * @param y Position Y
	 * @return Self
	 */
	public ItemEntity setPosition(float x, float y) {
		PositionComponent position = this.getComponent(PositionComponent.class);

		position.x = x;
		position.y = y;

		return this;
	}
}