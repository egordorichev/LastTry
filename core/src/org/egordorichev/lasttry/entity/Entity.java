package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Effect;
import org.egordorichev.lasttry.effect.EffectData;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Entity extends PhysicBody {

    public enum InvulnerableTimerConstant
    {
        WEAPONATTACK(500);

        private int timerDuration;

        InvulnerableTimerConstant(final int timerDuration) {
        this.timerDuration = timerDuration;
        }

        public int getTimerDuration()
        {
            return this.timerDuration;
        }
    }

    /**
     * Timer object specifically for the setting an amount of time for which the Entity should be invulnerable
     */
    private Timer invulnerableTimer;

    /**
     * Boolean acting as flag, to signal whether the invulnerable timer is currently active
     */
    private boolean invulnerableTimerActive;

    /**
     * Stats
     */
    protected EntityStats stats = new EntityStats();

    /**
     * Invulnerability status. If enabled the entity will not be able to take damage
     */
    protected boolean invulnerable;

    /**
     * Effects, applied on entity
     */
    protected List<EffectData> effects = new ArrayList<>();

    public Entity(int maxHp, int damage, int defense) {
        super();

        this.stats.defense = defense;
        this.stats.hp = this.stats.maxHp = maxHp;
    }

    /**
     * Updates entity
     */
    @Override
    public void update(int dt) {
        super.update(dt);

        for (int i = this.effects.size() - 1; i >= 0; i--) {
            this.effects.get(i).update(dt);

            if (this.effects.get(i).isDone()) {
                this.effects.remove(i);
            }
        }

        // Regeneration: TODO

		/*
         * boolean still = this.velocity.x == 0 && this.velocity.y == 0;
		 *
		 *
		 * int buffs = 0; // TODO: count buffs int time = 1;
		 * // TODO: time since last hit
		 * last hit
		 *
		 * float regen = ((this.maxHp / 400.0f) * 0.85f + 0.15f) * time + buffs
		 * * ((still) ? 0.5f : 1.25f) (LastTry.world.isExpert() ? 1 : 0.5f);
		 *
		 * this.hp = Math.min(this.maxHp, this.hp + (int) regen);
		 */
    }

    /**
     * Adds effect to entity, but if it is already applied, updates it's time
     *
     * @param effect effect to apply
     * @param time   time of effect
     */
    public void addEffect(Effect effect, int time) {
        for (EffectData effectData : this.effects) {
            if (effectData.getEffect() == effect) {
                effectData.setTime(time);
                return;
            }
        }

        this.effects.add(new EffectData(this, effect, time));
    }

    /**
     * Removes effect from entity
     *
     * @param effect effect to apply
     */
    public void removeEffect(Effect effect) {
        for (int i = 0; i < this.effects.size(); i++) {
            EffectData effectData = this.effects.get(i);

            if (effectData.getEffect() == effect) {
                this.effects.remove(i);
            }
        }
    }

    /**
     * Update the entity's hp if it is not
     * {@link #invulnerable}.
     *
     * @param amount Value to increment hit-points by.
     */
    public void modifyHp(int amount) {

        //TODO Remove Debug statements.

        LastTry.debug("Received request to modify hp by: "+amount);

        if (!this.invulnerable) {

            LastTry.debug("Entity is not invulnerable");

            this.stats.hp = Math.min(Math.max(0, this.stats.hp + amount), this.stats.maxHp);

            LastTry.debug("Entity final hp is: "+this.stats.hp);

            if (this.stats.hp == 0) {
                LastTry.debug("Entity hp has reached 0");
                this.die();
            }
        }
        else
        {
            LastTry.debug("Entity is invulnerable, damage will not be registered");
        }
    }

    /**
     * Sets the entity to be invulnerable for a certain period of time, dictated by the constant parameter.
     *
     * @param invulnerableTempTimePeriod Enum containing time constant
     */
    public void setEntityToInvulnerableTemp(InvulnerableTimerConstant invulnerableTempTimePeriod)
    {
        //Check that the timer is not currently active and the entity is not currently invulnerable, to prevent needlessly
        //initializing a new timer object.
        if(invulnerableTimerActive==false&&invulnerable==false)
        {
            invulnerable = true;
            invulnerableTimerActive = true;

            LastTry.debug("Entity has been set to be invulnerable for a time period of: "+invulnerableTempTimePeriod.getTimerDuration());
            startTemporarilyInvulnerableTimer(invulnerableTempTimePeriod);
        }
    }

    /**
     * Sets and starts a timer, to make the entity temporarily invulnerable.
     *
     * @param timerConstant enum containing the time duration, to make the entity invulnerable for.
     */
    private void startTemporarilyInvulnerableTimer(InvulnerableTimerConstant timerConstant)
    {
        invulnerableTimer = new Timer();

        invulnerableTimer.schedule(new TimerTask() {
            @Override
            public void run(){
                LastTry.debug("Invulnerablity timer has expired");
                invulnerable = false;
                invulnerableTimerActive = false;
            }
        }, timerConstant.getTimerDuration());
    }

    /**
     * Update the entity's maximum hit-points.
     *
     * @param amount Value to increment max health by.
     */
    public void modifyMaxHp(int amount) {
        this.stats.maxHp = Math.max(0, this.stats.maxHp + amount);
    }

    /**
     * Update the entity's defense
     *
     * @param amount Value to set defense-points to
     */
    public void modifyDefense(int amount) {
        this.stats.defense = Math.max(0, this.stats.defense + amount);
    }

    /**
     * Return the entity's defense-points.
     *
     * @return Defense-points.
     */
    public int getDefense() {
        return this.stats.defense;
    }

    /**
     * Set the entity's defense-points.
     *
     * @param defense Value to set defense to.
     */
    public void setDefense(int defense) {
        this.stats.defense = defense;
    }

    /**
     * Set the entity's damage
     *
     * @param damage
     */
    public void setDamage(int damage) {
        this.stats.damage = damage;
    }

    /**
     * Return the entity's hit-points.
     *
     * @return Hit-points.
     */
    public int getHp() {
        return this.stats.hp;
    }

    /**
     * Set the entity's hit-points.
     *
     * @param hp Value to set hit-points to.
     */
    public void setHp(int hp) {
        this.stats.hp = hp;
    }

    /**
     * Return the entity's maximum hit-points.
     *
     * @return Maximum hit-points.
     */
    public int getMaxHp() {
        return this.stats.maxHp;
    }

    /**
     * Set the entity's maximum hit-points.
     *
     * @param maxHp Value to set maximum hit-points to.
     */
    public void setMaxHp(int maxHp) {
        this.stats.maxHp = maxHp;
    }

    /**
     * Returns a boolean indicating whether the entity is invulnerable
     *
     * @return Boolean
     */
    public boolean isEntityInvulnerable()
    {
        return invulnerable;
    }
}
