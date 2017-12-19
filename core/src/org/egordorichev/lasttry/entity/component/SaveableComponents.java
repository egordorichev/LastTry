package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.world.ClockComponent;
import org.egordorichev.lasttry.util.log.Log;

import java.util.HashMap;

/**
 * Stores saveable components names in save order
 */
public class SaveableComponents {
	/**
	 * The list
	 */
	public static String[] list = {
		"PositionComponent",
		"SizeComponent",
		"InventoryComponent",
		"ClockComponent"
	};

	public static HashMap<String, Class<? extends Component>> map = new HashMap<>();

	/**
	 * Just init static vars
	 */
	public static void init() {
		map.put("PositionComponent", PositionComponent.class);
		map.put("SizeComponent", SizeComponent.class);
		map.put("InventoryComponent", InventoryComponent.class);
		map.put("ClockComponent", ClockComponent.class);

		Log.debug("Testing map: " + map.get("PositionComponent"));
	}
}