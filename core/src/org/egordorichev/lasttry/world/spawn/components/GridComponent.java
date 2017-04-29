package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.world.WorldTime;
import sun.net.www.content.text.Generic;
import sun.nio.cs.ext.MacArabic;

import java.util.Optional;

/**
 * Created by Admin on 21/04/2017.
 */
public class GridComponent {

    private static WorldTime time;
    private static CircleAreaComponent cachedActiveAreaCircle;

    public static CircleAreaComponent retrieveActiveAreaCircle(WorldTime timeOfRequest) {
        //Logic to prevent having to recalculate active area circle every game tick (one game tick = one in game second)
        if(time==null&&cachedActiveAreaCircle==null){
            time = timeOfRequest;
            cachedActiveAreaCircle = generateActiveAreaCircle();
            return cachedActiveAreaCircle;
        }else{
            //If time is matching we return previously created circle
            if(SpawnUtilComponent.matchingTime(time, timeOfRequest)){
                assert cachedActiveAreaCircle != null: "Cached active area circle points to null!";
                return cachedActiveAreaCircle;
            }

            time = timeOfRequest;
            cachedActiveAreaCircle = generateActiveAreaCircle();
            return cachedActiveAreaCircle;
        }
    }

    private static CircleAreaComponent generateActiveAreaCircle() {

        CircleAreaComponent circleAreaComponent = new CircleAreaComponent();

        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        int tww = windowWidth / Block.SIZE;
        int twh = windowHeight / Block.SIZE;

        // We want to get the further most position of x on the screen, camera is always in the middle so we
        // divide total window width by 2 and divide by blcok size to get grid position
        int tcx = (int) (Camera.game.position.x - windowWidth/2) / Block.SIZE;

        // TODO Change on inversion of y axis
        // We are subtracting because of the inverted y axis otherwise it would be LastTry.camera.position.y+windowheight/2
        int tcy = (int) (LastTry.world.getHeight() - (Camera.game.position.y + windowHeight/2)/Block.SIZE);

        // Checking to make sure y value is not less than 0 - World generated will always start from 0,0 top left.
        circleAreaComponent.setMinYActiveAreaGridPoint(Math.max(0, tcy - 2));
        circleAreaComponent.setMaxYActiveAreaGridPoint(Math.min(LastTry.world.getHeight() - 1, tcy + twh + 3));

        // Checking to make y values is not less than 0
        circleAreaComponent.setMinXActiveAreaGridPoint(Math.max(0, tcx - 2));
        circleAreaComponent.setMaxXActiveAreaGridPoint(Math.min(LastTry.world.getWidth() - 1, tcx + tww + 2));

        // Active zone is 6 greater
        // TODO Must check that it is not out of bou
        //Spawn window width and height are approx 6 - 7 blocks bigger than active area window
        final int spawnWindowWidth = windowWidth + 200;
        final int spawnWindowHeight = windowHeight + 200;
        final int gridSpawnWindowWidth = spawnWindowWidth/Block.SIZE;
        final int gridSpawnWindowHeight = spawnWindowHeight/Block.SIZE;

        final double activeAreaCircleDiameter = Math.sqrt((gridSpawnWindowWidth*gridSpawnWindowWidth)+(gridSpawnWindowHeight*gridSpawnWindowHeight));
        final double activeAreaCircleRadius = activeAreaCircleDiameter/2;

        circleAreaComponent.setCircleRadius(activeAreaCircleRadius);
        circleAreaComponent.setCircleDiameter(activeAreaCircleDiameter);

        cachedActiveAreaCircle = circleAreaComponent;

        return circleAreaComponent;
    }


