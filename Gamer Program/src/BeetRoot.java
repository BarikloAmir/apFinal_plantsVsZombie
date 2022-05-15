package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * beetroot is a plant in game and shoot 3 bullet in 3sides
 */
public class BeetRoot extends PeaShooteFather implements Serializable {

    //beet has tree bullet ones upp ones down and one in a row
    private ArrayList<PeaFather>uppBullets;
    private ArrayList<PeaFather>downBullets;

    /**
     * constructor
     *
     * @param xLoc return locX
     * @param yLoc return locY
     */
    public BeetRoot(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        cost=125;
        health=75;
        uppBullets=new ArrayList<>();
        downBullets=new ArrayList<>();

    }

    /**
     * shhot peas or bullet in three direction
     */
    @Override
    public void shootPea() {
        PeaFather beetpea1=new BeetBullet(locX+50,locY+20);
        beetpea1.setIndexX(indexX);
        beetpea1.setIndexY(indexY);
        peas.add(beetpea1);

        PeaFather beetpea2=new BeetBullet(locX+50,locY+20);
        beetpea2.setIndexX(indexX);
        beetpea2.setIndexY(indexY);
        peas.add(beetpea2);
        uppBullets.add(beetpea2);

        PeaFather beetpea3=new BeetBullet(locX+50,locY+20);
        beetpea3.setIndexX(indexX);
        beetpea3.setIndexY(indexY);
        peas.add(beetpea3);
        downBullets.add(beetpea3);


    }


    /**
     * return downBullets
     * @return downBullets
     */
    public ArrayList<PeaFather> getDownBullets() {
        return downBullets;
    }

    /**
     * return uppBullets
     * @return uppBullets
     */
    public ArrayList<PeaFather> getUppBullets() {
        return uppBullets;
    }
}
