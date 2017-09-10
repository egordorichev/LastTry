package org.egordorichev.lasttry.registry;

public final class CoreRegistry {
    private static Context context;

    public CoreRegistry() {
    }

    public static void setContext(Context context){
        CoreRegistry.context = context;
    }

    public static  <T> void bindProvider(Class<? extends T> classz,Provider<T> provider){
        if(context != null){
            context.bindProvider(classz,provider);
        }
    }

    public static  <T> T bindInstance(Class<? extends T> classz,T object){
        if(context == null){
            return null;
        }
        return context.bindInstance(classz,object);
    }
    public static  <T> void bindeInterface(Class<T> interfaceType, Class<? extends T> implementationType) throws IllegalArgumentException{
        if(context != null){
            context.bindeInterface(interfaceType,implementationType);
        }
    }


}
