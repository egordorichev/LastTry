package org.egordorichev.lasttry.world.spawn.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.world.WorldTime;

public class SpawnUtilComponent {

    public static int increaseAngle(int angle, int distance) {

        if(angle==360){
            angle = 0;
        }

        angle++;

        return angle;
    }


    public static boolean arePointsInCircle(int x, int y, CircleAreaComponent circleArea) {

        //Checking if points are in the circle
        //Formula source: https://math.stackexchange.com/questions/198764/how-to-know-if-a-point-is-inside-a-circle
        double cameraGridPositionXBlocks = Camera.game.position.x/Block.SIZE;
        double cameraGridPositionYBlocks = Camera.game.position.y/Block.SIZE;

        double xPointCalcs = x - cameraGridPositionXBlocks;
        double yPointCalcs = y - cameraGridPositionYBlocks;

        double xPointSquared = Math.pow(xPointCalcs,2);
        double yPointSquared = Math.pow(yPointCalcs,2);

        double result = Math.sqrt(xPointSquared+yPointSquared);

        if(result>=circleArea.getCircleRadius()){
            return false;
        }

        return true;
    }

    public static boolean isPointOnMap(int xGridPoint, int yGridPoint) {

        boolean isPointInMap = Globals.world.isInside(xGridPoint, yGridPoint);

        return  isPointInMap;
    }

    public static int generateRandomNumber(int minNumber, int maxNumber) {

        if(maxNumber-minNumber<=0){
            throw new IllegalArgumentException("Difference between max & min numbers is less than or equal to 0");
        }

        int randomNumber = LastTry.random.nextInt(maxNumber-minNumber)+minNumber;

        return randomNumber;
    }


    public static boolean matchingTime(WorldTime time1, WorldTime time2) {

        if(time1.toString(false).equals(time2.toString(false))){
            return true;
        }

        return false;
    }


}
