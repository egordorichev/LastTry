package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.entities.creature.AnimationComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.creature.HealthComponent;
import org.egordorichev.lasttry.graphics.animation.Animation;
import org.egordorichev.lasttry.graphics.animation.AnimationFrame;
import org.egordorichev.lasttry.util.log.Log;

public class CreatureFactory extends AssetFactory<Creature> {
	/**
	 * Creates new creature from data
	 *
	 * @param asset Data to parse
	 * @return New creature
	 */
	@Override
	public Creature parse(JsonValue asset) {
		Creature creature = new Creature();

		if (asset.has("components")) {
			this.parseComponents(creature, asset);
		}

		if (asset.has("health")) {
			HealthComponent health = creature.getComponent(HealthComponent.class);

			health.health = asset.getShort("health");
			health.healthMax = health.health;
		}

		if (asset.has("animation")) {
			this.parseAnimations(creature, asset);
		}

		SizeComponent size = creature.getComponent(SizeComponent.class);

		size.width = asset.getShort("w", (short) 16);
		size.height = asset.getShort("h", (short) 16);

		IdComponent id = creature.getComponent(IdComponent.class);
		id.id = asset.name();

		// TODO: other stuff, like mana

		return creature;
	}

	/**
	 * Creates new creature with given id
	 *
	 * @param id Creature id
	 * @return New creature
	 */
	public Creature create(String id) {
		JsonValue data = Assets.creaturesData.get(id);

		if (data == null) {
			return null;
		}

		return this.parse(data);
	}

	/**
	 * Adds components to creature
	 *
	 * @param creature Creature to modify
	 * @param asset Data
	 */
	private void parseComponents(Creature creature, JsonValue asset) {
		for (JsonValue component : asset.get("components")) {
			if (component.isString()) {
				try {
					Class type = Class.forName("org.egordorichev.lasttry." + component.toString());
					creature.addComponent(type);
				} catch (ClassNotFoundException exception) {
					Log.error("Class " + component.toString() + " is not found");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else {
				Log.warning("Non-string component in " + asset.name());
			}
		}
	}

	/**
	 * Parses animations
	 *
	 * @param creature Creature to modify
	 * @param asset Data
	 */
	private void parseAnimations(Creature creature, JsonValue asset) {
		AnimationComponent animation = creature.getComponent(AnimationComponent.class);
		JsonValue root = asset.get("animation");
		TextureRegion region = Assets.getTexture("creatures/" + asset.name().replace(':', '_'));

		if (region == null) {
			Log.error("Did not find texture for " + asset.name());
		}

		for (JsonValue data : root) {
			Animation anim = new Animation();

			for (JsonValue frameData : data) {
				AnimationFrame frame = new AnimationFrame();

				frame.texture = new TextureRegion(region.getTexture(), region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());

				if (frame.texture != null) {
					frame.texture.setRegionX(frame.texture.getRegionX() + frameData.getInt("x", 0));
					frame.texture.setRegionY(frame.texture.getRegionY() + frameData.getInt("y", 0));
					frame.texture.setRegionWidth(frameData.getInt("w", 16));
					frame.texture.setRegionHeight(frameData.getInt("h", 16));
				}

				frame.delta = frameData.getFloat("delta", 1.0f);
				anim.addFrame(frame);
			}

			animation.animations.put(data.name(), anim);
		}

		animation.current = animation.animations.get("idle");
	}
}