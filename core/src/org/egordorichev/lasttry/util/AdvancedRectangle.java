package org.egordorichev.lasttry.util;

import javafx.geometry.Side;
import org.egordorichev.lasttry.LastTry;
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

        int boundaryInPixels = boundaryInBlocks * Block.TEX_SIZE;

        topRightPoint = new xyPoint(x+boundaryInPixels, y-boundaryInPixels, PointsIdent.TOPRIGHT);

        bottomRightPoint = new xyPoint(x+boundaryInPixels, y+boundaryInPixels,PointsIdent.BOTTOMRIGHT);

        topLeftPoint = new xyPoint(x-boundaryInPixels, y-boundaryInPixels, PointsIdent.TOPLEFT);

        bottomLeftPoint = new xyPoint(x-boundaryInPixels, y+boundaryInPixels, PointsIdent.BOTTOMLEFT);

        top = new Side(topRightPoint, topLeftPoint, SidesIdent.TOP);
        bottom = new Side(bottomRightPoint, bottomLeftPoint, SidesIdent.BOTTOM);
        left = new Side(topLeftPoint, bottomLeftPoint, SidesIdent.LEFT);
        right = new Side(topRightPoint, bottomRightPoint, SidesIdent.RIGHT);

    }

    /**
     * Returns a boolean indicating whether all sides of the rectangle,  are in the boundary
     *
     * @return Boolean
     */
    public boolean allSidesInBoundary(){

        Side[] sides = {top, bottom, left, right};

        for(Side side: sides){
            if(!side.isSideInBoundary()){
                return false;
            }
        }
        return true;
    }

    public void debugSetItemsOnPoints(){
        LastTry.world.setBlock(ItemID.grassBlock, topLeftPoint.getX()/16,topLeftPoint.getY()/16);
        LastTry.world.setBlock(ItemID.grassBlock, topRightPoint.getX()/16,topRightPoint.getY()/16);
        LastTry.world.setBlock(ItemID.grassBlock, bottomLeftPoint.getX()/16,bottomLeftPoint.getY()/16);
        LastTry.world.setBlock(ItemID.grassBlock, bottomRightPoint.getX()/16,bottomRightPoint.getY()/16);
    }


    /**
     * Stores x and y points, with an identifier describing the point.
     */
    private class xyPoint{
        private GenericContainer.Triple<Integer, PointsIdent> specificXyPoint;

        xyPoint(int x, int y, PointsIdent pointIdentifier){
            specificXyPoint = new GenericContainer.Triple<>();
            specificXyPoint.set(x, y, pointIdentifier);
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
                if(!LastTry.world.isInside(convertToBlock(sidePoint.getX()), convertToBlock(sidePoint.getY()))){
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
        return xPoint/Block.TEX_SIZE;
    }

}
