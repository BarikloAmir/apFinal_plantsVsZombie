package src;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * this class make sounds and run from main
 */
public class MakeSound implements Runnable {

    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    //file of background sound
    private String backgroundFileSound;
    //
    private String menuFileSound;
    //
    private Source source;

    private String zombiesComing;

    boolean sound=true;



    /**
     * constructor
     *
     * @param backgroundFileSound name of background sound file
     */
    public MakeSound(Source source, String backgroundFileSound, String menuFileSound,String zombiesComing) {
        this.backgroundFileSound = backgroundFileSound;
        this.source = source;
        this.menuFileSound = menuFileSound;
        this.zombiesComing = zombiesComing;
    }

    /**
     * @param filename the name of the file that is going to be played
     */
    public void playSound(String filename) {

        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {

                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }

            if (!sound)break;
            if(source.start == 0 && filename.equals("background.wav"))
                break;
            if(source.start == 1 && (source.time == 30||source.time==31 || source.time==200 || source.time==201 || source.time==380 || source.time==381)
                    && !filename.equals("zombies_coming.wav"))
                break;


        }

        sourceLine.drain();
        sourceLine.close();
    }

    @Override
    public void run() {

        while (true) {
            System.out.println(sound);
            if(!sound){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;}
            if(source.start == 1 && (source.time == 30||source.time==31 || source.time==200 || source.time==201 || source.time==380 || source.time==381)  )
                playSound(zombiesComing);
            else {
                if(source.start!=0)
                    playSound(backgroundFileSound);
                if(source.start == 0)
                    playSound(menuFileSound);}


        }
    }
}