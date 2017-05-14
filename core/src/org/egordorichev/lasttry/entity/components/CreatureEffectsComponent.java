package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.effect.Effect;
import org.egordorichev.lasttry.effect.EffectData;
import org.egordorichev.lasttry.entity.Creature;
import java.util.ArrayList;

public class CreatureEffectsComponent extends CreatureComponent {
    private Creature creature;
    protected ArrayList<EffectData> effects = new ArrayList<>();

    public CreatureEffectsComponent(Creature creature) {
        this.creature = creature;
    }

    public void render() {
	    for (int i = 0; i < this.effects.size(); i++) {
		    this.effects.get(i).render(10 + (i % 11) * 34, Gdx.graphics.getHeight() - 130);
	    }
    }

    public void applyEffect(final Effect effect, final int time) {
        //If effect already exists, simply update time
        effects.stream().forEach(effectData ->{
            if(effectData.getEffect() == effect){
                effectData.setTime(time);
                return;
            }
        });

        //Else add effect
        effects.add(new EffectData(this.creature, effect, time));
    }

    public void removeEffect(final Effect effect) {
        effects.removeIf(effectData -> effectData.getEffect()==effect);
    }

    public void update(final int dt) {
        for (int i = this.effects.size() - 1; i >= 0; i--) {
            this.effects.get(i).update(dt);

            if (this.effects.get(i).isDone()) {
                this.effects.remove(i);
            }
        }
    }
}
