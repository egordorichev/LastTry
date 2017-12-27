package org.egordorichev.lasttry.entity.asset;

import java.util.HashMap;

public class AssetStorage<T> {
	/**
	 * Actual storage
	 */
	private HashMap<String, T> storage = new HashMap<>();

	/**
	 * Adds an asset to the storage
	 *
	 * @param id Asset ID
	 * @param value The asset
	 */
	public void add(String id, T value) {
		this.storage.put(id, value);
	}

	/**
	 * Searches for asset with given ID
	 *
	 * @param id Asset ID
	 * @return Asset
	 */
	public T get(String id) {
		return this.storage.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public <R> R getUnsafe(String id) {
		return (R) this.storage.get(id);
	}
}