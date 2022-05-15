package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 *class of tallnut
 */
public class TallWalnut extends Plant implements Serializable {
    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public TallWalnut(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        health=300;
        cost=125;

    }


}
