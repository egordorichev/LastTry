package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;
import sun.net.www.content.text.Generic;
import sun.nio.cs.ext.MacArabic;

/**
 * Created by Admin on 21/04/2017.
 */
public class GridComponent {

    public static CircleAreaComponent generateActiveAreaCircle() {

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

        //Divided by 2 as we want to get the radius from the center of the screen.
        final int gridSpawnWindowWidthForRadius =  gridSpawnWindowWidth/2;
        final int gridSpawnWindowHeightForRadius = gridSpawnWindowHeight/2;

        final double activeAreaCircleRadius = Math.sqrt((gridSpawnWindowWidthForRadius*gridSpawnWindowWidthForRadius)+(gridSpawnWindowHeightForRadius*gridSpawnWindowHeightForRadius));
        final double activeAreaCircleDiameter = activeAreaCircleRadius*2;

        circleAreaComponent.setCircleRadius(activeAreaCircleRadius);
        circleAreaComponent.setCircleDiameter(activeAreaCircleDiameter);

        return circleAreaComponent;
    }

    private static GenericContainer.Pair<Integer> retrieveMinMaxDistances(CircleAreaComponent enemySpawnArea) {

        int distanceOfScreenBlocksHeight = Gdx.graphics.getHeight()/Block.SIZE;
        int distanceOfScreenBlocksWidth = Gdx.graphics.getWidth()/Block.SIZE;

        //Get length of diagonal of inner active area rectangle
        final double radiusOfActiveArea = Math.sqrt((distanceOfScreenBlocksWidth*distanceOfScreenBlocksWidth)+(distanceOfScreenBlocksHeight*distanceOfScreenBlocksHeight));

        final int diffBetweenActiveAreaRadiusAndSpawnAreaRadius = (int)(enemySpawnArea.getCircleRadius()-radiusOfActiveArea);

        GenericContainer.Pair<Integer> minAndMaxDists = new GenericContainer.Pair<>();
        minAndMaxDists.set(diffBetweenActiveAreaRadiusAndSpawnAreaRadius, (int)enemySpawnArea.getCircleRadius());

        return minAndMaxDists;
    }

    public static GenericContainer.Pair<Integer> generateEligibleEnemySpawnPoint(CircleAreaComponent enemySpawnArea) {

        GenericContainer.Pair<Integer> minAndMaxDists = retrieveMinMaxDistances(enemySpawnArea);

        int minimumDistance = minAndMaxDists.getFirst();
        int maximumDistance = minAndMaxDists.getSecond();

        //Randomly generate angle
        int angle = UtilComponent.generateRandomNumber(0, 360);

        //Randomly generate a distance between min and max
        int randomDistance = UtilComponent.generateRandomNumber(minimumDistance, maximumDistance);

        GenericContainer.Pair<Integer> rotatedGridPoints = retrieveRotatedGridPoints(randomDistance, angle);

        boolean pointInMap = false;

        while(!pointInMap){

            if(UtilComponent.isPointOnMap(rotatedGridPoints.getFirst(), rotatedGridPoints.getSecond())==true){
                pointInMap = true;
            }else{
                angle = UtilComponent.increaseAngle(angle, randomDistance);
                rotatedGridPoints = retrieveRotatedGridPoints(randomDistance, angle);
            }
        }

        return rotatedGridPoints;
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

    public static boolean isEnemyInActiveArea(Enemy enemy, CircleAreaComponent area) {

        // Get block co ordinates of enemy
        int enemyBlockGridX = enemy.physics.getGridX();
        int enemyBlockGridY = enemy.physics.getGridY();

        // TODO Change on inversion of y axis
        if(enemyBlockGridX>=area.getMinXActiveAreaGridPoint()&&enemyBlockGridX<=area.getMaxXActiveAreaGridPoint()){
            if(enemyBlockGridY<=area.getMaxYActiveAreaGridPoint()&&enemyBlockGridY>=area.getMinYActiveAreaGridPoint()){
                return true;
            }
        }

        return false;
    }

}
