package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.graphics.particle.DamageParticle;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.util.Rectangle;

import java.util.List;

public class Tool extends Item {
	protected boolean autoSwing;
	protected float criticalStrikeChance;
	protected float baseDamage; // All tools have melee damage
	protected ToolPower power;

	public Tool(short id, String name, Rarity rarity, float baseDamage, ToolPower power, int useSpeed,
			TextureRegion texture) {

		super(id, name, rarity, texture);

		this.criticalStrikeChance = 4.0f;
		this.autoSwing = false;
		this.useDelay = 0.0f;
		this.baseDamage = baseDamage;
		this.power = power;
		this.useSpeed = useSpeed;
	}

	public Tool(short id, String name, float baseDamage, ToolPower power, int useSpeed, TextureRegion texture) {

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

	private void handleToolAttack() {
		List<Enemy> activeEnemies = Globals.entityManager.getEnemyEntities();
		Rectangle equippedPlayerHitBox = generateEquippedPlayerHitBox();

		activeEnemies.stream().forEach(enemy -> {
			if (!enemy.isInvulnrable() && equippedPlayerHitBox.intersects(enemy.physics.getHitbox())) {
				inflictDamageOnEnemy(enemy);
			}
		});
	}

	private Rectangle generateEquippedPlayerHitBox() {
		final Rectangle playerHitBox = Globals.player.physics.getHitbox();
		final TextureRegion itemTextureRegion = this.getTextureRegion();
		final Rectangle toolHitBox = new Rectangle(0, 0, itemTextureRegion.getRegionWidth(),
				itemTextureRegion.getRegionHeight());

		final Rectangle equippedPlayerHitbox = new Rectangle(playerHitBox.x + toolHitBox.x,
				playerHitBox.y + toolHitBox.y, playerHitBox.width + toolHitBox.width,
				playerHitBox.height + toolHitBox.height);

		return equippedPlayerHitbox;
	}

	private void inflictDamageOnEnemy(final Enemy enemy) {
		int damage = this.calculateDamageToInflict(enemy);
		enemy.hit(damage);
	}

	private int calculateDamageToInflict(final Enemy enemy) {
		int weaponDamage = Math.round(this.baseDamage);

		if (this.criticalStrikeChanceActive()) {
			weaponDamage = weaponDamage * 2;
		}

		weaponDamage = weaponDamage - enemy.stats.getDefense() / 2;

		if (weaponDamage < 1) {
			weaponDamage = 1; // Min damage!
		}

		return weaponDamage;
	}

	private boolean criticalStrikeChanceActive() {
		if (LastTry.random.nextInt(100) < this.criticalStrikeChance) {
			return true;
		}

		return false;
	}
}