package org.egordorichev.lasttry.entitySystem;

import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventResult;

import java.util.Collections;
import java.util.Set;

public interface EventProvider {
    EventResult send(Event event, EntityRef entity);
    EventResult send(Event event, EntityRef entity, Set<Class<? extends Component>> triggeringComponents);
}
