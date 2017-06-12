package org.egordorichev.lasttry.player;

import org.egordorichev.lasttry.entity.components.CreatureComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.ui.UiInventory;

public class PlayerInputComponent extends CreatureComponent {
    public PlayerInputComponent(Player player) {
        super(player);

        // TODO: fire-once actions.
        /*
         * InputManager.multiplexer.addProcessor(new DefaultInputProcessor(){
         * 
         * @Override public boolean keyDown(int keycode) { creature.onAttack();
         * return false; } });
         */
    }

    public void update(int dt) {
        if (InputManager.isKeyDown(Keys.JUMP)) {
            this.creature.physics.jump();
        }

        if (InputManager.isKeyDown(Keys.MOVE_LEFT)) {
            this.creature.physics.move(PhysicsComponent.Direction.LEFT);
        }

        if (InputManager.isKeyDown(Keys.MOVE_RIGHT)) {
            this.creature.physics.move(PhysicsComponent.Direction.RIGHT);
        }

        if (InputManager.isKeyJustDown(Keys.OPEN_INVENTORY)) {
            UiInventory inv = ((Player) this.creature).getInventory();
            inv.toggle();
        }
    }
}