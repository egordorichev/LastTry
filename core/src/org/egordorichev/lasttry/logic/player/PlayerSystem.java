package org.egordorichev.lasttry.logic.player;

import org.egordorichev.lasttry.entitySystem.EntitySystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.BaseComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;
import org.egordorichev.lasttry.injection.In;
import org.egordorichev.lasttry.logic.player.component.PlayerComponent;
import org.terasology.entitysystem.core.EntityRef;

public class PlayerSystem extends BaseComponentSystem implements UpdatableSystem{
    
    @In
    EntitySystem entitySystem;


    @Override
    public void Update(float delta) {
       for(EntityRef player: entitySystem.getEntitiesFilterByComponent(PlayerComponent.class)){

       }
    }
}
