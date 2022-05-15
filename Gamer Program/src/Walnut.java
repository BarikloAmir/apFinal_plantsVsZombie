package src;

import java.io.Serializable;

/**
 * class of walnut
 */
public class Walnut extends Plant implements Serializable {
    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public Walnut(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        cost=50;
        health=150;

    }


}
