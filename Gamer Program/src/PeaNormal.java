package src;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * class of pea normal that extend peaFather
 */
public class PeaNormal extends PeaFather implements Serializable {



    /**
     * constructor
     *
     * @param locX loc of x
     * @param locY y
     */
    public PeaNormal(int locX, int locY) {
        super(locX, locY);
        setEffectInjury(30);
//
    }

}
