package src;


import java.io.Serializable;

/**
 * beet bullet that shoot from beetroot
 */
public class BeetBullet extends PeaFather implements Serializable {
    /**
     * constructor
     *
     * @param locX loc of x
     * @param locY y
     */
    public BeetBullet(int locX, int locY) {
        super(locX, locY);
        setEffectInjury(30);
//
    }
}
