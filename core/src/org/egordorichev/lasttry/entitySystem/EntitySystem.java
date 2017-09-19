package org.egordorichev.lasttry.entitySystem;

import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.index.Index;

public interface EntitySystem {
    <T extends Component> Index getEntitiesFilterByComponent(Class<T>... elements);
}
