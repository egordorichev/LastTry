package org.egordorichev.lasttry.world.spawn;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.AdvancedRectangle;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.spawn.logic.EnemySpawn;
import org.egordorichev.lasttry.world.spawn.logic.SpawnRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Spawn system that will spawn monsters in the gameworld based on certain rules.
 *
 * Created by LogoTie on 17/04/2017.
 */
public class SpawnSystem {

    private Biome biome;

    private int spawnRate, maxSpawns;

    private int minXGrid, minYGRID, maxXGRID, maxYGrid, maxXGridForActiveZone;

    private int diffBetweenSpawnedAndMaxSpawns;

    private List<Enemy> activeEnemyEntities = new ArrayList<>();

    public void update() {
        if(LastTry.environment.getCurrentBiome()==null){
            return;
        }
        //Get user biome
        this.biome = LastTry.environment.getCurrentBiome();
        this.spawnTriggered();
    }


    //TODO Split method
    private void spawnTriggered() {

        //Retrieve max spawns of biome
        final int maxSpawns = biome.getSpawnMax();

        final int origSpawnRate = biome.getSpawnRate();

        //Spawn rate is modified based on different factors such as time, events occurring.
        int spawnRate = SpawnRate.calculateSpawnRate(origSpawnRate);

        //Spawn rate refers to 1 in 'Spawn Rate' chance of a monster spawning.
        float percentChanceSpawnRate = 1/(float)spawnRate;

        ArrayList<Enemy> enemiesInActiveArea = this.generateEnemiesInActiveArea();

        //TODO Expensive calculation
        //TODO Reached 49 and got stuck as there is no monster with spawn weight of 1, wasting calculations.
        int spawnWeightOfActiveEnemies = EnemySpawn.calcSpawnWeightOfActiveEnemies(enemiesInActiveArea);

        //If spawn weight of active enemies is greater than max spawns of biome we quit
        if(spawnWeightOfActiveEnemies>maxSpawns){
            return;
        }

        //TODO Split percentage calc into another method
        float percentageOfSpawnRateAndActiveMonsters;

        int diffBetweenSpawnedAndMaxSpawns = maxSpawns - spawnWeightOfActiveEnemies;

        if(spawnWeightOfActiveEnemies==0){
            percentageOfSpawnRateAndActiveMonsters = 1;
        }else {
            percentageOfSpawnRateAndActiveMonsters = ((float)spawnWeightOfActiveEnemies / (float)maxSpawns) * 100;
        }

        float spawnRateFloat = SpawnRate.applyMultiplierToSpawnRate(percentChanceSpawnRate, percentageOfSpawnRateAndActiveMonsters);

        if(!EnemySpawn.shouldEnemySpawn(spawnRateFloat)){
            return;
        }

        LastTry.debug("Monster probability successful");

        //TODO Can branch here if 0 enemies
        ArrayList<Enemy> eligibleEnemiesForSpawn = EnemySpawn.retrieveEligibleSpawnEnemies(diffBetweenSpawnedAndMaxSpawns);

        if(eligibleEnemiesForSpawn.size()==0){
            return;
        }

        Enemy enemyToBeSpawned = retrieveRandomEnemy(eligibleEnemiesForSpawn);

        LastTry.debug("Enemy to be spawned is: "+enemyToBeSpawned);

        spawnEnemy(enemyToBeSpawned);

    }



    private void spawnEnemy(Enemy enemy) {

        GenericContainer.Pair<Integer> suitableXySpawnPoint = generateEligibleSpawnPoint();

        LastTry.debug("Monster about to be spawned");

        LastTry.debug("Monster is being spawned with block x point of: "+suitableXySpawnPoint.getFirst()/Block.TEX_SIZE+ " block y point of: "+suitableXySpawnPoint.getSecond()/Block.TEX_SIZE);

        LastTry.debug("Monster is being spawned with x point of: "+suitableXySpawnPoint.getFirst()+ "y point of: "+suitableXySpawnPoint.getSecond());

        LastTry.entityManager.spawnEnemy((short)enemy.getId(), suitableXySpawnPoint.getFirst(), suitableXySpawnPoint.getSecond());

    }

    //MOVE OUT
    private GenericContainer.Pair<Integer> generateEligibleSpawnPoint() {

        LastTry.debug("Generating eligible spawn point");

        //Generate inside the active zone
        int xGridSpawnPoint = maxXGridForActiveZone-30; int yGridSpawnPoint = minYGRID;

        int xPixelSpawnPoint = xGridSpawnPoint* Block.TEX_SIZE; int yPixelSpawnPoint = yGridSpawnPoint * Block.TEX_SIZE;

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set(xPixelSpawnPoint, yPixelSpawnPoint);

        return xyPoint;
    }




    private Enemy retrieveRandomEnemy(ArrayList<Enemy> eligibleEnemiesForSpawning){
        int randomIndex = LastTry.random.nextInt(eligibleEnemiesForSpawning.size());
        return eligibleEnemiesForSpawning.get(randomIndex);
    }



