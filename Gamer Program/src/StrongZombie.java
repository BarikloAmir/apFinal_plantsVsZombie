package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * this is class of strong zombie
 */
public class StrongZombie extends Zombie implements Serializable{

    /**
     * constructor
     */
    public StrongZombie(){
        super();
        setLife(1300);
        setMove(true);
        run();
        setSpeed(1);

    }

    /**
     *
     * @param type if type == 0  => type = normal & if type == 1  => type = hard
     * @return damage of injury
     */
    @Override
    public int Injury(int type) {
        if(type == 0)
            return 20;
        else
            return 25;
    }


    /**
     * this method set y location of zombie in minimum danger
     * @param min it is the row has minimum plants
     */
    public void newSetLocY(int min){
        rowIndex=min;
        setLocY(min*95+25);
    }
//
}
