package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Creature;

public class EffectData {
    private Effects effect;
    private int currentTime;
    private int totalTime;
    private Creature creature;
    private boolean done;

    public EffectData(Creature creature, Effects effect, int time) {
        this.effect = effect;
        this.creature = creature;
        this.setTime(time);
        this.effect.apply(this.creature);
    }

    public void render(int x, int y) {
        this.effect.render(x, y);
    }

    public boolean update(int dt) {
        if (this.done) {
            return true;
        }

        this.currentTime--;
        this.effect.update(this.creature, dt);

        if (this.currentTime == 0) {
            this.done = true;
            this.effect.remove(this.creature);
            return true;
        }

        return false;
    }

    public void setTime(int time) {
        this.currentTime = time * 60;
        this.totalTime = time * 60;
    }

    public Effects getEffect() {
        return this.effect;
    }

    public boolean isDone() {
        return this.done;
    }
}