package org.egordorichev.lasttry.injection;

public class CoreRegistry {
    static Context context;

    public static void setContext(Context context){
        CoreRegistry.context = context;
    }

    public <T> T bindInstance(Class<? extends T> classz,T object){
        return context.bindInstance(classz,object);
    }

    public <T> void bindInterface(Class<T> interfaceType, Class<? extends T> implementationType) {
        context.bindInterface(interfaceType,implementationType);
    }

}
