package src;

import org.ietf.jgss.GSSException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * this is zombie class
 * it is parent class
 */
public abstract class Zombie  implements Serializable {
    //life number of zombie
    private int life;
    //location of zombie
    private int locX;
    private int locY;
    //speed of zombie
    private int  speed;
    //moving of zombie
    private boolean move;
    //is burnt
    private boolean isburnt;
    //time of burnt
    private int timeBurnt=-1;

    //index of row
    protected int rowIndex;

    /**
     * constructor
     */
    public Zombie(){
        //random location
        isburnt=false;
        int rand_int1 = ThreadLocalRandom.current().nextInt();
        rowIndex = Math.abs(rand_int1)%5;
        locX = 1120;
        locY = rowIndex * 95 + 25;
        move=true;
    }

    /**
     *
     * this method getting type of game and return injury of zombie
     * @param type if type == 0  => type = normal & if type == 1  => type = hard
     * @return injury of zombie
     */
    public abstract int Injury(int type);


    /**
     * This method manages the movement of zombies
     */
    public void move(int type){
        if(move){
            if(type==0)
                locX-=speed;
            else
                locX-=2*speed;
        }

    }

    /**
     * this method reduce life number of zombie
     * @param number
     */
    public void reduceLife(int number){
        life-=number;
    }

    /**
     * this method stop zombie from moving
     */
    public void stop(){
        move = false;
    }

    /**
     * this method running moving of zombie
     */
    public void run(){
        move = true;
    }


    //getters


    public int getTimeBurnt() {
        return timeBurnt;
    }

    public boolean isIsburnt() {
        return isburnt;
    }

    public int getLife() {
        return life;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRowIndex() {
        return rowIndex;
    }
    public boolean getMove(){
        return move;
    }

    //setters

    public void setLife(int life) {
        this.life = life;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void setIsburnt(boolean isburnt) {
        this.isburnt = isburnt;
    }

    public void setTimeBurnt(int timeBurnt) {
        this.timeBurnt = timeBurnt;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
