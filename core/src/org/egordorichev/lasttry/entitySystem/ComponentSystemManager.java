package org.egordorichev.lasttry.entitySystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.google.common.collect.Lists;
import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.core.context.InjectionHelper;
import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.entitySystem.componentSystem.UpdatableSystem;
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
    private List<UpdatableSystem> updatableSystem = Lists.newArrayList();
    private final Context context;

    private boolean isInitialized = false;

    public ComponentSystemManager(Context context) {
        this.context = context;
    }

    public EventProcessor buildEventProcessor(){
        EventProcessorBuilder builder = EventProcessor.newBuilder();

        for(ComponentSystem system: systems) {
            Method[] methods = system.getClass().getDeclaredMethods();
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
                        builder.addHandler(eventHandler, system.getClass(), eventType, eventHandlerBinder.filter());
                    }
                } else {
                    logger.error("unknown event handler with parameters: {}", params);
                }
            }
        }
        return builder.build();
    }

    public void initialise(){
        if(!isInitialized) {
            for (ComponentSystem system : systems) {
                initializeSystem(system);
            }
            isInitialized = true;
        } else {
            logger.error("ComponentSystemManager got initialized twice");
        }
    }

    public void initializeSystem(ComponentSystem system){
        InjectionHelper.inject(system);
        system.Initialize();
    }

    public <T extends ComponentSystem>void bindSystem(Class<T> componentSystemType){
        T componentSystem = InjectionHelper.createWithConstructorInjection(componentSystemType, context);
        InjectionHelper.inject(componentSystem);
        assert componentSystem != null;
        bindSystem(componentSystem);
    }


    public void bindSystem(ComponentSystem system){
        context.bindInstance(system.getClass(),system);
        systems.add(system);

        if(system instanceof UpdatableSystem){
            updatableSystem.add((UpdatableSystem) system);
        }

        if(isInitialized){
            initializeSystem(system);
        }
    }

    public List<ComponentSystem> getSystems(){
        return  systems;
    }

    public List<UpdatableSystem> getUpdatableSystem() {
        return updatableSystem;
    }
}
