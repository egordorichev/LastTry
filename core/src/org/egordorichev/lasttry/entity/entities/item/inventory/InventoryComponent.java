package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.IdComponent;

import java.io.IOException;

/**
 * Handles items
 */
public class InventoryComponent extends Component {
	/**
	 * Inventory size
	 */
	private short size = 40;

	/**
	 * The actual inventory
	 */
	public ItemComponent[] inventory;
	/**
	 * Shows, if the inventory is open
	 */
	public boolean open = false;
	/**
	 * Current selected slot
	 */
	public short selectedSlot = 0;

	public InventoryComponent() {
		this.initSlots();
	}

	/**
	 * Writes component to file
	 *
	 * @param writer File, to write
	 */
	@Override
	public void write(FileWriter writer) {
		try {
			writer.writeInt16(this.size);

			for (short i = 0; i < this.size; i++) {
				ItemComponent item = this.inventory[i];

				if (item.isEmpty()) {
					writer.writeBoolean(false);
				} else {
					writer.writeBoolean(true);

					writer.writeString(item.item.getComponent(IdComponent.class).id);
					writer.writeInt16(item.count);
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Loads component from a file
	 *
	 * @param reader File with component
	 */
	@Override
	public void load(FileReader reader) {
		try {
			this.size = reader.readInt16();
			this.initSlots();

			for (short i = 0; i < this.size; i++) {
				if (reader.readBoolean()) {
					this.inventory[i].item = Assets.items.get(reader.readString());
					this.inventory[i].count = reader.readInt16();
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Just creates the array
	 */
	private void initSlots() {
		this.inventory = new ItemComponent[this.size];

		for (int i = 0; i < this.size; i++) {
			this.inventory[i] = new ItemComponent();
		}
	}

	/**
	 * @return Inventory size
	 */
	public short getSize() {
		return this.size;
	}
}