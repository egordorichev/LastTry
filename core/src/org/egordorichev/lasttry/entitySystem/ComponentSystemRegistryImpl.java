package org.egordorichev.lasttry.entitySystem;

import com.badlogic.gdx.Gdx;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;

import java.util.Set;

//TODO: figure out a better way to manage components Systems this single use class does nothing more then update the associated system
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
