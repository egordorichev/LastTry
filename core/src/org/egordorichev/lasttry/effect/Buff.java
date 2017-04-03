package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Textures;

public abstract class Buff extends Effect {
    public static final Buff ammoReservation = new Buff("Ammo reservation", "Gives 20% chance to not consume ammo",
            Textures.ammoReservationBuff) {

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
            Textures.archeryBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff battle = new Buff("Battle", "Increased enemy spawn rate", Textures.battleBuff) {
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
            Textures.builderBuff) {

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
            Textures.calmBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff crate = new Buff("Crate", "Increases chance to get a crate", Textures.crateBuff) {
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
            Textures.dangersenseBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff endurance = new Buff("Endurance", "10% reduced damage", Textures.enduranceBuff) {
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
            Textures.featherfallBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff fishing = new Buff("Fishing", "Increased fishing level", Textures.fishingBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff flipper = new Buff("Flipper", "Move like normal in water", Textures.flipperBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff gills = new Buff("Gills", "Breathe water instead of air", Textures.gillsBuff) {
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
            Textures.gravityBuff) {

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
            Textures.heartreachBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff hunter = new Buff("Hunter", "Shows the location of enemies", Textures.hunterBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff inferno = new Buff("Inferno", "Nearby enemies are ignited", Textures.infernoBuff) {
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
            Textures.invisibilityBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff ironskin = new Buff("Ironskin", "Increase defense by 8", Textures.ironskinBuff) {
        @Override
        public void apply(Entity entity) {
            entity.modifyDefense(8);
        }

        @Override
        public void remove(Entity entity) {
            entity.modifyDefense(-8);
        }
    };

    public static final Buff lifeforce = new Buff("Lifeforce", "20% increased max life", Textures.lifeforceBuff) {
        @Override
        public void apply(Entity entity) {
            entity.modifyMaxHp((int) ((float) entity.getMaxHp()) / 100 * 20);
        }

        @Override
        public void remove(Entity entity) {
            entity.modifyMaxHp(-1 * (int) ((float) entity.getMaxHp()) / 100 * 20);
        }
    };

    public static final Buff lovestruck = new Buff("Lovestruck", "You are in love!", Textures.lovestruckBuff) {
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
            Textures.magicPowerBuff) {
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
            Textures.manaRegenerationBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff mining = new Buff("Mining", "25% increased mining speed", Textures.miningBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff nightOwl = new Buff("Night Owl", "Increased night vision", Textures.nightOwlBuff) {
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
            Textures.obsidianSkinBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff rage = new Buff("Rage", "10% increased critical strike chance", Textures.rageBuff) {
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
            Textures.regenerationBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff shine = new Buff("Shine", "Emmitting light", Textures.shineBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff sonar = new Buff("Sonar", "You can see what's biting your hook", Textures.sonarBuff) {
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
            Textures.spelunkerBuff) {

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
            Textures.summoningBuff) {

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
            Textures.swiftnessBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff thorns = new Buff("Thorns", "Attackers also take damage", Textures.thornsBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff titan = new Buff("Titan", "Increased knockback", Textures.titanBuff) {
        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff warmth = new Buff("Warmth", "Reduces damage from cold sources", Textures.warmthBuff) {

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
            Textures.waterWalkingBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff wrath = new Buff("Wrath", "10% increased damage", Textures.wrathBuff) {
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
            Textures.wellFedBuff) {

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
            Textures.cozyFireBuff) {

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
            Textures.dryadsBlessingBuff) {

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
            Textures.happyBuff) {

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
            Textures.heartLampBuff) {

        @Override
        public void apply(Entity entity) {
            // TODO
        }

        @Override
        public void remove(Entity entity) {
            // TODO
        }
    };

    public static final Buff honey = new Buff("Honey", "Life regeneration is increased", Textures.honeyBuff) {

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
            Textures.peaceCandleBuff) {

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
            Textures.starInABottleBuff) {

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

    public Buff(String name, String description, Texture texture) {
        super(name, description, texture);
    }

    /**
     * Abstact method, called on effect apply
     *
     * @param entity Entity, on witch it is applied
     */
    public abstract void apply(Entity entity);

    /**
     * Abstact method, called on effect remove
     *
     * @param entity Entity, from witch it is removed
     */
    public abstract void remove(Entity entity);
}