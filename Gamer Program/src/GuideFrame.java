package src;

import javax.swing.*;
import java.awt.*;

/**
 * this class shows guide of game in a frame
 */
public class GuideFrame {

    //frame of ranking
    private JFrame rankingFrame;
    private JTextArea rankText;

    /**
     * constructor
     *
     */
    public GuideFrame (){
        rankingFrame = new JFrame("Guide");
        rankText = new JTextArea("this is plants vs zombies game\nit is created by hadi nazemi and amirHosein bariklo and you can contact us with " +
                "ahbariklo@gmail.com and hadii_nz@yahoo.com\nyou can see how to play in https://plantsvszombies.fandom.com/wiki/Adventure_Mode/Strategy_guide" +
                "\nfor stop the game please press Esc and for resume the game press Alt \n" +
                "for change your name please enter c to request to server \n for save game you can press space in the game\n" +
                "if you are in stop menu you go to main menu by press M \n" +"if press S  sound off \n"+"if press A for sound on \n"+
                "enjoy game;)");
        JList<String> stringJList = new JList<>();

        rankingFrame.add(rankText);

        int x = rankingFrame.getX();
        int y = rankingFrame.getY();
        rankingFrame.setLocation(x+5,y+29);

        int w = rankingFrame.getWidth();
        int h = rankingFrame.getHeight();
        rankingFrame.setSize(w+900,h+250);

        Font font = rankText.getFont();
        float f = font.getSize();
        rankText.setFont(font.deriveFont(f+7));

        rankText.setBackground(Color.DARK_GRAY);
        rankText.setForeground(Color.BLUE);
        rankText.setEnabled(false);


        rankingFrame.setVisible(true);
    }
}
