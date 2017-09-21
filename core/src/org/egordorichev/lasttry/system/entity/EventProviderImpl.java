package org.egordorichev.lasttry.system.entity;

import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventResult;
import org.terasology.entitysystem.event.impl.EventProcessor;

import java.util.Set;

public class EventProviderImpl implements EventProvider{
    private final EventProcessor eventProcessor;

    public EventProviderImpl(ComponentSystemManager componentSystemManager){
        this.eventProcessor = componentSystemManager.buildEventProcessor();
    }

    @Override
    public EventResult send(Event event, EntityRef entity) {
        return eventProcessor.send(event,entity);
    }

    @Override
    public EventResult send(Event event, EntityRef entity, Set<Class<? extends Component>> triggeringComponents) {
        return eventProcessor.send(event,entity,triggeringComponents);
    }
}
