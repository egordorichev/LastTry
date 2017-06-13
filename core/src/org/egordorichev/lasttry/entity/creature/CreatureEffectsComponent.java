package org.egordorichev.lasttry.entity.creature;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.effect.Effect;
import org.egordorichev.lasttry.effect.EffectData;

import java.util.ArrayList;

public class CreatureEffectsComponent extends CreatureComponent {
	/**
	 * Effects list
	 */
	private ArrayList<EffectData> effects = new ArrayList<>();

	public CreatureEffectsComponent(Creature creature) {
		super(creature);
	}

	@Override
	public void render() {
		for (int i = 0; i < this.effects.size(); i++) {
			this.effects.get(i).render(10 + (i % 11) * 34, Gdx.graphics.getHeight() - 130);
		}
	}

	/**
	 * Applies effect on creature
	 *
	 * @param effect Effect to apply
	 * @param time   Time, for witch effect will be active
	 */
	public void applyEffect(final Effect effect, final int time) {
		// If effect already exists, simply update time
		effects.stream().forEach(effectData -> {
			if (effectData.getEffect() == effect) {
				effectData.setTime(time);
				return;
			}
		});

		// Else add effect
		effects.add(new EffectData(this.creature, effect, time));
	}

	/**
	 * Removes effect from creature
	 *
	 * @param effect Effect, to remove
	 */
	public void removeEffect(Effect effect) {
		effects.removeIf(effectData -> effectData.getEffect() == effect);
	}

	@Override
	public void update(final int dt) {
		for (int i = this.effects.size() - 1; i >= 0; i--) {
			this.effects.get(i).update(dt);

			if (this.effects.get(i).isDone()) {
				this.effects.remove(i);
			}
		}
	}
}
