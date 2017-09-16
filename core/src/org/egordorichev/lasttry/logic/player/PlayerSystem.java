package org.egordorichev.lasttry.logic.player;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entitySystem.EntitySystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.BaseComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;
import org.egordorichev.lasttry.injection.In;
import org.egordorichev.lasttry.logic.player.component.PlayerComponent;
import org.egordorichev.lasttry.physics.event.CollisionEvent;
import org.terasology.entitysystem.core.EntityRef;

public class PlayerSystem extends BaseComponentSystem implements UpdatableSystem{
    
    @In
    EntitySystem entitySystem;


    @BindEventHandler()
    public void handleCollisionEvent(CollisionEvent event, EntityRef target){

    }

    @Override
    public void update(float delta) {
       for(EntityRef player: entitySystem.getEntitiesFilterByComponent(PlayerComponent.class)){

       }
    }
}
