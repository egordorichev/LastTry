package org.egordorichev.lasttry.registry;

import com.google.common.collect.Maps;
import org.egordorichev.lasttry.registry.helpers.InjectionHelper;
import org.egordorichev.lasttry.registry.helpers.InstantiationHelper;

import java.util.Map;


public class ContextImpl implements Context{
    public static class ContextProxy implements Context{
        private final InstantiationHelper instantiationHelper;
        private final Context context;
        public  ContextProxy(Context context,InstantiationHelper instantiationHelper){
            this.instantiationHelper = instantiationHelper;
            this.context = context;
        }

        @Override
        public Provider get(Class parent) {
            Provider provider =  this.context.get(parent);
            Object result =  provider.get();
            if(result instanceof Class<?>) {
                return () -> this.instantiationHelper.lookup(this, (Class<?>) result);
            }
            return () -> result;
        }

        @Override
        public <T> void bindProvider(Class<? extends T> classz, Provider<T> provider) {
            this.context.bindProvider(classz,provider);
        }

        @Override
        public <T> T bindInstance(Class<? extends T> classz, T object) {
            return this.context.bindInstance(classz,object);
        }

        @Override
        public <T> void bindeInterface(Class<T> interfaceType, Class<? extends T> implementationType) throws IllegalArgumentException {
            this.context.bindInstance(interfaceType,implementationType);
        }

    }

    public static class ContextInjectorProxy<T>{
        private final InjectionHelper injectionHelper;
        private final ContextProxy contextProxy;
        public  ContextInjectorProxy(Context context,InjectionHelper injectionHelper,InstantiationHelper instantiationHelper){
            this.contextProxy = new ContextProxy(context,instantiationHelper);
            this.injectionHelper = injectionHelper;
        }

        public void get(Object obj) {
            this.injectionHelper.lookup(contextProxy,  obj);
        }
    }

    private final Map<Class<?> , Provider> providers = Maps.newHashMap();

    private final Map<Class , Class> interfaceMapping = Maps.newHashMap();

    private ContextImpl parent = null;

    public void addParent(ContextImpl parent)
    {
        this.parent = parent;
    }

    @Override
    public <T> void bindProvider(Class<? extends T> classz,Provider<T> provider) {
        providers.put(classz,provider);
    }

    @Override
    public <T> T bindInstance(Class<? extends T> classz,T object){
        bindProvider(classz,() -> object);
        return object;
    }

    @Override
    public <T> void bindeInterface(Class<T> interfaceType, Class<? extends T> implementationType) throws IllegalArgumentException {
        if(interfaceType.isInterface())
        {
            interfaceMapping.put(interfaceType,implementationType);
        } else {
            throw new IllegalArgumentException("The given type is not an interface. Expecting the first argument to be an interface.");
        }
    }

    protected Class getInterfaceMapping(Class classz){
        Class mapping = null;
        if((mapping =  this.interfaceMapping.get(classz)) != null)
            return mapping;
        return this.parent.getInterfaceMapping(classz);
    }


    protected Provider getProvider(Class classz){
        Provider provider = null;
        if((provider =  this.providers.get(classz)) != null)
            return provider;
        return this.parent.getProvider(classz);
    }

    public Provider  get(Class parent) {
        Class lookup = parent;

        Provider provider = null;
        Class mapping = null;

        if((mapping = this.getInterfaceMapping(lookup)) != null)
            lookup = mapping;

        if((provider = this.getProvider(lookup)) != null) {
            Provider finalProvider = provider;
            return () -> finalProvider.get();
        }

        Class finalLookup = lookup;
        return () -> finalLookup;
    }
}
