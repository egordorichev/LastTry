package org.egordorichev.lasttry.entitySystem;

import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityRef;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventResult;
import org.terasology.entitysystem.index.Index;

public interface EntitySystem {
    <T extends Component> Index filterEntitiesByComponent(Class<T>... elements);
    EventResult send(Event event, EntityRef entity);
}
