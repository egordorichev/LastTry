package org.egordorichev.lasttry.entitySystem;

import com.badlogic.gdx.Gdx;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;

import java.util.Set;

public class ComponentSystemRegistryImpl implements ComponentSystemRegistry {
    private Set<UpdatableSystem> updatableSystem = Sets.newHashSet();

    @Override
    public void update() {
        for(UpdatableSystem system: updatableSystem){
            system.update(Gdx.graphics.getDeltaTime());
        }
    }

    public void registerComponentSystem(ComponentSystem componentSystem){
        if(componentSystem instanceof UpdatableSystem)
            updatableSystem.add((UpdatableSystem) componentSystem);
    }
}
