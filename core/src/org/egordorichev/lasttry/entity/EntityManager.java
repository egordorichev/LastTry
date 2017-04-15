package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    /**
     * List of active entities.
     */
    private List<PhysicBody> entities = new ArrayList<>();
    /**
     * List of entities to remove.
     */
    private List<PhysicBody> clearList = new ArrayList<>();

    //TODO Convert the entities list to a map

    /**
     * TODO: gore
     */
    // private List<Gore> gores = new ArrayList<>();

    /**
     * Render entities.
     */
    public void render() {
        // TODO: Only render on-screen entities.
        // Old code for doing so:
        /*
         * int gx = entity.getGridX(); int gy = entity.getGridY(); int w =
		 * entity.getGridWidth(); int h = entity.getGridHeight(); if ((gx > minX
		 * - w && gx < maxX + w) && (gy > minY - h && gy < maxY + h)) {
		 * entity.render(); }
		 */
        for (PhysicBody entity : this.entities) {
            entity.render();
        }
    }

    /**
     * Update entities.
     *
     * @param dt Time passed in milliseconds since last update.
     */
    public void update(int dt) {
        // Remove entities marked for deletion
        for (PhysicBody entity : this.clearList) {
            this.entities.remove(entity);
        }
        this.clearList.clear();

        // Update
        for (int i = this.entities.size() - 1; i >= 0; i--) {
            PhysicBody entity = this.entities.get(i);
            entity.update(dt);

            if (!entity.shouldUpdate) {
                this.entities.remove(i);
            }
        }
    }

    /**
     * Spawns given entity at given position
     *
     * @param entity entity to spawn
     * @param x      spawn X coordinate
     * @param y      spawn Y coordinate
     */
    public PhysicBody spawn(PhysicBody entity, int x, int y) {
        entity.spawn(x, y);
        this.entities.add(entity);

        return entity;
    }

    /**
     * Spawns new enemy with given id at given position
     *
     * @param id enemy id
     * @param x  spawn X coordinate
     * @param y  spawn Y coordinate
     */
    public Enemy spawnEnemy(short id, int x, int y) {
        Enemy enemy = Enemy.create(id);
        this.spawn(enemy, x, y);
        return enemy;
    }

    /**
     * Removes entity from update list immediately.
     *
     * @param entity PhysicBody to remove.
     */
    public void remove(PhysicBody entity) {
        this.entities.remove(entity);
    }

    /**
     * Marks an entity for removal before the next update occurs.
     *
     * @param entity PhysicBody to remove.
     */
    public void markForRemoval(PhysicBody entity) {
        this.clearList.add(entity);
    }

    /**
     * Returns the list of entities.
     *
     * @return List of active entities
     */
    public List<PhysicBody> getEntities() {
        return entities;
    }

    /**
     * Returns a list of active enemies.
     * Does not return enemies that have been marked for deletion.
     *
     * @return List of enemies that have not been marked for deletion.
     */
    public List<Enemy> retrieveEnemyEntities()
    {
        List<Enemy> enemyEntities = new ArrayList<>();
        for(PhysicBody physicBody: entities)
        {
            //Check whether physicBody is an enemy type
            if(physicBody instanceof Enemy)
            {
                //Check that the object is not marked for removal
                if(!clearList.contains(physicBody))
                {
                 Enemy enemy = (Enemy)physicBody;
                 enemyEntities.add(enemy);
                }
            }
        }
        return enemyEntities;
    }
}