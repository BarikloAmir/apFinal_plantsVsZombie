
        package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

        /**
         * sunflower produce sun after seconds
         */
        public class SunFlower extends Plant implements Serializable {
    //sun of sun flower
    private Sun sun;
    private Boolean ifProduceSun=false;

            /**
             * constructor
             * @param locX
             * @param locY
             */
    public SunFlower(int locX,int locY){
        super(locX, locY);
        this.cost = 50;
        this.health = 50;

    }

            /**
             * produce sun after seconds
             * @param currentTime current time
             * @param type hard or normal
             */
    public void produceSun(int currentTime,int type){
        if ((currentTime-bornTime==19&&type==0)||(currentTime-bornTime==24&&type==1))ifProduceSun=true;
        else   if ((currentTime-bornTime>=20&&type==0)||(currentTime-bornTime>=25&&type==1)){
                System.out.println("born"+bornTime);
                System.out.println("curr"+currentTime);
                bornTime=currentTime;
                sun=new Sun(locX+40,locY+40);
                sun.setBornTime(currentTime);
                ifProduceSun=true;
            }else ifProduceSun=false;

    }

    /**
     * remove sun with compare born time of sun and current time
     * @param currentTime time in second
     */
    public void removeSun(int currentTime){
        if (sun!=null && currentTime - sun.getBornTime() > 4) {
            System.out.println("sub died"+sun.getBornTime());
            System.out.println("curr"+currentTime);
            sun = null;
        }
    }

            /**
             * set sun
             * @param sun
             */
    public void setSun(Sun sun) {
        this.sun = sun;
    }

            /**
             * return sun
             * @return sun
             */
    public Sun getSun() {
        return sun;
    }

            /**
             * if produce sun true else false
             * @return  ifProduceSun
             */
    public Boolean getIfProduceSun() {
        return ifProduceSun;
    }
}