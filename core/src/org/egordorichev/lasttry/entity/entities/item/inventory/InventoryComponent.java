package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.entities.item.StackComponent;

import java.io.IOException;

/**
 * Handles items
 */
public class InventoryComponent extends Component {
  /**
   * Inventory size
   */
  protected short size = 40;

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
   * Tries to add an item to the inventory
   *
   * @param item The item
   * @return Was it added or no
   */
  public boolean add(ItemComponent item) {
    if (item == null) {
      return false;
    }

    // Check for existing stack
    for (ItemComponent slot : this.inventory) {
      // The items match
      if (slot.item == item.item) {
        StackComponent slotMax = slot.item.getComponent(StackComponent.class);

        // If we have enough space
        if (slotMax.max >= slot.count + item.count) {
          slot.count += item.count;
          return true;
        }
      }
    }

    // Did not find it, check for an empty one
    for (ItemComponent slot : this.inventory) {
      if (slot.isEmpty()) {
        slot.item = item.item;
        // Cap the value
        StackComponent slotMax = slot.item.getComponent(StackComponent.class);
        slot.count = (short) Math.min(slotMax.max, item.count);

        return true;
      }
    }

    return false;
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