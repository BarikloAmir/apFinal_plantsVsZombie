package src;


import javax.swing.*;

import javax.swing.plaf.nimbus.State;
import java.awt.event.*;
import java.io.Serializable;

/**
 * this window is for mouse listener or key listener
 * all of keys that pressed in game  handel in this class
 *
 */
public class Listener implements Serializable{
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    Boolean sound=true;

    //if 0 normal   if 1 hard
    int hardNormal;

    private GameState state;

    /**
     * constructor
     * @param state gamestate
     */
    public Listener(GameState state){
        this.state=state;
        keyHandler=new KeyHandler();
        mouseHandler=new MouseHandler();
    }
    public KeyListener getKeyListener() {
        return keyHandler;
    }
    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }



    /**
     * The keyboard handler.
     */
    class KeyHandler implements KeyListener , Serializable {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            //key for change frame fram start 0 to start 5
            if (state.source.start == 0&& e.getKeyCode() == KeyEvent.VK_ENTER) {
                state.source.start = 5;
            }
            //key for stop the game
            if (state.source.start == 1 && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                state.source.start = 2;
                state.source.startTimeSet = 1;
                state.source.setRealTime(state.source.getRealTime()+(state.source.nowTime - state.source.startTime) / 1000000000);
            }
            //key for resume the game
            if (state.source.start == 2 && e.getKeyCode() == KeyEvent.VK_ALT)
                state.source.start = 1;

            //fore save game in file

            if ((state.source.start == 1||state.source.start == 5) && e.getKeyCode() == KeyEvent.VK_SPACE) {
                state.source.start = 4;
                state.source.saveToFile = 1;
                state.source.startTimeSet = 1;
                state.source.setRealTime(state.source.getRealTime()+(state.source.nowTime - state.source.startTime) / 1000000000);
                JFrame frame=new JFrame();
                JOptionPane.showMessageDialog(frame,"the game will be saved","save information",JOptionPane.INFORMATION_MESSAGE);


            }

            //creating frame for ranking
            RankingFrame rankingFrame;
            if(e.getKeyCode() == KeyEvent.VK_R&& state.login!=null){
                state.login.requestToServer("ranking");
                rankingFrame = new RankingFrame(state.login.result);}
            if(e.getKeyCode() == KeyEvent.VK_C&& state.login!=null){
                state.login.showGUI();
            }

            //show guide frame
            GuideFrame guideFrame;
            if(e.getKeyCode() == KeyEvent.VK_G)
                guideFrame = new GuideFrame();

            //resume after stop game
            if (state.source.start==4&& e.getKeyCode() == KeyEvent.VK_ALT ){
                state.source.start=1;
            }

            //show main menu
            if (state.source.start == 2 && e.getKeyCode() == KeyEvent.VK_M) {
                state.source.start = 0;


            }
            //hard Game
            if (state.source.start == 0 && e.getKeyCode() == KeyEvent.VK_H) {
                state.source.start = 0;
                state.source.setType(1);System.out.println("hard");
                hardNormal=1;
                JFrame frame=new JFrame();
                JOptionPane.showMessageDialog(frame,"the game will be hard","hard or normal",JOptionPane.INFORMATION_MESSAGE);


            }
            //normal game
            if (state.source.start == 0 && e.getKeyCode() == KeyEvent.VK_N) {
                state.source.start = 0;
                state.source.setType(0);System.out.println("normal");
                hardNormal=0;
                JFrame frame=new JFrame();
                JOptionPane.showMessageDialog(frame,"the game will be Normal","hard or normal",JOptionPane.INFORMATION_MESSAGE);

            }

            //sound manage
            if(e.getKeyCode()==KeyEvent.VK_S){
                JFrame stopFrame = new JFrame();
                JOptionPane.showMessageDialog(stopFrame,"SOUND OFF","SOUND SETTING",JOptionPane.INFORMATION_MESSAGE);
                sound = false;}

            if (e.getKeyCode() == KeyEvent.VK_A){
                JFrame runFrame = new JFrame();
                JOptionPane.showMessageDialog(runFrame,"SOUND ON","SOUND SETTING",JOptionPane.INFORMATION_MESSAGE);
                sound = true;}

        }
        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    /**
     * The mouse handler.
     */
    class MouseHandler implements MouseListener, MouseMotionListener,Serializable {

        @Override
        public void mouseClicked(MouseEvent e) {
            state.source.setMouseX(e.getX());
            state.source.setMouseY(e.getY());
            System.out.println("xmouse"+e.getX());
            System.out.println("ymouse"+e.getY());

            ////save place that mouse clicked on it in mousex and y in source
            if (0<e.getX()&&e.getX()<65&& 90<e.getY()&&e.getY()<175 ){
                state.setNameCart("sunF");
            }
            if (0<e.getX()&&e.getX()<65&& 175<e.getY()&&e.getY()<260 ){
                state.setNameCart("peaF");
            }
            if (0<e.getX()&&e.getX()<65&& 260<e.getY()&&e.getY()<345 ){
                state.setNameCart("FpeaF");
            }
            if (0<e.getX()&&e.getX()<65&& 345<e.getY()&&e.getY()<430 ){
                state.setNameCart("walF");
            }
            if (0<e.getX()&&e.getX()<65&& 430<e.getY()&&e.getY()<515 ){
                state.setNameCart("cherryF");
            }
            if (65<e.getX()&&e.getX()<155&& 90<e.getY()&&e.getY()<180 ){
                state.setNameCart("beet");
            }
            if (65<e.getX()&&e.getX()<155&& 180<e.getY()&&e.getY()<326 ){
                state.setNameCart("talnut");
            }


            //if you wanna plant  and click on carts in left of game  state.source.wantPlant = true
            if (state.source.getMouseX() < 1085 && state.source.getMouseX() > 262 && state.source.getMouseY() < 600 && state.source.getMouseY() > 50 && !state.getNameCart().equals(" ")) {
                state.source.wantPlant = true;
            }


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
//	
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }


}
