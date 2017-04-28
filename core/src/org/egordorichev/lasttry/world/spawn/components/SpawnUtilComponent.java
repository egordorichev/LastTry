package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;

public class SpawnUtilComponent {

    public static int increaseAngle(int angle, int distance) {

        if(angle==360){
            angle = 0;
        }

        angle++;

        return angle;
    }

    public static boolean arePointsInCircle(int x, int y, CircleAreaComponent circleArea) {

        //TODO Will convert into formula
        double cameraGridPositionXBlocks = Camera.game.position.x/Block.SIZE;
        double cameraGridPositionYBlocks = Camera.game.position.y/Block.SIZE;

        double xPointCalcs = x - cameraGridPositionXBlocks;
        double yPointCalcs = y - cameraGridPositionYBlocks;

        double xPointCalc = Math.pow(xPointCalcs,2);
        double yPointCalc = Math.pow(yPointCalcs,2);

        double result = Math.sqrt(xPointCalc+yPointCalc);

        if(result>=circleArea.getCircleRadius()){
            return false;
        }

        return true;
    }

    public static boolean isPointOnMap(int xGridPoint, int yGridPoint) {

        boolean isPointInMap = LastTry.world.isInside(xGridPoint, yGridPoint);

        return  isPointInMap;
    }

    public static int generateRandomNumber(int minNumber, int maxNumber) {

        if(maxNumber-minNumber<=0){
            throw new IllegalArgumentException("Difference between max & min numbers is less than or equal to 0");
        }

        int randomNumber = LastTry.random.nextInt(maxNumber-minNumber)+minNumber;

        return randomNumber;
    }

}
