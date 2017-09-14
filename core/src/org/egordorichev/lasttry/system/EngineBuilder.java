package org.egordorichev.lasttry.system;

import com.google.common.collect.Queues;
import org.egordorichev.lasttry.entity.CreatureManagerImpl;
import org.egordorichev.lasttry.injection.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class EngineBuilder {
    private static final Logger logger = LoggerFactory.getLogger(EngineBuilder.class);

    private final Queue<Subsystem> subsystems = Queues.newArrayDeque();

    public EngineBuilder addSystem(Subsystem subsystem)
    {
        subsystems.add(subsystem);
        return this;
    }

    public void Start(Context context){
        while (subsystems.size() > 0){
            Subsystem subsystem = subsystems.remove();
            logger.debug("Loading System:" + subsystem.name());
            subsystem.load(context);
        }
    }

}
