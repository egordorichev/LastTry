package org.egordorichev.lasttry.core;

import org.egordorichev.lasttry.item.Items;

public class Bootstrap {
	private static boolean loaded = false;

	public static boolean isLoaded() {
		return loaded;
	}

	public static void load() {
		if (loaded) {
			return;
		}

		loaded = true;

		Items.load();
	}
}