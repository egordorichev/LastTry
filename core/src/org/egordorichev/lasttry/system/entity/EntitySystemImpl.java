package org.egordorichev.lasttry.system.entity;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.google.common.collect.Sets;
import org.egordorichev.lasttry.core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitysystem.component.CodeGenComponentManager;
import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.entity.inmemory.InMemoryEntityManager;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventResult;
import org.terasology.entitysystem.event.impl.EventProcessor;
import org.terasology.entitysystem.index.ComponentIndexes;
import org.terasology.entitysystem.index.Index;
import org.terasology.entitysystem.transaction.TransactionManager;
import org.terasology.valuetype.ImmutableCopy;
import org.terasology.valuetype.TypeHandler;
import org.terasology.valuetype.TypeLibrary;

public class EntitySystemImpl implements EntitySystem {
    private static final Logger logger = LoggerFactory.getLogger(EntitySystemImpl.class);
    private final EntityManager entityManager;
    private final TransactionManager transactionManager;
    private final EventProcessor eventProcessor;


    public EntitySystemImpl(Context context,EventProcessor eventProcessor){
        TypeLibrary types = new TypeLibrary();
        this.eventProcessor = eventProcessor;
        types.addHandler(new TypeHandler<>(String.class, ImmutableCopy.create()));
        types.addHandler(new TypeHandler<>(EntityRef.class, ImmutableCopy.create()));
        types.addHandler(new TypeHandler<>(Vector3.class, vec -> new Vector3(vec.x, vec.y, vec.z)));
        types.addHandler(new TypeHandler<>(Vector2.class, vec -> new Vector2(vec.x, vec.y)));
        types.addHandler(new TypeHandler<>(Quaternion.class, quat -> new Quaternion(quat.x, quat.y, quat.z, quat.w)));

        transactionManager = new TransactionManager();
        entityManager =  new InMemoryEntityManager(new CodeGenComponentManager(types),transactionManager );

    }

    public EventResult send(Event event, EntityRef entity) {
        return eventProcessor.send(event,entity);
    }


    public<T extends Component> Index filterEntitiesByComponent(Class<T>... elements){
        return ComponentIndexes.createComponentIndex(transactionManager, entityManager, Sets.newHashSet(elements));
    }
}
