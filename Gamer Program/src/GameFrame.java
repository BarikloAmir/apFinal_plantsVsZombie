package src; /*** In The Name of Allah ***/



import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This structure uses the modern BufferStrategy approach for 
 * double-buffering; actually, it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 * 
 * @author Seyed Mohammad Ghaffarian edited by AmirHosein Baiklo and Hadi Nazemi
 */
public class GameFrame extends JFrame {
	private BufferedImage newOrResumeGame;
	private  BufferedImage field;
	//backGrounds
	private BufferedImage backyard;
	private BufferedImage fistScreen;
	//sunflower cart
	private BufferedImage sunCartActive;
	private BufferedImage sunCartInActive;
	//pea shooter
	private BufferedImage activeCartPea;
	private BufferedImage inActiveCartiPea;


	//freeze pea
	private BufferedImage activeCartFPea;
	private BufferedImage inActiveCartiFPea;
	//cart of walnut
	private BufferedImage cartWalnutAct;
	private BufferedImage cartWalnutInact;
	//cart of cherry
	private BufferedImage cartCherryAct;
	private BufferedImage cartCherryInact;
	//cart beetRoot
	private BufferedImage cartBeetAct;
	private BufferedImage cartBeetInact;

	//cart tall walnut
	private BufferedImage cartTallnutAct;
	private BufferedImage cartTallnutInact;


	private BufferedImage winImage;
	// meno for stop game
	private BufferedImage stopMenu;



	//////////////////gifs
	//gif lawnmower
	private Image staticLawnImg;
	private Image moveLawnImg;

	private Image imgSFNormal;
	private Image imgSFReady;
	private Image imgSFDying;

	public static final int GAME_HEIGHT = 720;                  // 720p game resolution
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
	
	private BufferStrategy bufferStrategy;
	
	public GameFrame(String title) {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH-160, GAME_HEIGHT-60);

		//// gif of lawn mower
		moveLawnImg=new ImageIcon("lawn_mower.gif").getImage();
		staticLawnImg=new ImageIcon("lawnmowerActivated.gif").getImage();

