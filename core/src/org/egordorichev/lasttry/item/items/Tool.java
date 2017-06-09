package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.components.PhysicsComponent.Direction;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;

import java.util.List;

public class Tool extends Item {
	/**
	 * Maximum force <i>(Velocity in any given direction)</i> that an entity can
	 * be sent flying back due to being hit.
	 */
	public static final float KNOCKBACK_MAX_POWER = 10F;
	/**
	 * Boolean indicating if the item is swung automatically when the primary
	 * action key is held down.
	 */
	protected boolean autoSwing;
	/**
	 * Chance in percent that a strike will be critical.
	 * <ul>
	 * <li>100 = Always</li>
	 * <li>0 = Never</li>
	 * </ul>
	 */
	protected float criticalStrikeChance;
	/**
	 * The base damage delt to entities when stuck by the tool.
	 */
	public float baseDamage;
	/**
	 * The tool's effectiveness against materials requiring axe,pickaxe,and
	 * hammer tools.
	 */
	public ToolPower power = ToolPower.DEFAULT;

	public Tool(String id) {
		super(id);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		if (root.has("speed")) {
			this.useDelayMax = root.getInt("speed");
		}
		if (root.has("damage")) {
			this.baseDamage = root.getInt("damage");
		}
		if (root.has("power")) {
			int[] pow = root.get("power").asIntArray();
			this.power = new ToolPower(pow[0], pow[1], pow[2]);
		}
	}

	@Override
	public boolean use() {
		if (!this.isReady()) {
			return false;
		}

		this.useDelay = this.useDelayMax;

		return this.onUse();
	}

	@Override
	protected void onUpdate(InventoryOwner<?> owner) {
		super.onUpdate(owner);
		// Check if entities should be hit.
		this.onToolAttack(owner);
	}

	/**
	 * Returns the tool's effectiveness against materials mined by pickaxes.
	 *
	 * @return Tool effectiveness.
	 */
	public int getPickaxePower() {
		return this.power.pickaxe;
	}

	/**
	 * Returns the tool's effectiveness against materials mined by axes.
	 *
	 * @return Tool effectiveness.
	 */
	public int getAxePower() {
		return this.power.axe;
	}

	/**
	 * Returns the tool's effectiveness against materials mined by hammers.
	 *
	 * @return Tool effectiveness.
	 */
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
		// TODO;
		return 0;
	}

	public Rarity getRarity() {
		return this.rarity;
	}

	public void onToolAttack(InventoryOwner<?> owner) {
		Creature cowner = (Creature) owner;

		// Get the list of enemies
		List<Creature> activeEnemies = Globals.entityManager.getCreatureEntities();

		// Get the hitbox that the tool takes up
		Rectangle equippedPlayerHitBox = generateToolHitbox();

		// Stream the entities, check if they can be attacked.
		// If attackable, attack them and apply knockback force.
		activeEnemies.stream().forEach(enemy -> {
			if (!enemy.isInvulnrable() && equippedPlayerHitBox.intersects(enemy.physics.getHitbox())) {
				int damage = this.calculateDamageToInflict(enemy);
				applyKnockback(cowner, enemy, damage);
				inflictDamageOnEnemy(enemy, damage);
			}
		});
	}

	private void applyKnockback(Creature attacker, Creature enemy, int damage) {
		float knockPower = damage * -0.04F;
		Vector2 diff = attacker.physics.getPosition().cpy().sub(enemy.physics.getPosition().cpy());
		Vector2 force = diff.scl(knockPower);
		force.limit(KNOCKBACK_MAX_POWER);
		enemy.physics.getVelocity().add(force);
	}

	private void inflictDamageOnEnemy(final Creature enemy, int damage) {
		enemy.hit(damage);
	}

	private Rectangle generateToolHitbox() {
		// Distance to offset from the player
		final int offsetDistance = (int) Math.round(Block.SIZE * 1.1);
		// Direction the player is facing
		final int dir = Globals.getPlayer().physics.getDirection() == Direction.LEFT ? -1 : 1;
		final Rectangle playerHitBox = Globals.getPlayer().physics.getHitbox();
		final TextureRegion itemTextureRegion = this.getTextureRegion();
		final Rectangle toolHitBox = new Rectangle(0, 0, itemTextureRegion.getRegionWidth(),
				itemTextureRegion.getRegionHeight());
		// Calculate hitbox with proper offsets
		final Rectangle finalHitbox = new Rectangle(playerHitBox.x + toolHitBox.x, playerHitBox.y + toolHitBox.y,
				playerHitBox.width + toolHitBox.width, playerHitBox.height + toolHitBox.height);
		return finalHitbox.offset(dir * offsetDistance, 0);
	}

	private int calculateDamageToInflict(final Creature enemy) {
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