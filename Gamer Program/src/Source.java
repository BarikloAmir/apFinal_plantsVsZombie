package src;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is source and main of the information of game
 * this class used for save to file and save game
 * all the plants and zombies lawnmowers and etc
 */
public class Source implements Serializable {

    //if we have in first start=0
    //if we in game start=1
    //if we in stop start=2
    //if we in field start=3
    //if we in meno in game start=4
    //state 5 for choose new game of resume game from file
    Integer start ;

    //if game is normal type = 0
    //if game is hard type = 1
    private int type;
    //total sun colected in game
    private int totalSun = 150;
    //x or y af mouse
    private int mouseX;
    private int mouseY;
    //if want a plant true      else false
    Boolean wantPlant=false;
    //counter time that is 30  per seconds
    int counterTime=0;


    //all plants in game in each place
   private ArrayList<ArrayList<Plant>> plants=new ArrayList<ArrayList<Plant>>();
   //if zombie is in row of i   true              else false
    private Boolean[] isZombyInRow=new Boolean[5];
    //the lawnmowers of game in left of game
    private LawnMower[]lawnMowers=new LawnMower[5];

    //name of cart you choosed
    private String nameCart=" ";
    //time that past from choose a cart  in first time is -1   and if choose it it updates
    int sunCartTime=-1;
    int npeaCartTime=-1;
    int fpeaCartTime=-1;
    int cherryCartTime=-1;
    int walnutCartTime=-1;
    int beetCartTime=-1;
    int tallnutCartTime=-1;
    int jelapenoCartTime=-1;
    //the last born time of sunSky
    private int bornSkyTime=-1;
    //sun of sunsky
    private Sun sunSky;

    //this is list of zombies
     private ArrayList<Zombie> zombies;
    //this is number of zombies
    private int zombiesNumber;
    //this is time of start the game after every resume and start in begin
    long startTime ;
    //this is sum of time in the last start
    long realTime = 0;
    //now time of game after start the game
    long nowTime ;
    //this is set that we can calculate new start time or not
    int startTimeSet =1;
    //counter
    int counter ;
    //game over
    Boolean gameOver=false;

    ///when you want  to store game in file 1
    ///when yo dont wanna                   0
    int saveToFile=0;


    //name of gamer
    private String name;

    //login
    Login login ;

    long time;

    /**
     * costructor
     *
     */

    public Source(){



        zombies=new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ArrayList <Plant> plants1=new ArrayList<>();
            for (int j = 0; j < 9; j++) {

                plants1.add(null);

            } plants.add(plants1);
        }

    }


    /**
     * return type of 0normal or 1hard
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * set type
     * @param type hard1  normal0
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * return total sun
     * @return totalSun
     */
    public int getTotalSun() {
        return totalSun;
    }

    /**
     *
     * @param totalSun all sun in game that you collected
     */
    public void setTotalSun(int totalSun) {
        this.totalSun = totalSun;
    }

    /**
     *
     * @return mouseX
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     *
     * @return mouseY
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * set mouse x
     * @param mouseX mousex
     */
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * set mousey
     * @param mouseY mousey
     */
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    /**
     * return all plants
     * @return plants
     */
    public ArrayList<ArrayList<Plant>> getPlants() {
        return plants;
    }

    /**
     * return the state of zombies in rows
     * @return  isZombyInRow
     */
    public Boolean[] getIsZombyInRow() {
        return isZombyInRow;
    }

    /**
     *
     * @return lawnMowers
     */
    public LawnMower[] getLawnMowers() {
        return lawnMowers;
    }

    /**
     *
     * @return nameCart
     */
    public String getNameCart() {
        return nameCart;
    }

    /**
     * set name of cart for plant
     * @param nameCart
     */
    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    /**
     *
     * @return bornSkyTime
     */
    public int getBornSkyTime() {
        return bornSkyTime;
    }

    /**
     *
     * @param bornSkyTime time of born sky
     */
    public void setBornSkyTime(int bornSkyTime) {
        this.bornSkyTime = bornSkyTime;
    }

    /**
     * return sun sky in game
     * @return sunSky
     */
    public Sun getSunSky() {
        return sunSky;
    }

    /**
     * set sunsky
     * @param sunSky
     */
    public void setSunSky(Sun sunSky) {
        this.sunSky = sunSky;
    }

    /**
     * return all zombies
     * @return zombies
     */
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    /**
     *
     * @return zombiesNumber
     */
    public int getZombiesNumber() {
        return zombiesNumber;
    }

    /**
     *
     * @param zombiesNumber number of zombies
     */
    public void setZombiesNumber(int zombiesNumber) {
        this.zombiesNumber = zombiesNumber;
    }

    /**
     * set real time
     * @param realTime
     */
    public void setRealTime(long realTime) {
        this.realTime = realTime;
    }

    public long getRealTime() {
        return realTime;
    }

    /**
     * return name of gamer
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name of gamer
     * @param name name of gamer
     */
    public void setName(String name) {
        this.name = name;
    }
}
