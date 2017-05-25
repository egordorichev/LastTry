package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;

/**
 * A rectangle object where the x, y co ordinates are placed in the center.
 * The adv
 *
 * Created by Admin on 18/04/2017.
 */
public class AdvancedRectangle {

    //Enums identifying the individual points
    private enum PointsIdent{
        TOPLEFT, BOTTOMLEFT, TOPRIGHT, BOTTOMRIGHT;
    }

    //Enums identifying the sides
    private enum SidesIdent{
        TOP, BOTTOM, LEFT, RIGHT;
    }

    //Center points for the rectangle
    private int x,y;

    //Amount of space we want between the points and all sides.
    private int boundaryInBlocks;

    //Sides for the rectangle
    private Side top, bottom, left, right;

    //Points
    private xyPoint topRightPoint, bottomRightPoint, topLeftPoint, bottomLeftPoint;

    /**
     * Creates a rectangle with the points in the center and a space of 'boundaryInBlock' between character and all
     * sides.
     *
     * @param x x point pixels
     * @param y y point pixels
     * @param boundaryInBlocks boundary in blocks
     */
    public AdvancedRectangle(float x, float y, int boundaryInBlocks){
        this.x = Math.round(x); this.y = Math.round(y); this.boundaryInBlocks = boundaryInBlocks;
        generatePoints();
    }

    /**
     * Generates the appropriate points based on the block boundary.
     */
    private void generatePoints(){

        int boundaryInPixels = this.boundaryInBlocks * Block.SIZE;

        //TODO Swap these on y axis reflection
        this.topRightPoint = new xyPoint(x+boundaryInPixels, y-boundaryInPixels, PointsIdent.TOPRIGHT);

        this.bottomRightPoint = new xyPoint(x+boundaryInPixels, y+boundaryInPixels,PointsIdent.BOTTOMRIGHT);

        this.topLeftPoint = new xyPoint(x-boundaryInPixels, y-boundaryInPixels, PointsIdent.TOPLEFT);

        this.bottomLeftPoint = new xyPoint(x-boundaryInPixels, y+boundaryInPixels, PointsIdent.BOTTOMLEFT);

        this.top = new Side(this.topRightPoint, this.topLeftPoint, SidesIdent.TOP);
        this.bottom = new Side(this.bottomRightPoint, this.bottomLeftPoint, SidesIdent.BOTTOM);
        this.left = new Side(this.topLeftPoint, this.bottomLeftPoint, SidesIdent.LEFT);
        this.right = new Side(this.topRightPoint, this.bottomRightPoint, SidesIdent.RIGHT);

    }

    /**
     * Returns a boolean indicating whether all sides of the rectangle,  are in the boundary
     *
     * @return Boolean
     */
    public boolean allSidesInBoundary(){

        Side[] sides = {this.top, this.bottom, this.left, this.right};

        for(Side side: sides){
            if(!side.isSideInBoundary()){
                return false;
            }
        }
        return true;
    }

    public void debugSetItemsOnPoints(){
        Globals.getWorld().setBlock(ItemID.grassBlock, this.topLeftPoint.getX()/16,this.topLeftPoint.getY()/16);
        Globals.getWorld().setBlock(ItemID.grassBlock, this.topRightPoint.getX()/16,this.topRightPoint.getY()/16);
        Globals.getWorld().setBlock(ItemID.grassBlock, this.bottomLeftPoint.getX()/16,this.bottomLeftPoint.getY()/16);
        Globals.getWorld().setBlock(ItemID.grassBlock, this.bottomRightPoint.getX()/16,this.bottomRightPoint.getY()/16);
    }


    /**
     * Stores x and y points, with an identifier describing the point.
     */
    private class xyPoint{
        private GenericContainer.Triple<Integer, PointsIdent> specificXyPoint;

        xyPoint(int x, int y, PointsIdent pointIdentifier){
            this.specificXyPoint = new GenericContainer.Triple<>();
            this.specificXyPoint.set(x, y, pointIdentifier);
        }

        public int getX() {return this.specificXyPoint.getX();}

        public int getY() {return this.specificXyPoint.getY();}
    }

    /**
     *A side object, contains of two xy points and an identifier describing the side
     */
    private class Side{
        private xyPoint first, second;
        private SidesIdent side;

        Side(xyPoint first, xyPoint second, SidesIdent side){
            this.first = first;
            this.second = second;
            this.side = side;
        }

        /**
         * Checks if both points describing the side are in boundary.
         *
         * @return boolean describing whether both points are in the boundary.
         */
        public boolean isSideInBoundary(){

            xyPoint[] sidePoints = {first, second};

            for(xyPoint sidePoint: sidePoints){
                if(!Globals.getWorld().isInside(convertToBlock(sidePoint.getX()), convertToBlock(sidePoint.getY()))){
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Convert co ordinate point to a block co ordinate point.
     * @param xPoint x point co ordinate
     * @return block point co oridnate in int
     */
    private int convertToBlock(int xPoint){
        return xPoint/Block.SIZE;
    }


    /**
     * Checks if the x & y points received are within the rectangle area
     *
     * @param x pixel x co ordinate
     * @param y pixel y co ordinate
     * @return boolean indicating whether the pixel is within.
     */
    public boolean isXyPointsWithinRectangle(float x, float y) {

        //Converts pixel co ordinates to grid style co oridnates
        int blockXPoint = convertToBlock(Math.round(x));
        int blockYPoint = convertToBlock(Math.round(y));

        //TODO Swap this on Y axis reflection
        //If the y point is less than top y point & x point less than bottom x point
        if(blockYPoint>topLeftPoint.getY()&&blockXPoint<bottomRightPoint.getX()) {
            return true;
        }

        return false;
    }

}
