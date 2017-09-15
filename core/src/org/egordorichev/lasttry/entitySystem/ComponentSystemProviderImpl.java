package org.egordorichev.lasttry.entitySystem;

import org.egordorichev.lasttry.entitySystem.componentSystem.BindEventHandler;
import org.egordorichev.lasttry.entitySystem.componentSystem.ComponentSystem;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.ContextImpl;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitysystem.core.EntityManager;
import org.terasology.entitysystem.event.EventHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ComponentSystemProviderImpl implements ComponentSystemProvider {
    private static final Logger logger = LoggerFactory.getLogger(ComponentSystemProvider.class);

    private EntityManager entityManager;

    public ComponentSystemProviderImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public <T extends ComponentSystem>T RegisterSystem(Context context, Class<T> system){
        T result = InjectionHelper.createWithConstructorInjection(system,context);
        InjectionHelper.inject(result);
        registerEventHandlers(result);
        return result;
    }

    private  void registerEventHandlers(ComponentSystem componentSystem){
        Method[] methods = componentSystem.getClass().getDeclaredMethods();
        for(Method method : methods ) {
            if (method.getAnnotation(BindEventHandler.class) != null) {

                Parameter[] parameters = method.getParameters();
                if(parameters.length == 2) {
                    for (Parameter param : method.getParameters()) {
                    }
                }
                //EventHandler<TestEvent> sampleHandler = mock(EventHandler.class);
            }
        }
    }
}
