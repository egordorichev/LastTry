package org.egordorichev.lasttry.item;

public interface ItemManager {
    public void load();
    boolean hasItem(String id);
    Item getItem(String id);
    Item addItem(String id, Item item);
}
