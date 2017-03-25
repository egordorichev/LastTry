package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Assets;
import org.newdawn.slick.Image;

public abstract class Buff extends Effect {
	public static final Buff ammoReservation = new Buff("Ammo reservation", "Gives 20% chance to not consume ammo",
			Assets.ammoReservationBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff archery = new Buff("Archery", "20% increased arrow damage and speed",
			Assets.archeryBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff battle = new Buff("Battle", "Increased enemy spawn rate", Assets.battleBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff builder = new Buff("Builder", "Increases block and wall placement speed and range",
			Assets.builderBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff calm = new Buff("Calm", "Enemies will be less aggressive towards players",
			Assets.calmBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff crate = new Buff("Crate", "Increases chance to get a crate", Assets.crateBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff dangersense = new Buff("Dangersense", "You can see nearby hazards",
			Assets.dangersenseBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff endurance = new Buff("Endurance", "10% reduced damage", Assets.enduranceBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff featherfall = new Buff("Featherfall", "Press UP or DOWN to control speed of descent",
			Assets.featherfallBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff fishing = new Buff("Fishing", "Increased fishing level", Assets.fishingBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff flipper = new Buff("Flipper", "Move like normal in water", Assets.flipperBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff gills = new Buff("Gills", "Breathe water instead of air", Assets.gillsBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff gravitation = new Buff("Gravitation", "Press UP or DOWN to reverse gravity",
			Assets.gravityBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff heartreach = new Buff("Heartreach", "Increased heart pickup range",
			Assets.heartreachBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff hunter = new Buff("Hunter", "Shows the location of enemies", Assets.hunterBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff inferno = new Buff("Inferno", "Nearby enemies are ignited", Assets.infernoBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff invisibility = new Buff("Invisibility", "Grants invisibility",
			Assets.invisibilityBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff ironskin = new Buff("Ironskin", "Increase defense by 8", Assets.ironskinBuffTexture) {
		@Override
		public void apply(Entity entity) {
			entity.modifyDefense(8);
		}

		@Override
		public void remove(Entity entity) {
			entity.modifyDefense(-8);
		}
	};

	public static final Buff lifeforce = new Buff("Lifeforce", "20% increased max life", Assets.lifeforceBuffTexture) {
		@Override
		public void apply(Entity entity) {
			entity.modifyMaxHp((int) ((float) entity.getMaxHp()) / 100 * 20);
		}

		@Override
		public void remove(Entity entity) {
			entity.modifyMaxHp(-1 * (int) ((float) entity.getMaxHp()) / 100 * 20);
		}
	};

	public static final Buff lovestruck = new Buff("Lovestruck", "You are in love!", Assets.lovestruckBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff magicPower = new Buff("Magic Power", "20% increased magic damage",
			Assets.magicPowerBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff manaRegeneration = new Buff("Mana Regeneration", "Increased mana regeneration",
			Assets.manaRegenerationBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff mining = new Buff("Mining", "25% increased mining speed", Assets.miningBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff nightOwl = new Buff("Night Owl", "Increased night vision", Assets.nightOwlBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff obsidianSkin = new Buff("Obsidian Skin", "Immune to lava",
			Assets.obsidianSkinBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff rage = new Buff("Rage", "10% increased critical strike chance", Assets.rageBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff regeneration = new Buff("Regeneration", "Provides life regeneration",
			Assets.regenerationBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff shine = new Buff("Shine", "Emmitting light", Assets.shineBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff sonar = new Buff("Sonar", "You can see what's biting your hook", Assets.sonarBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff spelunker = new Buff("Spelunker", "Shows the location of treasure and ore",
			Assets.spelunkerBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff summoning = new Buff("Summoning", "Increases max number of minions",
			Assets.summoningBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff swiftness = new Buff("Swiftness", "2% increased movement speed",
			Assets.swiftnessBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff thorns = new Buff("Thorns", "Attackers also take damage", Assets.thornsBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff titan = new Buff("Titan", "Increased knockback", Assets.titanBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff warmth = new Buff("Warmth", "Reduces damage from cold sources", Assets.warmthBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff waterWalking = new Buff("Water wallking", "Press DOWN to enter water",
			Assets.waterWalkingBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff wrath = new Buff("Wrath", "10% increased damage", Assets.wrathBuffTexture) {
		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff wellFed = new Buff("Well Fed", "Minor improvements to all stats",
			Assets.wellFedBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	// TODO Weapon Imbue buffs

	// TODO: Equipment buffs

	// TODO: Activated furniture buffs

	public static final Buff cozyFire = new Buff("Cozy Fire", "Life regeneration is slightly increased",
			Assets.cozyFireBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff dryadsBlessing = new Buff("Dryad's Blessing", "The power of nature protects you",
			Assets.dryadsBlessingFireBuffTexture) {

		@Override
		public void apply(Entity entity) {
			entity.modifyDefense(8);
		}

		@Override
		public void remove(Entity entity) {
			entity.modifyDefense(-8);
		}
	};

	public static final Buff happy = new Buff("Happy!", "Movement speed increased and enemy spawns reduced",
			Assets.happyBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff heartLamp = new Buff("Heart Lamp", "Life regeneration is increased",
			Assets.heartLampBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff honey = new Buff("Honey", "Life regeneration is increased", Assets.honeyBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff peaceCandle = new Buff("Peace Candle", "Decreases enemy spawn rate",
			Assets.peaceCandleBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	public static final Buff starInABottle = new Buff("Star in a Bottle", "Increased mana regeneration",
			Assets.starInABottleBuffTexture) {

		@Override
		public void apply(Entity entity) {
			// TODO
		}

		@Override
		public void remove(Entity entity) {
			// TODO
		}
	};

	// TODO: Summons buffs

	public Buff(String name, String description, Image texture) {
		super(name, description, texture);
	}

	/**
	 * Abstact method, called on effect apply
	 * 
	 * @param entity
	 *            Entity, on witch it is applied
	 */
	public abstract void apply(Entity entity);

	/**
	 * Abstact method, called on effect remove
	 * 
	 * @param entity
	 *            Entity, from witch it is removed
	 */
	public abstract void remove(Entity entity);
}