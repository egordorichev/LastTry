package org.egordorichev.lasttry.entitySystem;

import org.terasology.entitysystem.component.ComponentManager;
import org.terasology.entitysystem.entity.inmemory.InMemoryEntityManager;
import org.terasology.entitysystem.transaction.TransactionManager;

public class EntityProvider extends InMemoryEntityManager {

    public EntityProvider(ComponentManager library, TransactionManager transactionManager) {
        super(library, transactionManager);
    }

    public EntityProvider(ComponentManager library, TransactionManager transactionManager, long nextEntityId) {
        super(library, transactionManager, nextEntityId);
    }
}
