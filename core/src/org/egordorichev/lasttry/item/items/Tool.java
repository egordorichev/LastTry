package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.util.Rectangle;

import java.util.List;

public class Tool extends Item {
	protected boolean autoSwing;
	protected float criticalStrikeChance;
	protected float baseDamage; // All tools have melee damage
	protected ToolPower power;

	public Tool(short id, String name, Rarity rarity, float baseDamage, ToolPower power,
	        int useSpeed, Texture texture) {

		super(id, name, rarity, texture);

		this.criticalStrikeChance = 4.0f;
		this.autoSwing = false;
		this.useDelay = 0.0f;
		this.baseDamage = baseDamage;
		this.power = power;
		this.useSpeed = useSpeed;
	}

	public Tool(short id, String name, float baseDamage, ToolPower power,
	       int useSpeed, Texture texture) {

		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
	}

	@Override
	public boolean use() {
		if (!this.isReady()) {
			return false;
		}

		this.useDelay = this.useSpeed;
		return this.onUse();
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();

		handleToolAttack();
	}

	public int getPickaxePower() {
		return this.power.pickaxe;
	}

	public int getAxePower() {
		return this.power.axe;
	}

	public int getHammerPower() {
		return this.power.axe;
	}

	@Override
	public boolean isAutoUse() {
		return this.autoSwing;
	}

	public float getCriticalStrikeChance() {
		return this.criticalStrikeChance;
	}

	public float getBaseDamage() {
		return this.baseDamage;
	}

	protected float getCurrentAngle() {
		return 0; // TODO;
	}

	public Rarity getRarity() {
		return this.rarity;
	}

    /**
     *  Uses the tool to attack and handles calculations needed.
     */
    private void handleToolAttack() {
        //Retrieve active enemies
        List<Enemy> activeEnemies = LastTry.entityManager.retrieveEnemyEntities();

        //Retrieve equipped player hitbox
        Rectangle equippedPlayerHitBox = generateEquippedPlayerHitBox();

        //Loop through active enemies to check if enemy is not invulnerable then check if there is intersection between hitboxes.
        activeEnemies.stream().forEach(enemy -> {

            if(enemy.isEntityInvulnerable()==false)
            {
                if (equippedPlayerHitBox.intersects(enemy.getHitbox())) {

                    inflictDamageOnEnemy(enemy);
                }
            }
        });
    }

	/**
	 * Generates a Rectangle object signifying a hitbox that encompasses both the Player's personal hitbox and
	 * the hitbox of the equipped weapon.
	 *
	 * @return Rectangle object signifying equipped player hitbox.
	 */
	private Rectangle generateEquippedPlayerHitBox() {
	    //TODO Rewrite this entire method, it is wrong.  Hitbox should be rotated with the weapon.

		//Retrieve the player hitbox
		final Rectangle playerHitBox = LastTry.player.getHitbox();

		//Retrieve item texture, will be used for calculating item dimensions
		final Texture itemTexture = this.getTexture();

		//Generate weapon hitbox dimensions, player dimensions have not yet been taken into account
		final Rectangle toolHitBox = new Rectangle(0, 0, itemTexture.getWidth(), itemTexture.getHeight());

		//Generate a hitbox that combines the weapon reach and the player reach
		final Rectangle equippedPlayerHitbox = new Rectangle(playerHitBox.x+toolHitBox.x,
			playerHitBox.y+toolHitBox.y,
			playerHitBox.width+toolHitBox.width,
			playerHitBox.height+toolHitBox.height);

		return equippedPlayerHitbox;
	}

	/**
	 * Retrieves hp points to be removed from enemy and modifies hp of enemy object.
	 *
	 * @param enemy Enemy object representing enemy
	 */
	private void inflictDamageOnEnemy(final Enemy enemy) {
		LastTry.debug("Tool hitbox intersects with enemy hitbox");

		final int damageToInflict = this.calculateDamageToInflict(enemy);

		LastTry.debug("Calculate weapon damage is: "+damageToInflict);

		enemy.modifyHp(damageToInflict);

		enemy.setEntityToInvulnerableTemp(Entity.InvulnerableTimerConstant.WEAPONATTACK);

		//TODO Right now knock back velocity is a Magic Number. In the future, knockback will be based on weapon choice.
		enemy.applyKnockBackEffect(LastTry.player.getDirection(), 1);

	}

	/**
	 * Returns damage to inflict on enemy.
	 * Damage is a negative integer, calculated using 'baseDamage' of weapon, defense points of enemy and critical
	 * strike chance.
	 *
	 * @param enemy Enemy object
	 * @return Negative integer representing hp points to subtract from enemy
	 */
	private int calculateDamageToInflict(final Enemy enemy) {
		//Round float to int
		int weaponDamage = Math.round(this.baseDamage);

		//If crit strike chance is active, double
		if (this.criticalStrikeChanceActive()) {
			LastTry.debug("Critical strike chance active");
			weaponDamage = weaponDamage * 2;
		}

		//TODO Remove these debug statements
		LastTry.debug("Calculate weapon base damage is: "+weaponDamage+" defense of enemy is: "+enemy.getDefense());

		//Compensate for enemy defence points.
		weaponDamage = weaponDamage - enemy.getDefense();

		if (weaponDamage < 0) {
			LastTry.debug("No damage will be done as weapondamage is not greater than defense of enemy");
		}

		//Negate the value
		weaponDamage = Math.negateExact(weaponDamage);

		return weaponDamage;
	}


	/**
	 * Returns a boolean indicating whether a critical strike should be activated for the strike.
	 * Generates a random number, if random number is greater
	 *
	 * @return
	 */
	private boolean criticalStrikeChanceActive() {
	    //Generate random number, if less than critical strike chance percentage return true
		if (LastTry.random.nextInt(100)<criticalStrikeChance) {
			return true;
		}

		return false;
	}

}