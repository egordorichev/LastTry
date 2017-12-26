package org.egordorichev.lasttry.entity.engine;

import java.util.HashMap;
import java.util.Map;

public class SystemMessage {
	/**
	 * Extra data for message.
	 */
	private final Map<String, Object> data = new HashMap<>();
	/**
	 * Type of message.
	 */
	private final Type type;

	public SystemMessage(Type context) {
		this.type = context;
	}

	/**
	 * Builder with adding extra data to message.
	 * 
	 * @param key
	 *            Identifier of data.
	 * @param value
	 *            Data to store.
	 * @return Self.
	 */
	public SystemMessage with(String key, Object value) {
		data.put(key, value);
		return this;
	}

	/**
	 * @param key
	 *            Identifier of data.
	 * @return Extra message data associated with key.
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) data.get(key);
	}

	/**
	 * @param key
	 *            Identifier of data.
	 * @param fallback
	 *            Data to return if key does not link to a value.
	 * @return Extra message data associated with key. If no value exists for
	 *         key, return given parameter fallback.
	 */
	public <T> T getOr(String key, T fallback) {
		T value = get(key);
		if (value == null) return fallback;
		return value;
	}

	/**
	 * @return Type of message.
	 */
	public Type getType() {
		return type;
	}

	public enum Type {
		/**
		 * Entity was added/removed
		 */
		ENTITIES_UPDATED,
		/**
		 * Tile was updated (destroyed / placed)
		 */
		TILE_UPDATE,
		/**
		 * Wall was updated (destroyed / placed)
		 */
		WALL_UPDATE,
		/**
		 * Window was resized
		 */
		WINDOW_RESIZED,
		/**
		 * Game needs to save
		 */
		SAVE,
		/**
		 * Chunk loaded
		 */
		LOAD_CHUNK;

		public SystemMessage create() {
			return new SystemMessage(this);
		}
	}
}