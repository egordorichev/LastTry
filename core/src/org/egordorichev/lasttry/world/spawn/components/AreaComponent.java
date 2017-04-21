package org.egordorichev.lasttry.world.spawn.components;

/**
 * Created by Admin on 21/04/2017.
 */
public class AreaComponent {

    private int minXPoint, maxXPoint, minYPoint, maxYPoint;

    private int maxXPointActiveZone;

    public int getMinXPoint() {
        return this.minXPoint;
    }

    public void setMinXPoint(int minXPoint) {
        this.minXPoint = minXPoint;
    }

    public int getMaxXPoint() {
        return this.maxXPoint;
    }

    public void setMaxXPoint(int maxXPoint) {
        this.maxXPoint = maxXPoint;
    }

    public int getMinYPoint() {
        return this.minYPoint;
    }

    public void setMinYPoint(int minYPoint) {
        this.minYPoint = minYPoint;
    }

    public int getMaxYPoint() {
        return this.maxYPoint;
    }

    public void setMaxYPoint(int maxYPoint) {
        this.maxYPoint = maxYPoint;
    }

    public int getMaxXPointActiveZone() {
        return this.maxXPointActiveZone;
    }

    public void setMaxXPointActiveZone(int maxXPointActiveZone) {
        this.maxXPointActiveZone = maxXPointActiveZone;
    }
}
