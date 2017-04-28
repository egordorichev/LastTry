package org.egordorichev.lasttry.world.spawn.components;

public class CircleAreaComponent {

    private int minXActiveAreaGridPoint, maxXActiveAreaGridPoint, minYActiveAreaGridPoint, maxYActiveAreaGridPoint;

    private double circleRadius, circleDiameter;

    public int getMinXActiveAreaGridPoint() {
        return this.minXActiveAreaGridPoint;
    }

    public void setMinXActiveAreaGridPoint(int minXActiveAreaGridPoint) {
        this.minXActiveAreaGridPoint = minXActiveAreaGridPoint;
    }

    public int getMaxXActiveAreaGridPoint() {
        return this.maxXActiveAreaGridPoint;
    }

    public void setMaxXActiveAreaGridPoint(int maxXActiveAreaGridPoint) {
        this.maxXActiveAreaGridPoint = maxXActiveAreaGridPoint;
    }

    public int getMinYActiveAreaGridPoint() {
        return this.minYActiveAreaGridPoint;
    }

    public void setMinYActiveAreaGridPoint(int minYActiveAreaGridPoint) {
        this.minYActiveAreaGridPoint = minYActiveAreaGridPoint;
    }

    public int getMaxYActiveAreaGridPoint() {
        return this.maxYActiveAreaGridPoint;
    }

    public void setMaxYActiveAreaGridPoint(int maxYActiveAreaGridPoint) {
        this.maxYActiveAreaGridPoint = maxYActiveAreaGridPoint;
    }

    public double getCircleRadius() {
        return this.circleRadius;
    }

    public void setCircleRadius(double circleRadius) {
        this.circleRadius = circleRadius;
    }

    public double getCircleDiameter() {
        return this.circleDiameter;
    }

    public void setCircleDiameter(double circleDiameter) {
        this.circleDiameter = circleDiameter;
    }
}
