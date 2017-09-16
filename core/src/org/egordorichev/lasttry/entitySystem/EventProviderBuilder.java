package org.egordorichev.lasttry.entitySystem;

import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitysystem.event.Event;
import org.terasology.entitysystem.event.EventHandler;
import org.terasology.entitysystem.event.impl.EventProcessor;
import org.terasology.entitysystem.event.impl.EventProcessorBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class EventProviderBuilder {
    private static final Logger logger = LoggerFactory.getLogger(EventProviderBuilder.class);

    private final EventProcessorBuilder processorBuilder;

    public EventProviderBuilder(){
       processorBuilder = EventProcessor.newBuilder();
    }


    public <T extends ComponentSystem>T addSystem(Context context, Class<T> system) {
        T componentSystem = InjectionHelper.createWithConstructorInjection(system, context);
        InjectionHelper.inject(componentSystem);

        assert componentSystem != null;
        Method[] methods = componentSystem.getClass().getDeclaredMethods();
        for (Method method : methods) {

            Parameter[] params = method.getParameters();
            if (params.length == 2) {
                BindEventHandler eventHandlerBinder = method.getAnnotation(BindEventHandler.class);
                if (eventHandlerBinder != null) {
                    Class<? extends Event> eventType =  (Class<? extends Event>) params[0].getType();
                    EventHandler eventHandler = (event, entity) -> {
                        try {
                            method.invoke(event, entity);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            logger.error("bounded method should follow handler(Event,Entity)", e);
                            e.printStackTrace();
                        }
                        return null;
                    };
                    processorBuilder.addHandler(eventHandler, componentSystem.getClass(),eventType, eventHandlerBinder.filter());
                }
            } else {
                logger.error("unknown event handler with parameters: {}", params);
            }
        }
        return componentSystem;
    }

    public EventProcessorBuilder getEventProcessorBuilder(){
        return processorBuilder;
    }

    public EventProvider build(){
        return  new EventProviderImpl(this.processorBuilder.build());
    }
}
