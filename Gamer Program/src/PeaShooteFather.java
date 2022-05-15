package src;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * class of father of peashooters and beet root
 * extend from plant
 *
 */
public  class PeaShooteFather extends Plant implements Serializable {
    protected ArrayList<PeaFather> peas;
    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public PeaShooteFather(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        peas=new ArrayList<>();
    }

    /**
     * shoot pea in a row
     */
    public void shootPea(){}

    /**
     * return peas
     * @return peas
     */
    public ArrayList<PeaFather> getPeas() {
        return peas;
    }
}
