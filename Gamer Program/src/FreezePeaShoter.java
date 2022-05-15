package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of freeze pea shooter
 */
public class FreezePeaShoter extends PeaShooteFather implements Serializable {
    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public FreezePeaShoter(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        cost=175;
        health=100;

    }

    /**
     *build pea and shoot peas in a row
     */
    @Override
    public void shootPea(){
        PeaFather peaFreeze=new PeaFreeze(locX+50,locY);
        peaFreeze.setIndexX(indexX);
        peaFreeze.setIndexY(indexY);
        peas.add(peaFreeze);
    }


}
