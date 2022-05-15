package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * this is class of football zombie
 */
public class FootballZombie extends Zombie implements Serializable {
    /**
     * constructor
     */
    public FootballZombie(){
        super();
        setLife(1700);
        run();
        setSpeed(2);
//
    }

    /**
     *
     * @param type if type == 0  => type = normal & if type == 1  => type = hard
     * @return damage
     */
    @Override
    public int Injury(int type) {
        if(type == 0)
            return 25;
        return 35;
    }


//
}
