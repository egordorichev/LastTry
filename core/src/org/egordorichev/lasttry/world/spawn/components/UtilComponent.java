package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.GenericContainer;

/**
 * Created by Admin on 27/04/2017.
 */
public class UtilComponent {

    public static GenericContainer.Pair<Integer> increaseAngle(int angle, int distance) {

        if(angle==360){
            angle = 0;
        }

        angle++;

        return GridComponent.retrieveRotatedGridPoints(distance, angle);
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
