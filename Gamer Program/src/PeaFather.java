package src;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of peaFather that is a bullet and if zombi is in his row ,pea shooted and effect in zombies
 */
public class PeaFather implements Serializable {


    private int effectInjury;
    protected int locX;
    protected int locY;
    protected int indexX=-1;
    protected int indexY=-1;

    /**
     * constructor
     * @param locX loc of x
     * @param locY        y
     */
    public PeaFather(int locX,int locY){
        this.locX=locX;
        this.locY=locY;


    }


    /**
     * move pea in a row
     */
    public void movePea(){
        locX +=10;
    }

    /**
     * for beet root bullet
     */
    public void movePeaUp(){
        locX+=10;locY+=7;
    }

    /**
     * for beetroot bullet
     */
    public void movePeaDown(){
        locY-=7;
        locX+=10;
    }

    /**
     * set index y
     * @param indexY indexy
     */
    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    /**
     * set indexx
     * @param indexX indexx
     */
    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    /**
     * return locy
     * @return locY
     */
    public int getLocY() {
        return locY;
    }

    /**
     * return locx
     * @return locX
     */
    public int getLocX() {
        return locX;
    }

    /**
     * return effect of injury
     * @return effectInjury
     */
    public int getEffectInjury() {
        return effectInjury;
    }

    /**
     * set    effectInjury
     * @param effectInjury damage
     */
    public void setEffectInjury(int effectInjury) {
        this.effectInjury = effectInjury;
    }
}
