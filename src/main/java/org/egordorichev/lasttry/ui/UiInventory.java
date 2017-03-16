package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.entity.Inventory;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.geom.Rectangle;

public class UiInventory extends UiComponent {
	private Inventory inventory;

	// TODO: open, close

	public UiInventory(int size) {
		super(new Rectangle(10, 10, 100, 32)); // TODO: tune

		this.inventory = new Inventory(size); // TODO: add drag and drop ui slot
	}

	@Override
	public void render() {
		super.render();

		Assets.inventorySlotTexture.draw(this.getX(), this.getY()); // TODO: draw for all
	}

	// TODO: interfaces to inventory
}