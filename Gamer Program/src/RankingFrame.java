package src;

import javax.swing.*;
import java.awt.*;

/**
 * this class show rancking in game from server
 * has a rancking frame that show the gamers and grade and results
 */
public class RankingFrame {
    //frame of ranking
    private JFrame rankingFrame;
    private JTextArea rankText;

    /**
     * constructor
     * @param result it is ranking
     */
    public RankingFrame(String result){
        rankingFrame = new JFrame("Ranking");
        rankText = new JTextArea(result);
        JList<String> stringJList = new JList<>();

        for (String gamer : result.split("\n")) {
            JLabel jLabel = new JLabel(gamer);
            stringJList.add(jLabel);
        }
        rankingFrame.add(rankText);

        int x = rankingFrame.getX();
        int y = rankingFrame.getY();
        rankingFrame.setLocation(x+5,y+29);

        int w = rankingFrame.getWidth();
        int h = rankingFrame.getHeight();
        rankingFrame.setSize(w+50,h+680);

        Font font = rankText.getFont();
        float f = font.getSize();
        rankText.setFont(font.deriveFont(f+5));

        rankText.setBackground(Color.GREEN);
        rankText.setForeground(Color.BLUE);


        rankingFrame.setVisible(true);
    }
}
