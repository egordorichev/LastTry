package org.egordorichev.lasttry.entitySystem;

import org.egordorichev.lasttry.entitySystem.ComponentProvider;
import org.egordorichev.lasttry.entitySystem.EntityProvider;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.system.Subsystem;
import org.reflections.Reflections;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.transaction.Transaction;
import org.terasology.entitysystem.transaction.TransactionManager;
import org.terasology.valuetype.ImmutableCopy;
import org.terasology.valuetype.TypeHandler;
import org.terasology.valuetype.TypeLibrary;

public class EntitySubsystem implements Subsystem {
    @Override
    public String name() {
        return "Entity System";
    }

    @Override
    public void load(Context context) {
        //todo: reflection
        Reflections reflections = new Reflections("org.egordorichev.lasttry");
        for(Class<? extends ComponentSystem> componentSystem : reflections.getSubTypesOf(ComponentSystem.class)){
            InjectionHelper.createWithConstructorInjection(componentSystem,context);
        }

        TypeLibrary types = new TypeLibrary();

        types.addHandler(new TypeHandler<>(String.class, ImmutableCopy.create()));
        types.addHandler(new TypeHandler<>(EntityRef.class, ImmutableCopy.create()));

        TransactionManager transactionManager = new TransactionManager();
        EntityProvider entityProvider =  new EntityProvider(new ComponentProvider(types),transactionManager );
        context.bindInstance(EntityManager.class,entityProvider);
        context.bindInstance(Transaction.class,transactionManager);


    }

}
