package org.egordorichev.lasttry.registry;

import com.google.common.collect.Maps;
import org.egordorichev.lasttry.registry.helpers.InjectionHelper;
import org.egordorichev.lasttry.registry.helpers.InstantiationHelper;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class Injector {
    private static final Logger logger = LoggerFactory.getLogger(Injector.class);

    private Map<Class, Object> singelton = Maps.newHashMap();


    private InstantiationHelper helper = new InstantiationHelper() {
        @Override
        public Object lookup(Context context, Class<?> lookup) {
            return null;
        }
    };

    public <T> T inject(T object, Context context) throws Exception {
        ContextImpl.ContextInjectorProxy contextInjectorProxy  = new ContextImpl.ContextInjectorProxy(context, new InjectionHelper() {
            @Override
            public void lookup(Context context, Object obj) {
                for (Field field : ReflectionUtils.getAllFields(object.getClass(), ReflectionUtils.withAnnotation(In.class))) {
                    Object value = context.get(field.getType());
                    if (value != null) {
                        try {
                            field.setAccessible(true);
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            logger.error("Failed to inject value {} into field {} of {}", value, field, object, e);
                        }
                    }
                }
            }
        },helper);
       contextInjectorProxy.get(object.getClass());
       return object;
    }
}
