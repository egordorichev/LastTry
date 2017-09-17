package org.egordorichev.lasttry.core.context;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ContextImpl implements Context {
	private static final Logger logger = LoggerFactory.getLogger(ContextImpl.class);
	private final Map<Class<?>, Provider> providers = Maps.newHashMap();
	private Context parent;

	public ContextImpl(Context context) {
		this.parent = context;
	}

	public ContextImpl() {

	}

	public void setParent(Context context) {
		this.parent = context;
	}

	public <T> void bindProvider(Class<? extends T> classz, Provider<T> provider) {
		providers.put(classz, provider);
	}

	public <T> T bindInstance(Class<? extends T> classz, T object) {
		bindProvider(classz, () -> object);
		return object;
	}

	@Override
	public <T> T get(Class<? extends T> requestedType) {
		Provider<T> provider = providers.get(requestedType);

		if (provider == null) {
			if (parent == null) {
				logger.error("can't find type mapping for {}", requestedType);
				return null;
			}

			return parent.get(requestedType);
		}

		return provider.get();
	}
}