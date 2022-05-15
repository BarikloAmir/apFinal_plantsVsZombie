package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * class of lawnmower that they are in left of game
 */
public class LawnMower implements Serializable {
    //location
    private int locX;
    private int locY;

    private Boolean isMove=false;

    /**
     * constructr
     * @param locX
     * @param locY
     */
    public LawnMower(int locX,int locY){
        this.locX=locX;
        this.locY=locY;

    }

    /**
     * move in a row
     */
    public void move(){
        locX+=8;
    }


    /**
     *
     * @return locY
     */
    public int getLocY() {
        return locY;
    }

    /**
     *
     * @return locX
     */
    public int getLocX() {
        return locX;
    }

    /**
     * if can move tru (the plants is not in his way)
     * else false
     * @return isMove
     */
    public Boolean getIsMove() {
        return isMove;
    }

    /**
     * set can move
     * @param move canmove
     */
    public void setIsMove(Boolean move) {
        isMove = move;
    }
}
