package org.egordorichev.lasttry.crafting;

import com.badlogic.gdx.utils.JsonValue;

public class Recipe {
	/**
	 * Required crafting stations
	 */
	private String[] stations;
	/**
	 * Required materials
	 */
	private Holder[] materials;
	/**
	 * Result of crafting
	 */
	private Holder result;

	private static class Holder {
		public String item;
		public short count;
	}

	public Recipe() {

	}

	/**
	 * Loads recipe from json
	 * @param root Json root
	 * @throws Exception Exception containing parse error
	 */
	public void load(JsonValue root) throws Exception {
		if (root.has("stations")) {

		}

		if (!root.has("materials") || !root.has("result")) {
			throw new Exception("Recipe must have materials and result fields");
		}

		JsonValue materials = root.get("materials");
		this.materials = new Holder[materials.size];

		for (int i = 0; i < materials.size; i++) {
			this.materials[i] = new Holder();
			this.materials[i].count = root.get(i).getShort("count", (short) 1);
			this.materials[i].item = root.get(i).getString("id", "lt:dirt"); // ;D
		}

		JsonValue result = root.get("result");

		this.result = new Holder();
		this.result.count = result.getShort("count", (short) 1);
		this.result.item = result.getString("id", "lt:dirt"); // ;D
	}
}