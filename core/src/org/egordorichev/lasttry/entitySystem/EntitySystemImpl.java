package org.egordorichev.lasttry.entitySystem;

import com.badlogic.gdx.Gdx;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.core.context.InjectionHelper;
import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitysystem.component.CodeGenComponentManager;
import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.entity.inmemory.InMemoryEntityManager;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventHandler;
import org.terasology.entitysystem.event.EventResult;
import org.terasology.entitysystem.event.impl.EventProcessor;
import org.terasology.entitysystem.event.impl.EventProcessorBuilder;
import org.terasology.entitysystem.index.ComponentIndexes;
import org.terasology.entitysystem.index.Index;
import org.terasology.entitysystem.transaction.TransactionManager;
import org.terasology.valuetype.ImmutableCopy;
import org.terasology.valuetype.TypeHandler;
import org.terasology.valuetype.TypeLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
