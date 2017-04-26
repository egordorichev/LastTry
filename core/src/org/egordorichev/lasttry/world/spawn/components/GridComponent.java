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

    public static AreaComponent generateActiveArea() {
        AreaComponent activeAreaOfPlayer = new AreaComponent();

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
        activeAreaOfPlayer.setMinYGridPoint(Math.max(0, tcy - 2));
        activeAreaOfPlayer.setMaxYGridPoint(Math.min(LastTry.world.getHeight() - 1, tcy + twh + 3));

        // Checking to make y values is not less than 0
        activeAreaOfPlayer.setMinXGridPoint(Math.max(0, tcx - 2));
        activeAreaOfPlayer.setMaxXGridPoint(Math.min(LastTry.world.getWidth() - 1, tcx + tww + 2));

        // Active zone is 6 greater
        // TODO Must check that it is not out of bou
        activeAreaOfPlayer.setMaxXGridPointActiveZone(activeAreaOfPlayer.getMaxXGridPoint()+25);

        return activeAreaOfPlayer;
    }

    //TODO Must be changed to incorporate player location
    public static double generateActiveAreaCircleRadius() {

        //Add 200 to add a buffer to the view.
        final int windowWidth = Gdx.graphics.getWidth()+200;
        final int windowHeight = Gdx.graphics.getHeight();
        final int tww = windowWidth / Block.SIZE;
        final int twh = windowHeight / Block.SIZE;

        //Divide width & height of screen by 2, to get distance to furthermost part of the screen.
        //Will be used to construct a triangle from centerpoint to further most point of the screen
        final int centerWidth = tww/2;
        final int centerHeight = twh/2;

        final double activeAreaCircleRadius = Math.sqrt((tww*tww)+(twh*twh));

        assert activeAreaCircleRadius != 0 : "Generated radius is 0";

        return activeAreaCircleRadius;
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

        //Divided by 2 as we want to get the radius from the center of the screen.
        final int gridSpawnWindowWidthForRadius =  gridSpawnWindowWidth/2;
        final int gridSpawnWindowHeightForRadius = gridSpawnWindowHeight/2;

        final double activeAreaCircleRadius = Math.sqrt((gridSpawnWindowWidthForRadius*gridSpawnWindowWidthForRadius)+(gridSpawnWindowHeightForRadius*gridSpawnWindowHeightForRadius));
        final double activeAreaCircleDiameter = activeAreaCircleRadius*2;

        circleAreaComponent.setCircleRadius(activeAreaCircleRadius);
        circleAreaComponent.setCircleDiameter(activeAreaCircleDiameter);

        return circleAreaComponent;
    }


    public static GenericContainer.Pair<Integer> generateEligibleSpawnPoint(CircleAreaComponent enemySpawnArea) {

        //Get current center grid points of screen
        int xGridCenterPoint = (int)(Camera.game.position.x/Block.SIZE);
        int yGridCenterPoint = (int)(Camera.game.position.y/Block.SIZE);

        //Get min points of circle spawn area for x and y
        int minXGridSpawnAreaPoint = xGridCenterPoint - (int)enemySpawnArea.getCircleRadius();
        int maxXGridSpawnAreaPoint = xGridCenterPoint + (int)enemySpawnArea.getCircleRadius();

        int maxYGridSpawnAreaPoint = yGridCenterPoint - (int)enemySpawnArea.getCircleRadius();
        int minYGridSpawnAreaPoint = yGridCenterPoint + (int)enemySpawnArea.getCircleRadius();

        //Retrieve random values for the x and y point
        int randomXGridSpawnPoint = generateRandomNumber(minXGridSpawnAreaPoint, maxXGridSpawnAreaPoint);
        int randomYGridSpawnPoint = generateRandomNumber(minYGridSpawnAreaPoint, maxYGridSpawnAreaPoint);

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set(randomXGridSpawnPoint, randomYGridSpawnPoint);

        return xyPoint;
    }


    public static GenericContainer.Pair<Integer> generateEligibleEnemySpawnPoint(CircleAreaComponent enemySpawnArea) {

        int distanceOfScreenBlocksHeight = Gdx.graphics.getHeight()/Block.SIZE;
        int distanceOfScreenBlocksWidth = Gdx.graphics.getWidth()/Block.SIZE;

        //Get length of diagonal of inner active area rectangle
        final double radiusOfActiveArea = Math.sqrt((distanceOfScreenBlocksWidth*distanceOfScreenBlocksWidth)+(distanceOfScreenBlocksHeight*distanceOfScreenBlocksHeight));

        final int diffBetweenActiveAreaRadiusAndSpawnAreaRadius = (int)(enemySpawnArea.getCircleRadius()-radiusOfActiveArea);

        int minimumDistance = diffBetweenActiveAreaRadiusAndSpawnAreaRadius;
        int maximumDistance = (int) enemySpawnArea.getCircleDiameter();

        //Randomly generate angle
        int angle = generateRandomNumber(0, 360);

        //Randomly generate a distance between min and max
        int randomDistance = generateRandomNumber(minimumDistance, maximumDistance);

        //Get player co ordinates
        int playerXGridPoint = LastTry.player.physics.getGridX();
        int playerYGridPoint = LastTry.player.physics.getGridY();

        //Move point by distance
        //Source: http://stackoverflow.com/questions/41465581/move-point-in-cartesian-coordinate-through-distance-in-the-given-direction
        double newXGridPoint = playerXGridPoint + randomDistance * Math.cos(angle * Math.PI/180);
        double newYGridPoint = playerYGridPoint + randomDistance * Math.sin(angle * Math.PI/180);

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set((int)newXGridPoint, (int)newYGridPoint);

        //Origin to rotate about
//        int centerXGridPoint = (int)(Camera.game.position.x/Block.SIZE);
//        int centerYGridPoint = (int)(Camera.game.position.y/Block.SIZE);
//
//        int intermediateTransformXPoint = playerXGridPoint - centerXGridPoint;
//        int intermediateTransformYPoint = playerYGridPoint - centerYGridPoint;
//
//        //Rotate x point, source: https://academo.org/demos/rotation-about-point/
//        double rotatedXGridPoint = intermediateTransformXPoint*Math.cos(angle) - intermediateTransformYPoint * Math.sin(angle);
//        double rotatedYGridPoint = intermediateTransformYPoint*Math.cos(angle) + intermediateTransformXPoint * Math.sin(angle);
//
//        int finalRotatedXGridPoint = (int)(rotatedXGridPoint + centerXGridPoint);
//        int finalRotatedYGridPoint = (int)(rotatedYGridPoint + centerYGridPoint);

        return xyPoint;
    }

    private static int generateRandomNumber(int minNumber, int maxNumber) {

        if(maxNumber-minNumber<=0){
            throw new IllegalArgumentException("Difference between max & min numbers is less than or equal to 0");
        }

        int randomNumber = LastTry.random.nextInt(maxNumber-minNumber)+minNumber;

        return randomNumber;
    }


    public static boolean isEnemyInActiveArea(Enemy enemy, AreaComponent area) {

        // Get block co ordinates of enemy
        int enemyBlockGridX = enemy.physics.getGridX();
        int enemyBlockGridY = enemy.physics.getGridY();

        // TODO Change on inversion of y axis
        if(enemyBlockGridX>=area.getMinXGridPoint()&&enemyBlockGridX<=area.getMaxXGridPoint()){
            if(enemyBlockGridY<=area.getMaxYGridPoint()&&enemyBlockGridY>=area.getMinYGridPoint()){
                return true;
            }
        }

        return false;
    }



}
