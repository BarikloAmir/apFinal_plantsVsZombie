package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * this is class of normal zombie that extends from zombie class
 */
public class NormalZombie extends Zombie implements Serializable {

    public NormalZombie(){
        super();
        setLife(200);
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

        return 5;
    }

    @Override
    public void move(int type){
        if (getMove())
             setLocX(getLocX()-(int)getSpeed());
    }


}
