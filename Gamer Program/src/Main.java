package src; /*** In The Name of Allah ***/

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian edited by AmirHosein Bariklo and Hadi Nazemi
 */

public class Main {


	private static MakeSound makeSound;

    public static void main(String[] args) {






			// Initialize the global thread-pool
			ThreadPool.init();

			// Show the game menu ...

			// After the player clicks 'PLAY' ...
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					GameFrame frame = new GameFrame("AmirHosein Bariklo+Hadi Nazemi->finalProjectAp");
					frame.setLocationRelativeTo(null); // put frame at center of screen
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					frame.initBufferStrategy();
					// Create and execute the game-loop
					GameLoop game = new GameLoop(frame);
					try {
						game.init();
						 makeSound = new MakeSound(game.source,"background.wav","menu.wav","zombies_coming.wav");
						 game.makeSound=makeSound;
					} catch (IOException e) {
						e.printStackTrace();
					}
					ThreadPool.execute(game);ThreadPool.execute(makeSound);
//
					// and the game starts ...
				}
			});

	}
}
