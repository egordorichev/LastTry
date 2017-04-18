package org.egordorichev.lasttry.world.spawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javafx.util.Pair;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.AdvancedRectangle;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.environment.Event;
import org.w3c.dom.ls.LSException;

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

    private int minXGrid, minYGRID, maxXGRID, maxYGrid;

    private int diffBetweenSpawnedAndMaxSpawns;

    private List<Enemy> activeEnemyEntities = new ArrayList<>();

    private boolean day = false;

    public void update() {

        if(LastTry.environment.getCurrentBiome()==null){
            return;
        }

        //Get user biome
        this.biome = LastTry.environment.getCurrentBiome();

        //Retrieve spawn rate & max spawns
       // this.spawnRate = biome.getSpawnRate();
        //this.maxSpawns = biome.getSpawnMax();

        this.spawnTriggered();
    }


    //TODO Split method
    private void spawnTriggered() {

        final int maxSpawns = biome.getSpawnMax();

        //1 in 'origSpawnRate' chance of happening, so if spawn rate is '600'.  1 in 600 chance of occurring
        final int origSpawnRate = biome.getSpawnRate();

        int spawnRate = this.calculateSpawnRate(origSpawnRate);

        float percentChanceSpawnRate = 1/(float)spawnRate;

        //TODO Expensive calculation
        //TODO Reached 49 and got stuck as there is no monster with spawn weight of 1, wasting calculations.
        int spawnWeightOfActiveEnemies = this.calcSpawnWeightOfActiveEnemies();

        //If spawn weight of active enemies is greater than max spawns of biome we quit
        if(spawnWeightOfActiveEnemies>maxSpawns){
            return;
        }

        //TODO Split percentage calc into another method
        float percentageOfSpawnRateAndActiveMonsters;

        diffBetweenSpawnedAndMaxSpawns = maxSpawns - spawnWeightOfActiveEnemies;

        if(spawnWeightOfActiveEnemies==0){
            percentageOfSpawnRateAndActiveMonsters = 1;
        }else {
            percentageOfSpawnRateAndActiveMonsters = ((float)spawnWeightOfActiveEnemies / (float)maxSpawns) * 100;
        }

        float spawnRateFloat = this.applyMultiplierToSpawnRate(percentChanceSpawnRate, percentageOfSpawnRateAndActiveMonsters);

        if(!this.shouldEnemySpawn(spawnRateFloat)){
            return;
        }

        LastTry.debug("Monster probability successful");

        //TODO Can branch here if 0 enemies
        ArrayList<Enemy> eligibleEnemiesForSpawn = retrieveEligibleSpawnMonsters();

        if(eligibleEnemiesForSpawn.size()==0){

            return;
        }

        Enemy enemyToBeSpawned = retrieveRandomEnemy(eligibleEnemiesForSpawn);

        LastTry.debug("Enemy to be spawned is: "+enemyToBeSpawned);

        spawnEnemy(enemyToBeSpawned);

    }



    private void spawnEnemy(Enemy enemy) {

        GenericContainer.Pair<Integer> suitableXySpawnPoint = generateEligibleSpawnPoint();

        LastTry.entityManager.spawnEnemy((short)enemy.getId(), suitableXySpawnPoint.getFirst(), suitableXySpawnPoint.getSecond());

    }

    private GenericContainer.Pair<Integer> generateEligibleSpawnPoint() {

        //Add 6 blocks to min Y and 6 blocks to max X, to return a spawn point off screen.
        int xGridSpawnPoint = minXGrid+6; int yGridSpawnPoint = minYGRID + 6;

        int xPixelSpawnPoint = xGridSpawnPoint* Block.TEX_SIZE; int yPixelSpawnPoint = yGridSpawnPoint * Block.TEX_SIZE;

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set(xPixelSpawnPoint, yPixelSpawnPoint);

        return xyPoint;
    }

    private ArrayList<Enemy> retrieveEligibleSpawnMonsters() {

        ArrayList<Enemy> eligibleEnemiesForSpawn = new ArrayList<>();

        Enemy.triggerEnemyCacheCreation();

        Enemy.availEnemies.stream().forEach(enemy -> {
            if(enemy.canSpawn()&&enemy.getSpawnWeight()<diffBetweenSpawnedAndMaxSpawns){
                eligibleEnemiesForSpawn.add(enemy);
            }
        });

        return eligibleEnemiesForSpawn;
    }

    private Enemy retrieveRandomEnemy(ArrayList<Enemy> eligibleEnemiesForSpawning){
        int randomIndex = LastTry.random.nextInt(eligibleEnemiesForSpawning.size());
        return eligibleEnemiesForSpawning.get(randomIndex);
    }

    private boolean shouldEnemySpawn(float spawnRateFloat){
        //TODO Rethink probability
        float randomNumber = LastTry.random.nextFloat()/100;

        if(spawnRateFloat>randomNumber){
            return true;
        }

        return false;
    }

    private float applyMultiplierToSpawnRate(float spawnRate, float percentageOfSpawnRateAndActiveMonsters){

        double spawnRateDouble = spawnRate;

        if(percentageOfSpawnRateAndActiveMonsters<40){
            spawnRateDouble = spawnRateDouble * 0.6;
        }else if(percentageOfSpawnRateAndActiveMonsters<60){
            spawnRateDouble = spawnRateDouble * 0.8;
        }else{
            spawnRateDouble = spawnRateDouble * 0.9;
        }

        return (float)spawnRateDouble;
    }

    private int calculateSpawnRate(int spawnRate) {

        //Get active events
        ArrayList<Event> activeEvents = LastTry.environment.getCurrentEvents();

        spawnRate = this.calcSpawnRateBasedOnEvents(activeEvents, spawnRate);

        spawnRate = this.calcSpawnRateBasedOnItems(spawnRate);

        spawnRate = this.calcSpawnRateBasedOnTime(spawnRate);

        return spawnRate;
    }


    private int calcSpawnWeightOfActiveEnemies(){

       this.generateEnemiesInActiveArea();

        return this.activeEnemyEntities.stream().mapToInt(enemy->enemy.getSpawnWeight()).sum();
    }

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
    }

    /**
     * Retrieves enemies in the active area.
     *
     * @return A list of enemies int he active area.
     */
    private void generateEnemiesInActiveArea() {

        //Must clear the list each time, as it has no way of knowing if an entity has died so we must rebuild
        //each time to ensure we have an up to date list
        this.activeEnemyEntities.clear();

        this.generateMinMaxGridBlockValues();

        List<Enemy> enemyEntities = LastTry.entityManager.retrieveEnemyEntities();

        enemyEntities.stream().forEach(enemy -> {

            //TODO Rethink
            //Checks if the enemy is in the active area and if the enemy is not already in the list, it adds to the list
            if(this.isEnemyInActiveArea(enemy)&&this.activeEnemyEntities.contains(enemy)==false){
                this.activeEnemyEntities.add(enemy);
                //LastTry.debug("Enemy in active area of: "+enemy.getName());
            }
        });
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
        if(enemyBlockGridX>this.minXGrid&&enemyBlockGridX<this.maxXGRID){
            if(enemyBlockGridY<this.maxYGrid&&enemyBlockGridY>this.minYGRID){
                return true;
            }
        }

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


    private int calcSpawnRateBasedOnEvents(ArrayList<Event> activeEvents, int spawnRate){
        //TODO Implement spawn info in events
        return spawnRate;
    }

    private int calcSpawnRateBasedOnTime(int spawnRate){
        //TODO Implement spawn info based on day
        return spawnRate;
    }

    private int calcSpawnRateBasedOnItems(int spawnRate){
        //TODO Implement spawn rate altering based on items in environment
        return spawnRate;
    }

}
