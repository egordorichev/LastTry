package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();
    private List<Enemy> enemyEntities = new ArrayList<>();
    private List<Entity> clearList = new ArrayList<>();

    //Seconds
    public static final int ENEMY_DESPAWN_SWEEP_INTERVAL = 1;

    // private List<Gore> gores = new ArrayList<>();

    public EntityManager() {
        Util.runInThread(new Callable() {
            @Override
            public void call() {
                attemptDespawnEnemies();
            }
        }, ENEMY_DESPAWN_SWEEP_INTERVAL);
    }

    public void render() {
        // TODO: Only render on-screen entities.
        // Old code for doing so:
        /*
         * int gx = entity.getGridX(); int gy = entity.getGridY(); int w =
		 * entity.getGridWidth(); int h = entity.getGridHeight(); if ((gx > minX
		 * - w && gx < maxX + w) && (gy > minY - h && gy < maxY + h)) {
		 * entity.render(); }
		 */
        for (Entity entity : this.entities) {
            entity.render();
        }
    }

    public void update(int dt) {
        for (Entity entity : this.clearList) {
            this.entities.remove(entity);
        }

        this.clearList.clear();

        for (int i = this.entities.size() - 1; i >= 0; i--) {
	        Entity entity = this.entities.get(i);
            entity.update(dt);

            if (!entity.isActive()) {
                this.entities.remove(i);
            }
        }
    }

    public Entity spawn(Entity entity, int x, int y) {
        entity.spawn(x, y);
        this.entities.add(entity);
        return entity;
    }

    public Enemy spawnEnemy(short id, int x, int y) {
        Enemy enemy = Enemies.create(id);
        this.enemyEntities.add(enemy);
        this.spawn(enemy, x, y);
        return enemy;
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public void markForRemoval(Entity entity) {
        this.clearList.add(entity);
        //Maintaining a second list for quick enemy retrieval.
        if(entity instanceof Enemy){
            enemyEntities.remove(entity);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Enemy> getEnemyEntities() { return enemyEntities; }

    //Todo Will be rewritten to include NPCs
    private synchronized void attemptDespawnEnemies() {
        try{
            LastTry.debug.print("Starting despawn enemy process");

            //Iterator cannot be used here, as list size is being changed in another thread.
            //Iterator use here, will result in a NPE
            for(int i=0; i<this.enemyEntities.size(); i++){
                CreatureWithAI creatureWithAI = (CreatureWithAI)enemyEntities.get(i);

                //Acquire a read only lock, source: http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/
                ReadWriteLock readOnlyLock = new ReentrantReadWriteLock();

                readOnlyLock.readLock().lock();
                creatureWithAI.tryToDespawn();
                readOnlyLock.readLock().unlock();

            }

            LastTry.debug.print("Despawn enemy process complete");
        }catch (Exception e){
            LastTry.handleException(e);
        }

    }
}