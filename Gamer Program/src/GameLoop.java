package src; /** In The Name of Allah **/

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 *
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {

	/**
	 * Frame Per Second.
	 * Higher is better, but any value above 24 is fine.
	 */
	public static final int FPS = 30;

	Source source;
	private GameFrame canvas;
	private GameState state;
	private Listener listener;
	private Login login = new Login();
	private String name;

	boolean sound = true;
	MakeSound makeSound;




	//if 0 normal 1 hard
	private int hardNormal;

	//firsttime 0 or lasttime 1
	int numInit=-1;

	public GameLoop(GameFrame frame) {
		canvas = frame;
	}

	/**
	 * This must be called before the game loop starts.
	 */
	public void init() throws IOException {

		source=new Source();
		state = new GameState(source);


		state.getLoginFromGameLoop(login);


		listener=new Listener(state);

		canvas.addKeyListener(listener.getKeyListener());
		canvas.addMouseListener(listener.getMouseListener());
		canvas.addMouseMotionListener(listener.getMouseMotionListener());



	}


	int m = 0;
	@Override
	public void run() {
		boolean gameOver = false;

		while (!gameOver) {
			try {
				long start = System.currentTimeMillis();
				File stateFile=new File("source.ser");

				if (state.source.start==5){
					hardNormal=listener.hardNormal;


				}


				if (state.source.start==5){

					//new game
					if(state.newOrResumeGame()==1){
						System.out.println("newGame");
						init();
						state.source.start=1;

						state.source.setType(hardNormal);

					}
					//resume game
					if (state.newOrResumeGame()==2){
						System.out.println("resume");
						if (stateFile.exists()){
							//loadFromFile=1;
							ReadObj readObj=new ReadObj("source.ser");
							state.source= (Source) readObj.readFromFile();
							readObj.closeFile();

							state.source.start=1;
						}

					}
				}

				if(m == 0){
					login.showGUI();
					m=1;}

				state.source.setName(login.name);



				makeSound.sound = listener.sound;
				//System.out.println(makeSound.sound);


				if(state.source.start == 1)
					state.update();

				if (state.source.start==4&& state.source.saveToFile==1){
					//////add state to state.ser
					WriteObject writeObjectToCourseFile;
					try {
						writeObjectToCourseFile = new WriteObject("source.ser");
						writeObjectToCourseFile.writeObjectToFile(state.source);
						writeObjectToCourseFile.closeFile();
						state.source.saveToFile=0;

					} catch (IOException e) {
						e.printStackTrace();
					}


				}


				canvas.render(state);


				//
				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);
			} catch (InterruptedException | IOException ex) {
			}
		}
	}
}