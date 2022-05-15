package src;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * pea freeze extend from pea father
 */
public class PeaFreeze extends PeaFather implements Serializable {
    /**
     * constructor
     *
     * @param locX loc of x
     * @param locY y
     */
    public PeaFreeze(int locX, int locY) {
        super(locX, locY);
        setEffectInjury(35);
//
    }
}
