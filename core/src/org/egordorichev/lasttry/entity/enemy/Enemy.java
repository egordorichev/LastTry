package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.world.biome.Biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Enemy extends Entity {
    /**
     * Defined enemies
     */
    public static HashMap<Short, Class<? extends Enemy>> ENEMY_CACHE = new HashMap<>();

    /**
     * Max Ai for this enemy
     */
    protected static int maxAi;

    static {
        define(EnemyID.greenSlime, GreenSlime.class);
        define(EnemyID.blueSlime, BlueSlime.class);
        define(EnemyID.eyeOfCthulhu, EyeOfCthulhu.class);
        define(EnemyID.zombie, Zombie.class);

    }

    /**
     * Current Ai counter
     */
    protected int currentAi;
    /**
     * Enemy id
     */
    protected int id;
    /**
     * Enemy drops
     */
    protected List<Drop> drops = new ArrayList<>();
    /**
     * Animations
     */
    protected Animation[] animations;

    /**
     * Each biome has a maxinum number of enemies limit.  When deciding what enemy to spawn next in a biome, the
     * spawn weight of multiple enemies are added till the maximum number of enemies in the biome is complete.
     */
    private int spawnWeight;

    /**
     * Specifying the biome where the enemy spawns at.
     */
    private Biome.BiomeSpawnIdentifier biomeSpawnId;

    //TODO Should these parameters be replaced with an enum that encapsulates the stats?
    public Enemy(short id, int maxHp, int defense, int damage, int spawnWeight, Biome.BiomeSpawnIdentifier biomeSpawnId) {
        super(maxHp, damage, defense);

        this.animations = new Animation[State.values().length];
        this.id = id;
    }

    public Enemy(short id, Biome.BiomeSpawnIdentifier biomeSpawnId) {
        //TODO Should parameters be converted into an enum?
        //TODO Handle the 'spawnWeight' for a 'Boss' level enemy
        this(id, 10, 0, 5, 1, biomeSpawnId);

        this.animations = new Animation[State.values().length];
        this.id = id;
    }

    public static void define(short id, Class<? extends Enemy> enemy) {
        // TODO: handle duplicates
        LastTry.debug("Defined [" + id + "] as " + enemy.getSimpleName());
        ENEMY_CACHE.put(id, enemy);
    }

    public static Enemy create(short id) {
        try {
            Class<? extends Enemy> aClass = ENEMY_CACHE.get(id);

            if (aClass != null) {
                return aClass.newInstance();
            } else {
                LastTry.log.warn("Enemy with id " + id + " is not found");
                return null;
            }
        } catch (Exception exception) {
            LastTry.handleException(exception);
            return null;
        }
    }

    @Override
    public void render() {
        this.animations[this.state.getId()].render(this.renderBounds.x, LastTry.world.getHeight() * Block.TEX_SIZE
                        - this.renderBounds.y - this.renderBounds.height, this.renderBounds.width, this.renderBounds.height,
                (this.direction == Direction.RIGHT), false);
    }

    @Override
    public void update(int dt) {
        super.update(dt);

        this.animations[this.state.getId()].update();

        if (LastTry.player.getHitbox().intersects(this.getHitbox())) {
            this.onPlayerCollision(LastTry.player);
        }

        // this.animations[this.state.getId()].update(dt);
        this.updateAI();
    }

    public void updateAI() {
        this.currentAi++;

        if (this.currentAi >= this.maxAi) {
            this.currentAi = 0;
        }
    }

    @Override
    public void onSpawn() {

    }

    @Override
    public void onDeath() {
        // On death, drop items in world.
        for (Drop drop : this.drops) {
            if (drop.getChance().roll()) {
                DroppedItem droppedItem = new DroppedItem(drop.createHolder());

                LastTry.entityManager.spawn(droppedItem, (int) this.getCenterX(),
                    (int) this.getCenterY());
            }
        }
    }

    /**
     * Called when the entity collides with player.
     */
    protected void onPlayerCollision(Player player) {
        // TODO
    }

    public int getId() {
        return this.id;
    }
}