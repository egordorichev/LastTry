package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.util.Log;

public class Buffs {
    /** Buff lookup. Buff ID used as the index */
    public static final Buff[] BUFFS_CACHE = new Buff[BuffID.count];

    public static final Buff ammoReservation;
    public static final Buff archery;
    public static final Buff battle;
    public static final Buff builder;
    public static final Buff calm;
    public static final Buff crate;
    public static final Buff dangersense;
    public static final Buff endurance;
    public static final Buff featherfall;
    public static final Buff fishing;
    public static final Buff flipper;
    public static final Buff gills;
    public static final Buff gravitation;
    public static final Buff heartreach;
    public static final Buff hunter;
    public static final Buff inferno;
    public static final Buff invisibility;
    public static final Buff ironskin;
    public static final Buff lifeforce;
    public static final Buff lovestruck;
    public static final Buff magicPower;
    public static final Buff manaRegeneration;
    public static final Buff mining;
    public static final Buff nightOwl;
    public static final Buff obsidianSkin;
    public static final Buff rage;
    public static final Buff regeneration;
    public static final Buff shine;
    public static final Buff sonar;
    public static final Buff spelunker;
    public static final Buff summoning;
    public static final Buff swiftness;
    public static final Buff thorns;
    public static final Buff titan;
    public static final Buff warmth;
    public static final Buff waterWalking;
    public static final Buff wrath;
    public static final Buff wellFed;
    public static final Buff cozyFire;
    public static final Buff dryadsBlessing;
    public static final Buff happy;
    public static final Buff heartLamp;
    public static final Buff honey;
    public static final Buff peaceCandle;
    public static final Buff starInABottle;