    //TODO Move out
    /**
     * Generates the max and minimum x,y values of the current screen the user is viewing.
     */
    private void generateMinMaxGridBlockValues() {
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        int tww = windowWidth / Block.TEX_SIZE;
        int twh = windowHeight / Block.TEX_SIZE;

        //We want to get the further most position of x on the screen, camera is always in the middle so we
        //divide total window width by 2 and divide by blcok size to get grid position
        int tcx = (int) (LastTry.camera.position.x - windowWidth/2) / Block.TEX_SIZE;

        //TODO Change on inversion of y axis
        //We are subtracting because of the inverted y axis otherwise it would be LastTry.camera.position.y+windowheight/2
        int tcy = (int) (LastTry.world.getHeight() - (LastTry.camera.position.y + windowHeight/2)
                / Block.TEX_SIZE);

        //Checking to make sure y value is not less than 0 - World generated will always start from 0,0 top left.
        this.minYGRID = Math.max(0, tcy - 2);
        this.maxYGrid = Math.min(LastTry.world.getHeight() - 1, tcy + twh + 3);

        //Checking to make y values is not less than 0
        this.minXGrid = Math.max(0, tcx - 2);
        this.maxXGRID = Math.min(LastTry.world.getWidth() - 1, tcx + tww + 2);

        //Active zone is 6 greater
        //TODO Must check that it is not out of bounds.
        this.maxXGridForActiveZone = this.maxXGRID + 25;
    }

    /**
     * Retrieves enemies in the active area.
     *
     * @return A list of enemies int he active area.
     */
    private ArrayList<Enemy> generateEnemiesInActiveArea() {

        //Must clear the list each time, as it has no way of knowing if an entity has died so we must rebuild
        //each time to ensure we have an up to date list
        ArrayList<Enemy> enemiesInActiveArea = new ArrayList<>();

        this.generateMinMaxGridBlockValues();

        List<Enemy> enemyEntities = LastTry.entityManager.retrieveEnemyEntities();

        enemyEntities.stream().forEach(enemy -> {

            //TODO Rethink
            //Checks if the enemy is in the active area and if the enemy is not already in the list, it adds to the list
            if(this.isEnemyInActiveArea(enemy)){
                enemiesInActiveArea.add(enemy);
                //LastTry.debug("Enemy in active area of: "+enemy.getName());
            }
        });

        return enemiesInActiveArea;
    }

    /**
     * Checks grid x and y of enemy, comparing with min x,y grid values calculated above.
     * If enemy grid x and y, is within the specified area returns true else false.
     *
     * @param enemy Enemy entity
     * @return boolean indicating whether enemy is in grid area
     */
    private boolean isEnemyInActiveArea(Enemy enemy) {

        //Get block co ordinates of enemy
        int enemyBlockGridX = enemy.getGridX();
        int enemyBlockGridY = enemy.getGridY();

        //TODO Change on inversion of y axis
        //Due to inverted y axis
        /**
         * If enemy block grid x is greater than minimum screen x and enemy block grid x less than max screen x
         * True
         * If enemy block grid y is less than max y grid and enemy block grid y greater than min y grid
         */
        if(enemyBlockGridX>=this.minXGrid&&enemyBlockGridX<=this.maxXGridForActiveZone){
            if(enemyBlockGridY<=this.maxYGrid&&enemyBlockGridY>=this.minYGRID){
                return true;
            }
        }

        LastTry.debug("Limits maxXGrid: "+this.maxXGridForActiveZone+" and limits min X grid: "+this.minXGrid);
        LastTry.debug("Pixels max X Grid: "+this.maxXGridForActiveZone*Block.TEX_SIZE+" and pixel limits X grid: "+this.minXGrid*Block.TEX_SIZE);
        LastTry.debug("Limits maxYGrid: "+this.maxYGrid+" and limits min Y grid: "+this.minYGRID);
        LastTry.debug("Pixels max Y Grid: "+this.maxYGrid*Block.TEX_SIZE+" and pixel limits Y grid: "+this.minYGRID*Block.TEX_SIZE);
        LastTry.debug("Enemy found out of active area with x of: "+enemyBlockGridX+" and y of: "+enemyBlockGridY);
        LastTry.debug("In pixels x is: "+enemyBlockGridX*Block.TEX_SIZE+" and y pixels is: "+enemyBlockGridY*Block.TEX_SIZE);

        return false;
    }


    /**
     * Generate rectangle with co ordinates where the player is in the center and a boundary between sides and player
     * is 6 blocks.
     */
    public void calcArea(){

        float xOfPLayer = LastTry.player.getCenterX();
        float yOfPlayer = LastTry.player.getCenterY();

        AdvancedRectangle advancedRectangle = new AdvancedRectangle(xOfPLayer, yOfPlayer, 6);

        if(advancedRectangle.allSidesInBoundary()){
            LastTry.debug("All sides in boundary");
            //advancedRectangle.debugSetItemsOnPoints();
        }
    }


    public void calculateNonSpawnSafePlayerArea(){
        //TODO Implement
    }




}
