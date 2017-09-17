package org.egordorichev.lasttry.core.context;

public class CoreRegistry {
	private static Context context;

	public static void setContext(Context context) {
		CoreRegistry.context = context;
	}

	public static Context getContext() {
		return context;
	}

	public static <T> T get(Class<? extends T> requestedType) {
		return context.get(requestedType);
	}

	public static <T> T bindInstance(Class<? extends T> classz, T object) {
		return context.bindInstance(classz, object);
	}

	public static <T> void bindProvider(Class<? extends T> classz, Provider<T> provider) {
		context.bindProvider(classz, provider);
	}
}