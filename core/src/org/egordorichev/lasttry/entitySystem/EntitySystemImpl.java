package org.egordorichev.lasttry.entitySystem;

import com.google.common.collect.Sets;
import org.egordorichev.lasttry.core.context.Context;
import org.terasology.entitysystem.component.CodeGenComponentManager;
import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.entity.inmemory.InMemoryEntityManager;
import org.terasology.entitysystem.index.ComponentIndexes;
import org.terasology.entitysystem.index.Index;
import org.terasology.entitysystem.transaction.TransactionManager;
import org.terasology.valuetype.ImmutableCopy;
import org.terasology.valuetype.TypeHandler;
import org.terasology.valuetype.TypeLibrary;

public class EntitySystemImpl implements EntitySystem {
    private final EntityManager entityManager;
    private final TransactionManager transactionManager;

    public EntitySystemImpl(Context context){
        TypeLibrary types = new TypeLibrary();

        types.addHandler(new TypeHandler<>(String.class, ImmutableCopy.create()));
        types.addHandler(new TypeHandler<>(EntityRef.class, ImmutableCopy.create()));

        transactionManager = new TransactionManager();
        entityManager =  new InMemoryEntityManager(new CodeGenComponentManager(types),transactionManager );

    }

    public<T extends Component> Index getEntitiesFilterByComponent(Class<T>... elements){
        return ComponentIndexes.createComponentIndex(transactionManager, entityManager, Sets.newHashSet(elements));
    }
}
