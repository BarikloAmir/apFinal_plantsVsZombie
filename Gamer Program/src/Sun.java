package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Sun implements Serializable {
    //loc x and y
    private int locX;
    private int locY;
    //value per each sun
    private int value=25;
    //time for disapear
    private int timeDisAppear=10;
    //time of born
    private int bornTime;
    //the loc of earth that sun downs
    private  int yLocEarth;
    /**
     * constructor
     * @param locX loc of x in map
     * @param locY loc of y
     */
    public Sun(int locX,int locY){
        this.locX=locX;
        this.locY=locY;

    }


    /**
     * set born time
     * @param bornTime
     */
    public void setBornTime(int bornTime) {
        this.bornTime = bornTime;
    }

    /**
     *return locx
     * @return locX
     */
    public int getLocX() {
        return locX;
    }

    /**
     *return locy
     * @return locY
     */
    public int getLocY() {
        return locY;
    }

    /**
     * return borntime
     * @return bornTime
     */
    public int getBornTime() {
        return bornTime;
    }

    /**
     * move sunsky to down
     */
    public void moveFromSkyToEarth(){
        if (yLocEarth!=locY){
            locY++;
            if (yLocEarth!=locY)locY++;
        }
    }

    /**
     * set y ground
     * @param yLocEarth y of loc earth
     */
    public void setyLocEarth(int yLocEarth) {
        this.yLocEarth = yLocEarth;
    }

}