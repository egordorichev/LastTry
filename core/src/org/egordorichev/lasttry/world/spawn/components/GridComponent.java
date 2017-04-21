package org.egordorichev.lasttry.world.spawn.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;

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

    public static GenericContainer.Pair<Integer> generateEligibleSpawnPoint(AreaComponent enemySpawnArea) {

        // Generate inside the active zone
        int xGridSpawnPoint = enemySpawnArea.getMaxXGridPointActiveZone()-30;
        int yGridSpawnPoint = enemySpawnArea.getMinYGridPoint();

        int xPixelSpawnPoint = xGridSpawnPoint* Block.SIZE;
        int yPixelSpawnPoint = yGridSpawnPoint * Block.SIZE;

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set(xPixelSpawnPoint, yPixelSpawnPoint);

        return xyPoint;
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