    private static GenericContainer.Pair<Integer> retrieveMinMaxDistances(CircleAreaComponent enemySpawnArea) {

        int distanceOfScreenBlocksHeight = Gdx.graphics.getHeight()/Block.SIZE;
        int distanceOfScreenBlocksWidth = Gdx.graphics.getWidth()/Block.SIZE;

        //Get length of diagonal of inner active area rectangle
        final double diameterOfActiveArea = Math.sqrt((distanceOfScreenBlocksWidth*distanceOfScreenBlocksWidth)+(distanceOfScreenBlocksHeight*distanceOfScreenBlocksHeight));

        final double radiusOfActiveArea = diameterOfActiveArea/2;

        assert (int)(enemySpawnArea.getCircleRadius()-radiusOfActiveArea) > 0: "Enemy spawn area MUST be greater than radius of active area";

        GenericContainer.Pair<Integer> minAndMaxDists = new GenericContainer.Pair<>();
        //minAndMaxDists.set(diffBetweenActiveAreaRadiusAndSpawnAreaRadius, (int)radiusOfActiveArea);
        //Enemy must spawn at least outside of the active area and at most inside the spawn area
        minAndMaxDists.set((int)radiusOfActiveArea, (int)enemySpawnArea.getCircleRadius());

        return minAndMaxDists;
    }

    public static Optional<GenericContainer.Pair<Integer>> generateEligibleEnemySpawnPoint(CircleAreaComponent enemySpawnArea) {

        GenericContainer.Pair<Integer> minAndMaxDists = retrieveMinMaxDistances(enemySpawnArea);

        int minimumDistance = minAndMaxDists.getFirst();
        int maximumDistance = minAndMaxDists.getSecond();

        //Randomly generate angle
        int angle = SpawnUtilComponent.generateRandomNumber(0, 360);

        //Randomly generate a distance between min and max
        int randomDistance = SpawnUtilComponent.generateRandomNumber(minimumDistance, maximumDistance);

        Optional<GenericContainer.Pair<Integer>> optionalRotatedSpawnPoints = Optional.of(retrieveRotatedGridPoints(randomDistance, angle));

        boolean pointInMap = false;

        //A rudimentary timer counter
        int counter = 64;

        while(!pointInMap){
            if(counter==0){

                LastTry.debug.print("Unable to find suitable point to spawn enemy, counter expired");
                optionalRotatedSpawnPoints = Optional.ofNullable(null);
                return optionalRotatedSpawnPoints;

            }else{

                int xSpawnPoint = optionalRotatedSpawnPoints.get().getFirst();
                int ySpawnPoint = optionalRotatedSpawnPoints.get().getSecond();

                //TODO Add check to see if anything exists on that point
                if (SpawnUtilComponent.isPointOnMap(xSpawnPoint, ySpawnPoint)) {
                    pointInMap = true;
                } else {
                    angle = SpawnUtilComponent.increaseAngle(angle, randomDistance);
                    optionalRotatedSpawnPoints = Optional.of(retrieveRotatedGridPoints(randomDistance, angle));
                }
                counter--;

            }
        }

        return optionalRotatedSpawnPoints;
    }

    public static GenericContainer.Pair<Integer> retrieveRotatedGridPoints(int distance, int angle) {

        int playerXGridPoint = LastTry.player.physics.getGridX();
        int playerYGridPoint = LastTry.player.physics.getGridY();

        //Move point by distance
        //Source: http://stackoverflow.com/questions/41465581/move-point-in-cartesian-coordinate-through-distance-in-the-given-direction
        int newXGridPoint = (int)(playerXGridPoint + distance * Math.cos(angle*Math.PI/180));
        int newYGridPoint = (int)(playerYGridPoint + distance * Math.sin(angle*Math.PI/180));

        GenericContainer.Pair<Integer> rotatedXyPoints = new GenericContainer.Pair<>();
        rotatedXyPoints.set(newXGridPoint, newYGridPoint);

        return rotatedXyPoints;
    }

    public static boolean isCreatureInPlayerActiveArea(CreatureWithAI creatureWithAI, CircleAreaComponent area) {
        // Get block co ordinates of enemy
        int enemyBlockGridX = creatureWithAI.physics.getGridX();
        int enemyBlockGridY = creatureWithAI.physics.getGridY();

        boolean isEnemyInCircleSpawnArea = SpawnUtilComponent.arePointsInCircle(enemyBlockGridX, enemyBlockGridY, area);

        return isEnemyInCircleSpawnArea;
    }

}
