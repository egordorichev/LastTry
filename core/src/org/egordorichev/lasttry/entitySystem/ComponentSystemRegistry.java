package org.egordorichev.lasttry.entitySystem;

import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;

public interface ComponentSystemRegistry {
    void update();
    void registerComponentSystem(ComponentSystem componentSystem);
}
