package org.egordorichev.lasttry.entitySystem;

import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.terasology.entitysystem.core.EntityManager;

public class SystemProvider {
    private EntityManager entityManager;

    public SystemProvider(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public <T extends ComponentSystem>T RegisterSystem(Context context, Class<T> system){
        T result = InjectionHelper.createWithConstructorInjection(system,context);
        return result;
    }

    private  void registerEventHandler(){

    }
}
