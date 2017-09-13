package org.egordorichev.lasttry.system;

import org.egordorichev.lasttry.entitySystem.ComponentProvider;
import org.egordorichev.lasttry.entitySystem.EntityProvider;
import org.egordorichev.lasttry.entitySystem.TransactionManagerProvider;
import org.egordorichev.lasttry.injection.Context;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.valuetype.TypeLibrary;

public class EntitySystem implements Subsystem{
    @Override
    public String name() {
        return "Entity System";
    }

    @Override
    public void load(Context context) {
        TypeLibrary types = new TypeLibrary();

        EntityProvider entityProvider =  new EntityProvider(new ComponentProvider(types),new TransactionManagerProvider());
        context.bindInstance(EntityManager.class,entityProvider);
    }

}
