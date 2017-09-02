package org.egordorichev.lasttry.crafting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

public class Recipes {
	/*
	 * Recipes storage
	 */
	public static HashMap<String, Recipe> RECIPE_CACHE = new HashMap<>();

	/**
	 * Loads recipes
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to load recipes before bootstrap");
			return;
		}

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/recipes.json"));

			for (JsonValue recipe : root) {
				try {
					RECIPE_CACHE.put(recipe.name(), Recipe.load(recipe));
				} catch (Exception exception) {
					Log.error(exception.getMessage());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load recipes");
		}
	}
}