    static{
        if (!Bootstrap.isLoaded()){
            Log.error("Trying to access Buffs class before bootstrap");
        }
        ammoReservation = new Buff(Language.text.get("ammoBuff"), Language.text.get("ammoBuffDescription"), Assets.getTextureRegion(Textures.ammoReservationBuff));
        archery = new Buff(Language.text.get("archeryBuff"), Language.text.get("archeryBuffDescription"), Assets.getTextureRegion(Textures.archeryBuff));
        battle = new Buff(Language.text.get("battleBuff"), Language.text.get("battleBuffDescription"),Assets.getTextureRegion(Textures.battleBuff));
        builder = new Buff("Builder", "Increases block and wall placement speed and range",Assets.getTextureRegion(Textures.builderBuff));
        calm = new Buff("Calm", "Enemies will be less aggressive towards players", Assets.getTextureRegion(Textures.calmBuff));
        crate = new Buff("Crate", "Increases chance to get a crate", Assets.getTextureRegion(Textures.crateBuff));
        dangersense = new Buff("Dangersense", "You can see nearby hazards", Assets.getTextureRegion(Textures.dangersenseBuff));
        endurance = new Buff("Endurance", "10% reduced damage", Assets.getTextureRegion(Textures.enduranceBuff));
        featherfall = new Buff("Featherfall", "Press UP or DOWN to control speed of descent", Assets.getTextureRegion(Textures.featherfallBuff));
        fishing = new Buff("Fishing", "Increased fishing level", Assets.getTextureRegion(Textures.fishingBuff));
        flipper = new Buff("Flipper", "Move like normal in water", Assets.getTextureRegion(Textures.flipperBuff));
        gills = new Buff("Gills", "Breathe water instead of air", Assets.getTextureRegion(Textures.gillsBuff));
        gravitation = new Buff("Gravitation", "Press UP or DOWN to reverse gravity", Assets.getTextureRegion(Textures.gravityBuff));
        heartreach = new Buff("Heartreach", "Increased heart pickup range", Assets.getTextureRegion(Textures.heartreachBuff));
        hunter = new Buff("Hunter", "Shows the location of enemies", Assets.getTextureRegion(Textures.hunterBuff));
        inferno = new Buff("Inferno", "Nearby enemies are ignited", Assets.getTextureRegion(Textures.infernoBuff));
        invisibility = new Buff("Invisibility", "Grants invisibility", Assets.getTextureRegion(Textures.invisibilityBuff));
        ironskin = new Buff("Ironskin", "Increase defense by 8", Assets.getTextureRegion(Textures.ironskinBuff));
        lifeforce = new Buff("Lifeforce", "20% increased max life", Assets.getTextureRegion(Textures.lifeforceBuff));
        lovestruck = new Buff("Lovestruck", "You are in love!", Assets.getTextureRegion(Textures.lovestruckBuff));
        magicPower = new Buff("Magic Power", "20% increased magic damage", Assets.getTextureRegion(Textures.magicPowerBuff));
        manaRegeneration = new Buff("Mana Regeneration", "Increased mana regeneration", Assets.getTextureRegion(Textures.manaRegenerationBuff));
        mining = new Buff("Mining", "25% increased mining speed", Assets.getTextureRegion(Textures.miningBuff));
        nightOwl = new Buff("Night Owl", "Increased night vision", Assets.getTextureRegion(Textures.nightOwlBuff));
        obsidianSkin = new Buff("Obsidian Skin", "Immune to lava", Assets.getTextureRegion(Textures.obsidianSkinBuff));
        rage = new Buff("Rage", "10% increased critical strike chance", Assets.getTextureRegion(Textures.rageBuff));
        regeneration = new Buff("Regeneration", "Provides life regeneration", Assets.getTextureRegion(Textures.regenerationBuff));
        shine = new Buff("Shine", "Emmitting light", Assets.getTextureRegion(Textures.shineBuff));
        sonar = new Buff("Sonar", "You can see what's biting your hook", Assets.getTextureRegion(Textures.sonarBuff));
        spelunker = new Buff("Spelunker", "Shows the location of treasure and ore", Assets.getTextureRegion(Textures.spelunkerBuff));
        summoning = new Buff("Summoning", "Increases max number of minions", Assets.getTextureRegion(Textures.summoningBuff));
        swiftness = new Buff("Swiftness", "2% increased movement speed", Assets.getTextureRegion(Textures.swiftnessBuff));
        thorns = new Buff("Thorns", "Attackers also take damage", Assets.getTextureRegion(Textures.thornsBuff));
        titan = new Buff("Titan", "Increased knockback", Assets.getTextureRegion(Textures.titanBuff));
        warmth = new Buff("Warmth", "Reduces damage from cold sources", Assets.getTextureRegion(Textures.warmthBuff));
        waterWalking = new Buff("Water wallking", "Press DOWN to enter water", Assets.getTextureRegion(Textures.waterWalkingBuff));
        wrath = new Buff("Wrath", "10% increased damage", Assets.getTextureRegion(Textures.wrathBuff));
        wellFed = new Buff("Well Fed", "Minor improvements to all stats", Assets.getTextureRegion(Textures.wellFedBuff));
        cozyFire = new Buff("Cozy Fire", "Life regeneration is slightly increased", Assets.getTextureRegion(Textures.cozyFireBuff));
        dryadsBlessing = new Buff("Dryad's Blessing", "The power of nature protects you", Assets.getTextureRegion(Textures.dryadsBlessingBuff));
        happy = new Buff("Happy!", "Movement speed increased and enemy spawns reduced", Assets.getTextureRegion(Textures.happyBuff));
        heartLamp = new Buff("Heart Lamp", "Life regeneration is increased", Assets.getTextureRegion(Textures.heartLampBuff));
        honey = new Buff("Honey", "Life regeneration is increased", Assets.getTextureRegion(Textures.honeyBuff));
        peaceCandle = new Buff("Peace Candle", "Decreases enemy spawn rate", Assets.getTextureRegion(Textures.peaceCandleBuff));
        starInABottle = new Buff("Star in a Bottle", "Increased mana regeneration", Assets.getTextureRegion(Textures.starInABottleBuff));
    }

    public static void load() { }
}
