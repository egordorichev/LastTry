package org.egordorichev.lasttry.injection;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    final Map<Class<?> , Provider> providers = Maps.newHashMap();

    final Map<Class , Class> interfaceMapping = Maps.newHashMap();

    public <T> void bindProvider(Class<? extends T> classz,Provider<T> provider) {
        providers.put(classz,provider);
    }

    public <T> T bindInstance(Class<? extends T> classz,T object){
        bindProvider(classz,() -> object);
        return object;
    }

    public <T> void bindInterface(Class<T> interfaceType, Class<? extends T> implementationType) {
        if(interfaceType.isInterface())
        {
            interfaceMapping.put(interfaceType,implementationType);
        } else {
            logger.error("The given type is not an interface. Expecting the first argument to be an interface.");
        }
    }
}
