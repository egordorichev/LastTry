package org.egordorichev.lasttry.entity.player;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.inventory.Inventory;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.ui.UiInventory;
import org.egordorichev.lasttry.ui.UiItemSlot;

public class Player extends Creature implements InventoryOwner<UiItemSlot> {
    public static final int INVENTORY_SIZE = 88;
    private UiInventory inventory;
    private PlayerInputComponent input;
    private String name;

    public Player(String name) {
        super(new CreaturePhysicsComponent(), new PlayerGraphicsComponent());

        this.input = new PlayerInputComponent(this);
        this.stats.set(100, 20, 0, 0);
        this.name = name;
        this.setInventory(new UiInventory(INVENTORY_SIZE, this));

        LastTry.ui.add(this.getInventory());

        this.setZIndex(Layers.player);
    }

    @Override
    public void update(int dt) {
        super.update(dt);
        this.input.update(dt);
        
        if (this.getInventory().getSelectedItem() != null && this.getInventory().getSelectedItem().getItem() != null) {
            this.getInventory().getSelectedItem().getItem().update(this, dt);
        }
    }

    public int getItemUseRadius() {
        // TODO: modify
        return 10;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public UiInventory getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(Inventory<UiItemSlot> inventory) {
        this.inventory = (UiInventory) inventory;
        
    }
}