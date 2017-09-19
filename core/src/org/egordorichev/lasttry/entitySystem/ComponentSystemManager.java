package org.egordorichev.lasttry.entitySystem;

import com.google.common.collect.Lists;
import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.core.context.InjectionHelper;
import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventHandler;
import org.terasology.entitysystem.event.impl.EventProcessor;
import org.terasology.entitysystem.event.impl.EventProcessorBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class ComponentSystemManager {
    private static final Logger logger = LoggerFactory.getLogger(ComponentSystemManager.class);

    private List<ComponentSystem> systems = Lists.newArrayList();

    public ComponentSystemManager(Context context) {
        EventProcessorBuilder processorBuilder = EventProcessor.newBuilder();

        Reflections reflections = new Reflections("org.egordorichev.lasttry");
        for (Class<? extends ComponentSystem> componentSystem : reflections.getSubTypesOf(ComponentSystem.class)) {
            ComponentSystem system = buildComponentSystem(context, componentSystem, processorBuilder);
            context.bindProvider(system.getClass(), () -> system);
            systems.add(system);
        }
//        context.bindInstance(EventProvider.class,processorBuilder.build());
    }
    

    private <T extends ComponentSystem>T  buildComponentSystem(Context context, Class<T> system, EventProcessorBuilder builder) {
        T componentSystem = InjectionHelper.createWithConstructorInjection(system, context);
        InjectionHelper.inject(componentSystem);

        assert componentSystem != null;
        Method[] methods = componentSystem.getClass().getDeclaredMethods();
        for (Method method : methods) {

            Parameter[] params = method.getParameters();
            if (params.length == 2) {
                BindEventHandler eventHandlerBinder = method.getAnnotation(BindEventHandler.class);
                if (eventHandlerBinder != null) {
                    Class<? extends Event> eventType = (Class<? extends Event>) params[0].getType();
                    EventHandler eventHandler = (event, entity) -> {
                        try {
                            method.invoke(event, entity);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            logger.error("bounded method should follow handler(Event,Entity)", e);
                            e.printStackTrace();
                        }
                        return null;
                    };
                    builder.addHandler(eventHandler, componentSystem.getClass(), eventType, eventHandlerBinder.filter());
                }
            } else {
                logger.error("unknown event handler with parameters: {}", params);
            }
        }
        return componentSystem;
    }

}
