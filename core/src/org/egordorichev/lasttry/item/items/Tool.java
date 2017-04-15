package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
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

	/**
	 * Checks if an enemy is in the proximity range of the equipped player hitbox and writes to console.
	 * Equipped player hitbox combines the player hitbox with the tool/weapon hitbox.
	 * @return
	 */
	@Override
	protected void onUpdate() {
		super.onUpdate();

		//Retrieve active enemies
		List<Enemy> activeEnemies = LastTry.entityManager.retrieveEnemyEntities();

		//Retrieve the player hitbox
		Rectangle playerHitBox = LastTry.player.getHitbox();

		//Retrieve item texture, will be used for calculating item dimensions
		Texture itemTexture = this.getTexture();

		//Generate weapon hitbox dimensions, player dimensions have not yet been taken into account
		Rectangle toolHitBox = new Rectangle(0, 0, itemTexture.getWidth(), itemTexture.getHeight());

		//Generate a hitbox that combines the weapon reach and the player reach
		Rectangle equippedPlayerHitbox = new Rectangle(playerHitBox.x+toolHitBox.x,
				playerHitBox.y+toolHitBox.y,
				playerHitBox.width+toolHitBox.width,
				playerHitBox.height+toolHitBox.height);

		//Loop through active enemies and write to console if equipped player with tool intersects
		activeEnemies.stream().forEach(enemy -> {
			if(equippedPlayerHitbox.intersects(enemy.getHitbox()))
			{
				LastTry.debug("Tool hitbox intersects with enemy jotnpx");
			}
		});
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
}