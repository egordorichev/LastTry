package org.egordorichev.lasttry.world.spawn.components;

/**
 * Created by Admin on 21/04/2017.
 */
public class AreaComponent {

    private int minXGridPoint, maxXGridPoint, minYGridPoint, maxYGridPoint;

    private int maxXGridPointActiveZone;

    public int getMinXGridPoint() {
        return this.minXGridPoint;
    }

    public void setMinXGridPoint(int minXGridPoint) {
        this.minXGridPoint = minXGridPoint;
    }

    public int getMaxXGridPoint() {
        return this.maxXGridPoint;
    }

    public void setMaxXGridPoint(int maxXGridPoint) {
        this.maxXGridPoint = maxXGridPoint;
    }

    public int getMinYGridPoint() {
        return this.minYGridPoint;
    }

    public void setMinYGridPoint(int minYGridPoint) {
        this.minYGridPoint = minYGridPoint;
    }

    public int getMaxYGridPoint() {
        return this.maxYGridPoint;
    }

    public void setMaxYGridPoint(int maxYGridPoint) {
        this.maxYGridPoint = maxYGridPoint;
    }

    public int getMaxXGridPointActiveZone() {
        return this.maxXGridPointActiveZone;
    }

    public void setMaxXGridPointActiveZone(int maxXGridPointActiveZone) {
        this.maxXGridPointActiveZone = maxXGridPointActiveZone;
    }
}
