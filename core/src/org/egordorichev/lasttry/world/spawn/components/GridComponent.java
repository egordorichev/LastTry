package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;
import sun.net.www.content.text.Generic;

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

        final double activeAreaCircleRadius = Math.sqrt((tww*tww)+(twh*twh));
        final double activeAreaCircleDiameter = activeAreaCircleRadius*2;

        circleAreaComponent.setCircleRadius(activeAreaCircleRadius);
        circleAreaComponent.setCircleDiameter(activeAreaCircleDiameter);

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
        //circleAreaComponent.setMaxXGridPointSpawnZone(circleAreaComponent.getMaxXActiveAreaGridPoint()+25);

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
