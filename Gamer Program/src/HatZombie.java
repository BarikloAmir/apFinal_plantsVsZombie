package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * class of hat zombies
 */
public class HatZombie extends Zombie implements Serializable {

    /**
     * construtor
     */
    public HatZombie(){
        super();
        setLife(560);
        run();
        setSpeed(1);

    }

    /**
     *
     * @param type if type == 0  => type = normal & if type == 1  => type = hard
     * @return return damage of injury
     */
    @Override
    public int Injury(int type) {
        if(type == 0)
            return 10;
        return 15;
    }


}
