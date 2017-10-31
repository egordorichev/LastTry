package org.egordorichev.lasttry.entity.asset;

import org.egordorichev.lasttry.entity.asset.factories.ItemFactory;
import org.egordorichev.lasttry.entity.asset.storages.Items;

public class Assets {
	public static Items items = new Items();

	public static void load() {
		AssetLoader.load("items", new ItemFactory());
	}
}