		try {

			stopMenu=ImageIO.read(new File("GameMenu.png"));
			newOrResumeGame=ImageIO.read(new File("levelmenu.png"));
			field = ImageIO.read(new File("background.jpg"));
			backyard = ImageIO.read(new File("backyard.jpg"));
			fistScreen = ImageIO.read(new File("first_screen.jpg"));
			sunCartActive=ImageIO.read(new File("sunfloweractC.png"));
			sunCartInActive=ImageIO.read(new File("sunflowerinactC.png"));
			activeCartPea =ImageIO.read(new File("peaactiveCart.png"));
			inActiveCartiPea =ImageIO.read(new File("peainactiveCart.png"));

			activeCartFPea =ImageIO.read(new File("card_freezepeashooter.png"));
			inActiveCartiFPea =ImageIO.read(new File("card_freezeinact.png"));

			cartWalnutAct =ImageIO.read(new File("wal.png"));
			cartWalnutInact =ImageIO.read(new File("walD.png"));

			cartCherryAct =ImageIO.read(new File("cherryBut.png"));
			cartCherryInact =ImageIO.read(new File("cherryButD.png"));

			cartBeetAct=ImageIO.read(new File("active_beetroot.png"));
			cartBeetInact=ImageIO.read(new File("inactive_beetroot.png"));

			cartTallnutAct=ImageIO.read(new File("Tall-nutSeedPacket.png"));
			cartTallnutInact=ImageIO.read(new File("ImitaterTallnutSp.png"));

			winImage=ImageIO.read(new File("backgroundwin.jpg"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		// Initialize the JFrame ...
		//
	}
	
	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	
	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		// Get a new graphics context to render the current frame
		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		try {
			// Do the rendering
			doRendering(graphics, state);
		} finally {
			// Dispose the graphics, because it is no more needed
			graphics.dispose();
		}
		// Display the buffer
		bufferStrategy.show();
		// Tell the system to do the drawing NOW;
		// otherwise it can take a few extra ms and will feel jerky!
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state) {
		if(state.source.start == 10)
			g2d.drawImage(winImage,null,0,27);
		if (state.source.start== 5){
			g2d.drawImage(newOrResumeGame,null,0,27);

		}
		//this is backGround of menu game
		if (state.source.start == 0)
			g2d.drawImage(fistScreen, null, 0, 27);



		//meno of stop
		if (state.source.start == 2){
			g2d.drawImage(stopMenu, null, 0, 27);

		}
	


		if (state.source.start == 1) {
			//add background graphic to frame
			g2d.drawImage(backyard, null, 0, 27);

			//check the game over


			//add sun flower cart graphic to frame and others
			if (state.getTotalSun() >= 50 && ((state.source.sunCartTime == -1) || ((state.source.sunCartTime != -1) && (state.getCounterTime() / 30 - state.source.sunCartTime > 7.5)))) {
				g2d.drawImage(sunCartActive, null, 10, 90);
			} else {
				g2d.drawImage(sunCartInActive, null, 10, 90);
			}
			if (state.getTotalSun() >= 100 && ((state.source.npeaCartTime == -1) || ((state.source.npeaCartTime != -1) && (state.getCounterTime() / 30 - state.source.npeaCartTime > 7.5)))) {
				g2d.drawImage(activeCartPea, null, 10, 175);
			} else {
				g2d.drawImage(inActiveCartiPea, null, 10, 175);
			}
			if (state.getTotalSun() >= 175 && ((state.source.fpeaCartTime == -1) || (((state.source.fpeaCartTime != -1) && (state.getCounterTime() / 30 - state.source.fpeaCartTime > 7.5) && (state.getType() == 0)) || ((state.source.fpeaCartTime != -1) && (state.getCounterTime() / 30 - state.source.fpeaCartTime > 30) && (state.getType() == 1))))) {
				g2d.drawImage(activeCartFPea, null, 10, 260);
			} else {
				g2d.drawImage(inActiveCartiFPea, null, 10, 260);
			}
			if (state.getTotalSun() >= 50 && ((state.source.walnutCartTime == -1) || ((state.source.walnutCartTime != -1) && (state.getCounterTime() / 30 - state.source.walnutCartTime > 30)))) {
				g2d.drawImage(cartWalnutAct, null, 10, 345);
			} else {
				g2d.drawImage(cartWalnutInact, null, 10, 345);
			}
			if (state.getTotalSun() >= 150 && ((state.source.cherryCartTime == -1) || (((state.source.cherryCartTime != -1) && (state.getCounterTime() / 30 - state.source.cherryCartTime > 30) && state.getType() == 0) || ((state.source.cherryCartTime != -1) && (state.getCounterTime() / 30 - state.source.cherryCartTime > 45) && state.getType() == 1)))) {
				g2d.drawImage(cartCherryAct, null, 10, 430);
			} else {
				g2d.drawImage(cartCherryInact, null, 10, 430);
			}

			if (state.getTotalSun() >= 125 && ((state.source.beetCartTime == -1) || ((state.source.beetCartTime != -1) && (state.getCounterTime() / 30 - state.source.beetCartTime > 30)))) {
				g2d.drawImage(cartBeetAct, null, 90, 90);
			} else {
				g2d.drawImage(cartBeetInact, null, 90, 90);
			}

			if (state.getTotalSun() >= 125 && ((state.source.tallnutCartTime == -1) || ((state.source.tallnutCartTime != -1) && (state.getCounterTime() / 30 - state.source.tallnutCartTime > 10)))) {
				g2d.drawImage(cartTallnutAct, null, 90, 180);
			} else {
				g2d.drawImage(cartTallnutInact, null, 90, 180);
			}


			//paint total sun's number
			paintTotalSun(state, g2d);


			/**
			 * add lawnMower to map
			 */
			for (int i = 0; i < 5; i++) {
				if (state.getLawnMowers()[i] != null) {
					g2d.drawImage(moveLawnImg, state.getLawnMowers()[i].getLocX(), state.getLawnMowers()[i].getLocY(), null);

					if (state.getLawnMowers()[i].getIsMove() == true) {
						state.getLawnMowers()[i].move();
						g2d.drawImage(moveLawnImg, state.getLawnMowers()[i].getLocX(), state.getLawnMowers()[i].getLocY(), null);

					}
				}
			}


			// add flowers to background
			int i;
			int j;
			for (i = 0; i < 5; ++i) {
				for (j = 0; j < 9; ++j) {
					if (state.getPlants().get(i).get(j) != null) {

						//gif of tallnut
						if (state.getPlants().get(i).get(j) instanceof TallWalnut) {
							imgSFNormal = new ImageIcon("Tall-nut.png").getImage();
							imgSFReady = new ImageIcon("Tallnut_cracked2.png").getImage();

							if (state.getPlants().get(i).get(j).health >= 150)
								g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX() - 10, state.getPlants().get(i).get(j).getLocY() - 30, null);
							else{
								g2d.drawImage(imgSFReady, state.getPlants().get(i).get(j).getLocX() - 10, state.getPlants().get(i).get(j).getLocY() - 30, null);
							}


						}

						////gif of cherry bomb
						else if (state.getPlants().get(i).get(j) instanceof CherryBomb) {
							imgSFNormal = new ImageIcon("cherry.gif").getImage();

							g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

						}

						/////draw beetroot
						else if (state.getPlants().get(i).get(j) instanceof BeetRoot) {
							imgSFDying = new ImageIcon("beetroot_dying.gif").getImage();
							imgSFNormal = new ImageIcon("beetroot.gif").getImage();

							if (state.getPlants().get(i).get(j).health > 5)
								g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);
							else {
								g2d.drawImage(imgSFDying, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

								state.getPlants().get(i).set(j,null);
							}


						}

						///draw peashooter
						else if (state.getPlants().get(i).get(j) instanceof PeaShoter) {
							imgSFDying = new ImageIcon("pea_shooter_dying.gif").getImage();
							imgSFNormal = new ImageIcon("peashooter.gif").getImage();
							imgSFReady = new ImageIcon("pea_shooter.gif").getImage();
							if (state.getPlants().get(i).get(j).health > 0)
								g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);
							else {
								g2d.drawImage(imgSFDying, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);
								state.getPlants().get(i).set(j,null);

							}
						}
						//draw freeze pea shooter
						else if (state.getPlants().get(i).get(j) instanceof FreezePeaShoter) {
							imgSFNormal = new ImageIcon("freezepeashooter.gif").getImage();
							g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

							if (state.getPlants().get(i).get(j).health < 0)
								state.getPlants().get(i).set(j,null);


						}
						//draw sunflower and its sun
						else if (state.getPlants().get(i).get(j) instanceof SunFlower) {
							imgSFDying = new ImageIcon("sun_flower_dying.gif").getImage();
							imgSFNormal = new ImageIcon("sunflower.gif").getImage();
							imgSFReady = new ImageIcon("sun_flower.gif").getImage();

							if (state.getPlants().get(i).get(j) instanceof SunFlower) {
								Image img = new ImageIcon("sun.gif").getImage();
								if (((SunFlower) state.getPlants().get(i).get(j)).getSun() != null) {
									g2d.drawImage(img, ((SunFlower) state.getPlants().get(i).get(j)).getSun().getLocX(), ((SunFlower) state.getPlants().get(i).get(j)).getSun().getLocY(), null);
								}
							}

							if (((SunFlower) state.getPlants().get(i).get(j)).getIfProduceSun() == true)
								g2d.drawImage(imgSFReady, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

							else if (state.getPlants().get(i).get(j).health > 0)
								g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

							else {

								g2d.drawImage(imgSFDying, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);
								state.getPlants().get(i).set(j,null);
							}
						}
						//draw walnut
						else if (state.getPlants().get(i).get(j) instanceof Walnut) {
							imgSFDying = new ImageIcon("walnut_dead.gif").getImage();
							imgSFNormal = new ImageIcon("walnut_full_life.gif").getImage();
							imgSFReady = new ImageIcon("walnut_half_life.gif").getImage();

							if (state.getPlants().get(i).get(j).health >= 75)
								g2d.drawImage(imgSFNormal, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

							else {
								if (0 < state.getPlants().get(i).get(j).health && state.getPlants().get(i).get(j).health < 75)
									g2d.drawImage(imgSFReady, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);

								else {
									g2d.drawImage(imgSFDying, state.getPlants().get(i).get(j).getLocX(), state.getPlants().get(i).get(j).getLocY(), null);
									state.getPlants().get(i).set(j,null);

								}
							}
						}


						}


				}
			}
			/**
			 * sun from Sky to earth
			 */
			if (state.getSunSky() != null) {
				Image img = new ImageIcon("sun.gif").getImage();
				g2d.drawImage(img, state.getSunSky().getLocX(), state.getSunSky().getLocY(), null);
			}


			//draw bullet and peas
			for (i = 0; i < 5; ++i) {
				for (j = 0; j < 9; ++j) {
					if (state.getPlants() != null && state.getPlants().get(i).get(j) instanceof PeaShooteFather) {

						for (PeaFather p : ((PeaShooteFather) state.getPlants().get(i).get(j)).getPeas()
						) {
							if (p instanceof PeaNormal) {
								BufferedImage image;
								try {
									image = ImageIO.read(new File("pea.png"));
									g2d.drawImage(image, null, p.getLocX(), p.getLocY());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if (p instanceof PeaFreeze) {
								BufferedImage image;
								try {
									image = ImageIO.read(new File("freezepea.png"));
									g2d.drawImage(image, null, p.getLocX(), p.getLocY());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if (p instanceof BeetBullet) {
								BufferedImage image;
								try {
									image = ImageIO.read(new File("beetbullet.png"));
									g2d.drawImage(image, null, p.getLocX(), p.getLocY());
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						}
					}
				}
			}



				//draw zombies in frame
				for (Zombie zombie : state.getZombies()) {

					//if zombie is burnt show gif of burnt zombie else show the gif of which ones
					if (zombie.isIsburnt()==true){
					Image burntzImg= (new ImageIcon("burntZombie.gif").getImage());
					g2d.drawImage(burntzImg, zombie.getLocX(), zombie.getLocY()-70, null);



				}else {
						//show normalzombies
						if (zombie instanceof NormalZombie) {
							Image moveImage = (new ImageIcon("zombie_normal.gif").getImage());
							Image dyeImage = (new ImageIcon("zombie_normal_dying.gif").getImage());
							if (zombie.getLife() > 0)
								g2d.drawImage(moveImage, zombie.getLocX(), zombie.getLocY(), null);
							else
								g2d.drawImage(dyeImage, zombie.getLocX(), zombie.getLocY(), null);


						}
						//show hatzombies
						if (zombie instanceof HatZombie) {
							Image moveImage = (new ImageIcon("z3.gif").getImage());
							Image dyeImage = (new ImageIcon("zombie_normal_dying.gif").getImage());
							Image halfLife = new ImageIcon("zombie_normal.gif").getImage();

							if (zombie.getLife() > 200)
								g2d.drawImage(moveImage, zombie.getLocX(), zombie.getLocY(), null);
							else if (zombie.getLife() < 200 && zombie.getLife() > 0)
								g2d.drawImage(halfLife, zombie.getLocX(), zombie.getLocY(), null);
							else
								g2d.drawImage(dyeImage, zombie.getLocX(), zombie.getLocY(), null);
						}
						//show footnbal zombiez
						if (zombie instanceof FootballZombie) {
							Image moveImage = new ImageIcon("zombie_football.gif").getImage();
							Image dyeImage = new ImageIcon("zombie_football_dying.gif").getImage();
							if (zombie.getLife() > 0)
								g2d.drawImage(moveImage, zombie.getLocX(), zombie.getLocY(), null);
							else
								g2d.drawImage(dyeImage, zombie.getLocX(), zombie.getLocY(), null);


						}
						///show strong zombies
						if (zombie instanceof StrongZombie) {

							Image moveImage = (new ImageIcon("zombiSatli.gif").getImage());

							Image dyeImage = (new ImageIcon("zombie_normal_dying.gif").getImage());
							Image halfLife = new ImageIcon("zombie_normal.gif").getImage();

							if (zombie.getLife() > 200)
								g2d.drawImage(moveImage, zombie.getLocX(), zombie.getLocY(), null);
							else if (zombie.getLife() < 200 && zombie.getLife() > 0)
								g2d.drawImage(halfLife, zombie.getLocX(), zombie.getLocY(), null);
							else
								g2d.drawImage(dyeImage, zombie.getLocX(), zombie.getLocY(), null);

						}

					}

				}

				////remove burnt zombies
			Iterator<Zombie> itZombie=state.source.getZombies().iterator();
			while (itZombie.hasNext()) {
				Zombie z = itZombie.next();


				if (state.source.counterTime / 30 - z.getTimeBurnt() >= 1&&z.isIsburnt()==true) {

					itZombie.remove();
				}
			}
				if (state.source.gameOver) {
					g2d.drawImage(field, null, 0, 27);
					state.source.start = 3;
				}
			}
		}

	/**
	 * paint total sun number to background
	 * @param state gamestate
	 * @param g graphic2d
	 */
	public void paintTotalSun(GameState state,Graphics g) {
		Graphics2D g2D;
		g2D = (Graphics2D) g;
		FontRenderContext frc = g2D.getFontRenderContext();
		Font font1 = new Font("Courier", Font.BOLD, 20);
		String str1 =String.valueOf(state.getTotalSun());
		TextLayout tl = new TextLayout(str1, font1, frc);
		g2D.setColor(Color.gray);
		tl.draw(g2D, 35, 556);
	}
}
