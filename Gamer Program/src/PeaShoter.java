package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of peashooter that shoot peanormal
 * and extends from peashootefather
 */
public class PeaShoter extends PeaShooteFather implements Serializable {

    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public PeaShoter(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        cost=100;
        health=70;

    }

    /**
     * shoot pea bullet
     */
    @Override
    public void shootPea(){
        PeaFather peaNormal=new PeaNormal(locX+50,locY);
        peaNormal.setIndexX(indexX);
        peaNormal.setIndexY(indexY);
        peas.add(peaNormal);
    }


}
