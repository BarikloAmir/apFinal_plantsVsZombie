package src; /*** In The Name of Allah ***/


import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

/**
 * This class holds the state of the game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Seyed Mohammad Ghaffarian and amir.h bariklo and hadi nazemi
 */


public class GameState implements Serializable {
	Source source;
	Login login;

	/**
	 * constructor
	 * @param source source
	 */
	public GameState(Source source) {
		this.source=source;
		source.start = 0;
				//set name of gamer
		source.setName("guest001");
		source.setZombiesNumber(0);
		source.counter=0;
		source.gameOver=false;


		source.setType(0);

		login=new Login();
	}




	/**
	 *
	 * @return 1 if new GAME   2 if resume game
	 */
	public int newOrResumeGame(){

		if (796<=source.getMouseX()&&source.getMouseX()<=1092) {
			if (135 < source.getMouseY() && source.getMouseY() <= 202) {
				return 1;
			}
			if (228 < source.getMouseY() && source.getMouseY() <= 300){
				return 2;
			}
		}

		else return 0;
		return 0;



	}
	/**
	 * The method which updates the game state.
	 */
	public void update() {

		//calculate the real time of game
		//*************************************************************************************
		//this if calculate the startTime of game after every resume
		if(source.startTimeSet==1){
			source.startTime = System.nanoTime();
			source.startTimeSet=0;}
		source.nowTime = System.nanoTime();
		//this is time of executing of update method

		source.time = source.getRealTime()+(source.nowTime-source.startTime)/1000000000;
		source.counter+=1;
		if(source.counter==25)
			source.counter=0;

		//counter1.counter= (int) time;

		//**************************************************************************************
		//creating zombie every 30s in wave of 1

		if(source.time >50 && source.time < 200 )
			if(source.time%30 == 0){
				if(source.counter==1){
					NormalZombie zombie = new NormalZombie();

					source.getZombies().add(zombie);source.setZombiesNumber(source.getZombiesNumber()+1);
					}}

		StrongZombie strongZombie;


		//creating two zombies every 30s in wave of 2

		if(source.time >200 && source.time<380)
			if(source.time%30 == 0){

				if(source.counter==1 ){

					NormalZombie zombie = new NormalZombie();
					source.getZombies().add(zombie);source.setZombiesNumber(source.getZombiesNumber()+1);
				}
				if(source.counter==3){
					HatZombie hatZombie = new HatZombie();
					source.getZombies().add(hatZombie);
					source.setZombiesNumber(source.getZombiesNumber()+1);
				}
			}



		//creating two zombies every 25s in wave of 3
		if(source.time >380 && source.time<530)
			if(source.time%25 == 0){

				if(source.counter==1 ){

					FootballZombie footballZombie = new FootballZombie();
					source.getZombies().add(footballZombie);source.setZombiesNumber(source.getZombiesNumber()+1);}
				if(source.counter==3){
					HatZombie hatZombie = new HatZombie();
					source.getZombies().add(hatZombie);
					source.setZombiesNumber(source.getZombiesNumber()+1);
				}
			}


		//**************************************************************************************
		//check field of planets and finish of game
		//moving all of zombies
		for(Zombie zombie: source.getZombies()){
			zombie.move(getType());
			if(zombie.getLocX()<200)
				source.gameOver=true;}

		source.counterTime++;



		int i,j;

		plantToMap();

		//check zombie is in row or not
		checkZombieInRow();

			/**********************************************************
			/**
			 * if in a row exists zombie shoot pea per 2seconds
			 */
			for (i=0;i<5;i++){
				for (j=0;j<9;j++){
					if (source.getIsZombyInRow()[i]==true &&source.getPlants().get(i).get(j) instanceof PeaShooteFather&&source.counterTime%60==0){
						((PeaShooteFather) source.getPlants().get(i).get(j)).shootPea();

					}
				}
			}


			for (i=0;i<5;i++){
				for (j=0;j<9;j++){

					if (source.getPlants().get(i).get(j)instanceof PeaShooteFather){
						for (PeaFather p:((PeaShooteFather) source.getPlants().get(i).get(j)).getPeas()
						) {
							p.movePea();
						}

						if (source.getPlants().get(i).get(j) instanceof BeetRoot){
							for (PeaFather p:((BeetRoot) source.getPlants().get(i).get(j)).getDownBullets()
							) {
								p.movePeaDown();
							}
							for (PeaFather p:((BeetRoot) source.getPlants().get(i).get(j)).getUppBullets()
							) {
								p.movePeaUp();
							}
						}

					}
				}
			}
			/**************************************************************************
			 *if you have sunflower must produce sun and in you click on the sun your total sun increase 25 and remove from backyard
			 */
			 for (i=0;i<5;i++) {
				 for (j = 0; j < 9; j++) {
					 if (source.getPlants().get(i).get(j) != null) {
						 if (source.getPlants().get(i).get(j) instanceof SunFlower) {

							 ((SunFlower) source.getPlants().get(i).get(j)).produceSun(source.counterTime / 30,source.getType());
							 if (((SunFlower) source.getPlants().get(i).get(j)).getSun() != null){

								 if (source.getMouseX() < 50 + ((SunFlower) source.getPlants().get(i).get(j)).getSun().getLocX() &&
										 source.getMouseX() > ((SunFlower) source.getPlants().get(i).get(j)).getSun().getLocX() &&
										 source.getMouseY() < 50 + ((SunFlower) source.getPlants().get(i).get(j)).getSun().getLocY() &&
										 source.getMouseY() > ((SunFlower) source.getPlants().get(i).get(j)).getSun().getLocY()) {
								 	     source.setTotalSun(source.getTotalSun()+25);
									 ((SunFlower) source.getPlants().get(i).get(j)).setSun(null);
								 }
								 ((SunFlower) source.getPlants().get(i).get(j)).removeSun(source.counterTime / 30);

							 }



						 }
					 }
				 }
			 }

		//zombies eating the plants every 1s
		if(source.counter == 1) {
			eating(source.getZombies(), source.getPlants());
		}


		//removing died zombie
		if(source.counter == 20)
			removeZombie();

		//zombies injuring
		zombiesInjuring();

		/*************
		 * cherry
		 */
		cherryBomb();


		/**
		 * add LawnMower in first time
		 */
		addLawnMover();


		/**
		 * sun from sky
		 */
		sunFromSky();



		if(source.gameOver)
			if(getType() == 0)
				login.requestToServer("-,"+source.getName()+",1");

			else
				login.requestToServer("-,"+source.getName()+",3");

		if(source.time >= 540 && source.getZombies().size() == 0){
			if(getType() == 0)
				login.requestToServer("+,"+source.getName()+",3");

			else
				login.requestToServer("+,"+source.getName()+",10");


			source.start=10;}



		//create zombie in the row that have minimum plants
		if(source.time == 80 || source.time==150 || source.time==250 || source.time==400 || source.time==450)
			if(source.counter==1){
				IntelligenceComingZombie();
			}
	}




