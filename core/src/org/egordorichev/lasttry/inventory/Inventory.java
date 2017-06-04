package org.egordorichev.lasttry.inventory;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.ui.UiItemSlot;

public interface Inventory<Slot extends InventorySlot> {

	/**
	 * Adds the given content to the inventory.
	 *
	 * @param holder
	 *            Content to add.
	 * @return If addition was success.
	 */
	default boolean add(ItemHolder holder) {
		// TODO: check if item is already in inventory
		for (int i = 0; i < getMaxInventorySize(); i++) {
			Slot slot = getSlot(i);
			// If slot is empty or slot is full skip consideration.
			if (slot.isEmpty() || slot.getItemCount() >= slot.getItem().getMaxInStack()) {
				continue;
			}

			// Check if ID's match.
			// If so, add to inventory.
			// If overflows max-per item stack, add the remainder as another
			// stack.
			Item item = slot.getItem();
			if (item.getID() == holder.getItem().getID()) {
				int count = slot.getItemCount() + holder.getCount();
				// If overflow, split stacks
				if (count > item.getMaxInStack()) {
					slot.setItemCount(item.getMaxInStack());
					return add(new ItemHolder(item, count - slot.getItem().getMaxInStack()));
				}
				// Not overflowm insert and return success
				slot.setItemCount(count);
				return true;
			}
		}

		Slot slot = this.getFirstFreeSlot(UiItemSlot.Type.ANY);
		if (slot != null) {
			slot.setItemHolder(holder);
			return true;
		}
		return false;
	}

	/**
	 * Returns the slot with the first open spot in the given range. If no open
	 * slot exists, null is returned.
	 *
	 * @param start
	 *            First slot index.
	 * @param end
	 *            End slot index.
	 * @return First empty slot in range, null if no empty slots.
	 */
	default Slot getFirstFreeSlot(int start, int end) {
		if (start > end || start < 0 || end > getMaxInventorySize()) {
			return null;
		}

		for (int i = start; i < end; i++) {
			if (getSlot(i).isEmpty()) {
				return getSlot(i);
			}
		}

		return null;
	}

	/**
	 * Returns the first free slot in the inventory for the given type of slot.
	 *
	 * @param type
	 *            Type of slot.
	 * @return
	 */
	Slot getFirstFreeSlot(Slot.Type type);

	/**
	 * Return the ItemHolder at the {@link #getActiveSlot() active slot}.
	 *
	 * @return
	 */
	default ItemHolder getActiveItem() {
		return this.getItemInSlot(getActiveSlot());
	}

	/**
	 * Sets the ItemHolder at the given index.
	 *
	 * @param holder
	 * @param index
	 *            Slot index.
	 */
	default void setItemInSlot(int index, ItemHolder holder) {
		if (index < 0 || index > getMaxInventorySize()) {
			return;
		}

		getSlot(index).setItemHolder(holder);
	}

	/**
	 * Returns the Item at the given slot index.
	 *
	 * @param index
	 *            Slot index.
	 * @return
	 */
	default Item getItem(int index) {
		return getItemInSlot(index).getItem();
	}

	/**
	 * Returns the ItemHolder at the given slot index.
	 *
	 * @param index
	 *            Slot index.
	 * @return
	 */
	default ItemHolder getItemInSlot(int index) {
		if (index < 0 || index > 88) {
			return null;
		}

		return getSlot(index).getItemHolder();
	}

	/**
	 * Returns the current active slot in the inventory. For example, this
	 * should be a toolbar slot.
	 *
	 * @return
	 */
	int getActiveSlot();

	/**
	 * Return the owner of this inventory.
	 *
	 * @return
	 */
	InventoryOwner<Slot> getOwner();

	/**
	 * Returns the item slot at the given index.
	 *
	 * @param index
	 *            Slot index.
	 * @return
	 */
	Slot getSlot(int index);

	/**
	 * Returns the item currently clicked in the inventory. This is not to be
	 * confused with the current active item which is what the entity uses with
	 * their main action input <i>(Like a pickaxe being used for mining)</i>
	 */
	ItemHolder getSelectedItem();

	/**
	 * Sets the current selected item. See {@link #getSelectedItem()} for more
	 * details.
	 *
	 * @param selectedItem
	 *            Item to select.
	 */
	void setSelectedItem(ItemHolder selectedItem);

	/**
	 * Returns the maximum number of slots in the inventory.
	 *
	 * @return
	 */
	int getMaxInventorySize();

	default public void clear() {
		for (int i = 0; i < getMaxInventorySize(); i++) {
			getSlot(i).setItemHolder(new ItemHolder(null, 0));
		}
	}
}
