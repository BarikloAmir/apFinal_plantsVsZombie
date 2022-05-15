
package src;

import java.awt.*;
import java.io.Serializable;

/**
 * this class is father of all plants in this game and every plants extends from this
 *
 */
public class Plant implements Serializable {
    //the health of each plant
    protected int health;
    //locX in map
    protected int locX;
    //locY in map
    protected int locY;
    //cost for plant a flower
    protected int cost;
    //time of born is useful for cherry bomb and sunflower
    protected int bornTime;
    //index of x and y
    protected int indexX=-1;
    protected int indexY=-1;



    /**
     * constructor
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public Plant(int xLoc, int yLoc) {
        this.locX = xLoc;
        this.locY = yLoc;

    }

    /**
     * return locx
     * @return locX
     */
    public int getLocX() {
        return locX;
    }

    /**
     *
     * @return locY
     */
    public int getLocY() {
        return locY;
    }


    /**
     * useful for cherrybomb and sunflower that time of born is important
     * @param bornTime the time that a plant born
     */
    public void setBornTime(int bornTime) {
        this.bornTime = bornTime;
    }

    /**
     *
     * @return bornTime
     */
    public int getBornTime() {
        return bornTime;
    }

    /**
     * return cost of each plant
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     *
     * @param indexX index x in map
     */
    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    /**
     * this method reduce health of plant
     * @param injury value that must reduced
     */
    public void reduceHealth(int injury){
        health-=injury;
    }
}