package org.egordorichev.lasttry.crafting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Recipes {
	private static final Logger logger = LoggerFactory.getLogger(Recipes.class);
	/*
	 * Recipes storage
	 */
	public static HashMap<String, Recipe> RECIPE_CACHE = new HashMap<>();

	/**
	 * Looks up all recipes with given material
	 *
	 * @param material Material, included in recipes
	 * @return All recipes with given material
	 */
	public static Recipe[] getAllForMaterial(String material) {
		ArrayList<Recipe> recipes = new ArrayList<>();

		for (Recipe recipe : RECIPE_CACHE.values()) {
			for (Recipe.Holder m : recipe.getMaterials()) {
				if (Objects.equals(m.item, material)) {
					recipes.add(recipe);
					break;
				}
			}
		}

		return recipes.toArray(new Recipe[0]);
	}

	/**
	 * Loads recipes
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			logger.error("Trying to load recipes before bootstrap");
			return;
		}

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/recipes.json"));

			for (JsonValue recipe : root) {
				try {
					RECIPE_CACHE.put(recipe.name(), Recipe.load(recipe));
				} catch (Exception exception) {
					logger.error(exception.getMessage());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Failed to load recipes");
		}
	}
}