	/**
	 * this method remove zombie that died
	 */
	private void removeZombie() {
		for(Iterator<Zombie> iterator = source.getZombies().iterator();iterator.hasNext();){
			Zombie zombie = iterator.next();
			if(zombie.getLife()<=0)
				iterator.remove();}


	}

	/**
	 * this method manage injuring zombies with pea that shooting
	 */
	private void zombiesInjuring() {
		for (Zombie zombie : source.getZombies())
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 9; j++)
					if (source.getPlants().get(i).get(j) != null) {
						if (source.getPlants().get(i).get(j) instanceof PeaShoter || source.getPlants().get(i).get(j) instanceof FreezePeaShoter || source.getPlants().get(i).get(j) instanceof BeetRoot)
							for (Iterator<PeaFather> iterator = ((PeaShooteFather) source.getPlants().get(i).get(j)).peas.iterator(); iterator.hasNext(); ) {
								PeaFather pea = iterator.next();
								if (zombie.getLocX() >= pea.getLocX() - 80 && zombie.getLocX() <= pea.getLocX() + 25
										&& zombie.getRowIndex() == i) {
									iterator.remove();
									zombie.reduceLife(pea.getEffectInjury());
									if (zombie.getSpeed()>1&& source.getPlants().get(i).get(j) instanceof FreezePeaShoter)
										zombie.setSpeed(zombie.getSpeed() / 2);


								}
							}



						if (source.getPlants().get(i).get(j) instanceof BeetRoot) {
							for (Iterator<PeaFather> iterator = ((BeetRoot) source.getPlants().get(i).get(j)).getUppBullets().iterator(); iterator.hasNext(); ) {
								PeaFather pea = iterator.next();
								if (zombie.getLocX() >= pea.getLocX() - 80 && zombie.getLocX() <= pea.getLocX() + 25
										&& zombie.getRowIndex() == i) {
									iterator.remove();
									zombie.reduceLife(pea.getEffectInjury());
								}
							}
							for (Iterator<PeaFather> iterator = ((BeetRoot) source.getPlants().get(i).get(j)).getDownBullets().iterator(); iterator.hasNext(); ) {
								PeaFather pea = iterator.next();
								if (zombie.getLocX() >= pea.getLocX() - 80 && zombie.getLocX() <= pea.getLocX() + 25
										&& zombie.getRowIndex() == i) {
									iterator.remove();
									zombie.reduceLife(pea.getEffectInjury());
								}
							}

						}
					}
	}

	/**
	 * this method check zombie is in the row or not
	 */
	private void checkZombieInRow() {
		for (int i=0 ; i<5 ; i++)
			source.getIsZombyInRow()[i]=false;
		for(Zombie zombie : source.getZombies()){
			source.getIsZombyInRow()[zombie.getRowIndex()] = true;
			}


		}


	/**
	 * this method managing eating plants and reduce health of them and
	 * move of zombies and remove plants that died
	 * @param zombies zombies in the game
	 * @param plants plants in the game
	 */
	private void eating(ArrayList<Zombie> zombies, ArrayList<ArrayList<Plant>> plants) {
	int distance=0;

		for (int i=0 ; i<5 ; i++)
			for (int j=0 ; j<9 ; j++)
				if (plants.get(i).get(j) != null){
					ArrayList<Zombie>zombiesNearPlant=new ArrayList<>();
					for (Zombie zombie:zombies){
						if (zombie instanceof FootballZombie)
							distance=57;
						else distance=40;
						if (zombie.getLocX() >= plants.get(i).get(j).getLocX() && zombie.getLocX() <= plants.get(i).get(j).getLocX() + distance
								&& zombie.getRowIndex() == i){
							zombiesNearPlant.add(zombie);
						}
					}
					for(Zombie zombie:zombiesNearPlant){


						zombie.stop();
						plants.get(i).get(j).reduceHealth(zombie.Injury(source.getType()));
						if(plants.get(i).get(j).health <= 0) {
							for (Zombie zombie1:zombiesNearPlant)
								zombie1.run();
							for (Zombie zombie1:zombies){
								if (zombie1.getLocX() >= plants.get(i).get(j).getLocX() && zombie1.getLocX() <= plants.get(i).get(j).getLocX() + distance
										&& zombie1.getRowIndex() == i){
									zombie1.run();
								}
							}
							plants.get(i).set(j, null);
							break;
						}



				}
			}
	}




	/**
	 * return all plants from sorce
	 * @return source.getPlants()
	 */
	public ArrayList<ArrayList<Plant>> getPlants() {
		return source.getPlants();
	}

	/**
	 * return total sun
	 * @return totalsun
	 */
	public int getTotalSun() {
		return source.getTotalSun();
	}

	/**
	 * plant to map by getMouse x and y and determine the place of plant
	 */
	public void plantToMap(){
		//x is index of x of array of plants in map
		//y is index     y
		//262 is the minim of map started

		int x = (source.getMouseX() - (40 + 262)) / 85;
		int y = (source.getMouseY() - 90) / 95;

		if (x <= 0) {
			x = 0;
		} else if (x >= 8) {
			x = 8;
		}
		if (y <= 0) {
			y = 0;
		} else if (y >= 5) {
			y = 4;
		}

		/**
		 * for add plants to main we should underestand in which place we can add plants
		 * if  aplace is empty check which one of plants you want to implant
		 */


		if (source.getPlants().get(y).get(x) ==(null) ) {

			if (source.getNameCart().equals("sunF") && source.getMouseX() > 290 && source.getTotalSun() >= 50 &&((source.sunCartTime==-1) ||(  (source.sunCartTime!=-1)&& (getCounterTime()/30-source.sunCartTime>7.5)) )) {
				SunFlower sunFlower = new SunFlower(x * 85 + 40 + 262, y * 95 + 90);
				source.getPlants().get(y).set(x,sunFlower);
				source.sunCartTime= source.counterTime/30;


				source.setTotalSun(source.getTotalSun()-sunFlower.getCost());
				source.setNameCart(" ");
			}
			if (source.getNameCart().equals("peaF") && source.getMouseX() > 290 && source.getTotalSun() >= 100&&((source.npeaCartTime==-1) ||(  (source.npeaCartTime!=-1)&& (getCounterTime()/30-source.npeaCartTime>7.5)) )) {
				PeaShoter peaShoter=new PeaShoter(x * 85 + 40+262 ,y * 95 + 90);
				source.getPlants().get(y).set(x,peaShoter);
				source.npeaCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-peaShoter.getCost());
				source.setNameCart(" ");
			}
			if (source.getNameCart().equals("FpeaF") && source.getMouseX() > 290 && source.getTotalSun() >= 175&&((source.fpeaCartTime==-1) ||(  (source.fpeaCartTime!=-1)&& ((getCounterTime()/30-source.fpeaCartTime>7.5&&source.getType()==0)||getCounterTime()/30-source.fpeaCartTime>30&&source.getType()==1)   ) )) {
				FreezePeaShoter fpeaShoter=new FreezePeaShoter(x * 85 + 40+262 ,y * 95 + 90);
				source.getPlants().get(y).set(x,fpeaShoter); ;
				source.fpeaCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-fpeaShoter.getCost());
				source.setNameCart(" ");
			}
			if (source.getNameCart().equals("walF") && source.getMouseX() > 290 && source.getTotalSun() >= 50&&((source.walnutCartTime==-1) ||(  (source.walnutCartTime!=-1)&& (getCounterTime()/30-source.walnutCartTime>30)) )) {
				Walnut walnut=new Walnut(x * 85 + 40+262 ,y * 95 + 90);
				source.getPlants().get(y).set(x,walnut);
				source.walnutCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-walnut.getCost());
				source.setNameCart(" ");

			}
			if (source.getNameCart().equals("cherryF") && source.getMouseX() > 290 && source.getTotalSun() >= 150&&((source.cherryCartTime==-1) ||(  (source.cherryCartTime!=-1)&& ((getCounterTime()/30-source.cherryCartTime>30&&source.getType()==0)||(getCounterTime()/30-source.cherryCartTime>45&&source.getType()==1))   ) )) {
				CherryBomb cherryBomb=new CherryBomb(x * 85 + 200 ,y * 95 + 40);
				source.getPlants().get(y).set(x,cherryBomb); ;
				source.cherryCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-cherryBomb.getCost());
				source.setNameCart(" ");

			
			}
			if (source.getNameCart().equals("beet") && source.getMouseX() > 290 && source.getTotalSun() >= 125&&((source.beetCartTime==-1) ||(  (source.beetCartTime!=-1)&& (getCounterTime()/30-source.beetCartTime>30)) )) {
				BeetRoot beetRoot=new BeetRoot(x * 85 + 40+262 ,y * 95 + 90);
				source.getPlants().get(y).set(x,beetRoot);
				source.beetCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-beetRoot.getCost());
				source.setNameCart(" ");
			}
			if (source.getNameCart().equals("talnut") && source.getMouseX() > 290 && source.getTotalSun() >= 125&&((source.tallnutCartTime==-1) ||(  (source.tallnutCartTime!=-1)&& (getCounterTime()/30-source.tallnutCartTime>10)) )) {
				TallWalnut tallWalnut=new TallWalnut(x * 85 + 50+262 ,y * 95 +90);
				source.getPlants().get(y).set(x,tallWalnut);
				source.tallnutCartTime= source.counterTime/30;
				source.setTotalSun(source.getTotalSun()-tallWalnut.getCost());
				source.setNameCart(" ");
			}


			if (source.getPlants().get(y).get(x) != null) {
				source.getPlants().get(y).get(x).setIndexX(x);
				source.getPlants().get(y).get(x).setIndexY(y);

			}
			///if you want to plant this statement is tru and you must update mousex and mousey to -1
			if (source.wantPlant&& source.getPlants().get(y).get(x)!=null)
			{
				source.getPlants().get(y).get(x).setBornTime( source.counterTime/30);
				source.setMouseX(source.getMouseY()-1);
				source.setMouseX(source.getMouseX()-1);
			}

			source.wantPlant=false;
		}
	}

	/**
	 * return counter time that is 30 per one second
	 * @return source.counterTime
	 */
	public int getCounterTime() {
		return source.counterTime;
	}


	/**
	 * return start that show wich frame will be show or state of load or main meno or etc.
	 * @return source.start
	 */
	public Integer getStart() {
		return source.start;
	}


	/**
	 * return zombies from sorce
	 * @return source.getZombies()
	 */
	public ArrayList<Zombie> getZombies() {
		return source.getZombies();
	}


	/**
	 * reurn a cart that you choose for plant
	 * @return source.getNameCart()
	 */
	public String getNameCart() {
		return source.getNameCart();
	}

	/**
	 * return type of game hard or normal
	 * @return source.getType()
	 */
	public int getType() {
		return source.getType();
	}

	/**
	 *bomb explode and the nearbies zombies burnt
	 * and active burnt zombiegif in gameframe and in game frame remove them
	 */
	public void cherryBomb(){
		int i,j;
		for (i=0;i<5;i++){
			for (j=0;j<9;j++){
				if (source.getPlants().get(i).get(j)!=null&&source.getPlants().get(i).get(j) instanceof CherryBomb){

					Iterator<Zombie>itZombie=source.getZombies().iterator();
					while (itZombie.hasNext()) {
						Zombie z = itZombie.next();
						if (source.getPlants().get(i).get(j)!=null&&source.getPlants().get(i).get(j).getLocX()-170<=z.getLocX()&&z.getLocX()<=source.getPlants().get(i).get(j).getLocX()+170+200
								&& source.getPlants().get(i).get(j).getLocY()-170<=z.getLocY()&&z.getLocY()<=source.getPlants().get(i).get(j).getLocY()+170){


							if (z.isIsburnt()==false){
								//itZombie.remove();
								z.setIsburnt(true);
								z.setTimeBurnt(source.counterTime/30);

							}

						}
					}
					if (source.counterTime/30-source.getPlants().get(i).get(j).getBornTime()>=2){
						source.getPlants().get(i).set(j,null);
					}
				}
			}
		}
	}


	/**
	 * add lownMower for the first of game to map
	 */
	public void addLawnMover(){
		if (source.counterTime==1) {
			for (int i = 0; i < 5; i++) {
				source.getLawnMowers()[i] = new LawnMower(200, i * 95 + 90);
			}
		}
		else{

			int lawnMowerdeleted=-1;
			for (int i = 0; i < 5; i++) {
				Iterator<Zombie>itZombie=source.getZombies().iterator();
				while (itZombie.hasNext()) {
					Zombie z = itZombie.next();
					if (source.getLawnMowers()[i]!=null){

						if(source.getLawnMowers()[i].getLocX()>=z.getLocX()-10){

							if (source.getLawnMowers()[i].getLocY()-65-40<z.getLocY()&& z.getLocY()<source.getLawnMowers()[i].getLocY()-65+40){
								source.getLawnMowers()[i].setIsMove(true);
								itZombie.remove();
							}
						}

					}
				}
				if (source.getLawnMowers()[i]!=null&&source.getLawnMowers()[i].getLocX()>1090) lawnMowerdeleted=i;
			}
			if (lawnMowerdeleted!=-1){
				source.getLawnMowers()[lawnMowerdeleted]=null;

			}
		}

	}

	/**
	 * add sun from sky to ground
	 */
	public void sunFromSky(){
		Random randomx=new Random();
		Random randomy=new Random();int yLocSun=randomy.nextInt(5);
		int xLocSun=randomx.nextInt(9);

		/**
		 * born sun
		 * if hard in 30s
		 * if noemal in 25s
		 */
		if (source.getSunSky()==null&&((source.getBornSkyTime()==-1&&source.counterTime/30==3)||(source.getBornSkyTime()!=-1&&(source.counterTime/30-source.getBornSkyTime()==25)&&source.getType()==0)||(source.getBornSkyTime()!=-1&&(source.counterTime/30-source.getBornSkyTime()==30)&&source.getType()==1))){

			System.out.println("sun produced");
			source.setSunSky(new Sun(xLocSun * 85 + 40 + 262+40,10 ));
			source.getSunSky().setyLocEarth(yLocSun * 95 + 90+40);
			System.out.println(yLocSun);
			System.out.println("xof sun"+source.getSunSky().getLocX()+"y of sun"+(yLocSun * 95 + 90));
			source.getSunSky().setBornTime(source.counterTime/30);
			source.setBornSkyTime(source.counterTime/30);

		}
		if (source.getSunSky() != null){

			source.getSunSky().moveFromSkyToEarth();
			/**
			 * if click in sun added to your totalsun
			 */
			if (source.getSunSky()!=null&&source.getMouseX() < 50 + source.getSunSky().getLocX() &&
					source.getMouseX() > source.getSunSky().getLocX() &&
					source.getMouseY() < 50 + source.getSunSky().getLocY() &&
					source.getMouseY() > source.getSunSky().getLocY()) {
				source.setTotalSun(source.getTotalSun()+25);
				source.setSunSky(null);
			}

			/**
			 * if you dont click on sun the sun died after 10seconds
			 */
			if (source.getSunSky()!=null&& source.counterTime/30 - source.getSunSky().getBornTime() > 10) {
				System.out.println("sub died"+source.getSunSky().getBornTime());
				System.out.println("curr"+source.counterTime/30);
				source.setSunSky(null);
			}

		}

	}


	/**
	 * return remains lawnmowers
	 * @return source.getLawnMowers()
	 */
	public LawnMower[] getLawnMowers() {
		return source.getLawnMowers();
	}

	/**
	 * return sun sky
	 * @return source.getSunSky()
	 */
	public Sun getSunSky() {
		return source.getSunSky();
	}

	/**
	 * set name of cart that choose
	 * @param nameCart name of cart
	 */
	public void setNameCart(String nameCart) {
		source.setNameCart(nameCart);
	}


	/**
	 * set login frame
	 * @param login loginClass
	 */
	public void getLoginFromGameLoop(Login login){
		this.login = login;
	}




	/**
	 * this method create a strong zombie in the row have minimum plants
	 */
	private void IntelligenceComingZombie() {
		int[] rowsForce = new int[5];
		for (int i=0 ; i<5 ; i++)
			rowsForce[i]=0;

		for (int i=0 ; i<5 ; i++)
			for (int j=0 ; j<9 ; j++)
				if(getPlants().get(i).get(j)!=null)
					rowsForce[i] += 1;

//				for (int i=0 ; i<5; i++)
//					System.out.println("jkdjfkj"+rowsForce[i]);
		int min = 0;

		for (int i=1 ; i<5 ; i++)
			if(rowsForce[min]>rowsForce[i])
				min = i;

		System.out.println(min);
		StrongZombie strongZombie = new StrongZombie();
		strongZombie.newSetLocY(min);

		source.getZombies().add(strongZombie);

	}

}

