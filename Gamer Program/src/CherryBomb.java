package src;



import java.io.Serializable;

/**
 * cherry bomb is a plant that explode
 */
public class CherryBomb extends Plant implements Serializable {

    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public CherryBomb(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        health=70;
        cost=150;

    }

}
