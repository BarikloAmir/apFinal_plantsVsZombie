package MultiThreadServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    //list of gamers
    private static ArrayList<Gamer> gamers;


    public static void main(String[] args) {
        gamers = new ArrayList<>();
        Gamer guest=new Gamer("guest001");
        gamers.add(guest);
        ExecutorService pool = Executors.newCachedThreadPool();
        int count = 0;
        try (ServerSocket welcomingSocket = new ServerSocket(7660)) {
            System.out.print("Server started.\nWaiting for a client ... ");
            while (count < 1000000) {
                Socket connectionSocket = welcomingSocket.accept();
                count++;
                System.out.println("client accepted!");
                pool.execute(new ClientHandler(connectionSocket, count,gamers));
            }
            pool.shutdown();
            System.out.print("done.\nClosing server ... ");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("done.");
    }

}

class ClientHandler implements Runnable {

    private Socket connectionSocket;
    private int clientNum;
    //list of gamers
    ArrayList<Gamer> gamers;


    public ClientHandler(Socket connectionSocket, int clientNum, ArrayList<Gamer> gamers) {
        this.connectionSocket = connectionSocket;
        this.clientNum = clientNum;
        this.gamers = gamers;
    }

    /**
     * this method check that we have this name or not
     *
     * @param name new name
     * @return false if we have that name else true
     */
    public Boolean check(String name) {
        for (Gamer gamer : gamers)
            if (name.equals(gamer.getName()))
                return false;

        return true;
    }

    /**
     * this method find gamer with this name
     *
     * @param name name of gamer
     * @return object of that gamer
     */
    public Gamer findGamer(String name) {
        for (Gamer gamer : gamers)
            if (name.equals(gamer.getName()))
                return gamer;

        return null;
    }

    /**
     * this method
     *
     * @return
     */
    public void ranking() {
        for (int i = 0; i < gamers.size(); i++)
            for (int j = i + 1; j < gamers.size(); j++)
                if (gamers.get(i).getScore() < gamers.get(j).getScore()) {
                    Gamer copy = gamers.get(i);
                    gamers.set(i, gamers.get(j));
                    gamers.set(j, copy);
                }

    }


    @Override
    public void run() {

            try {
                OutputStream out = connectionSocket.getOutputStream();
                InputStream in = connectionSocket.getInputStream();
                byte[] buffer = new byte[2048];

                while (true) {
                    int read = in.read(buffer);
                    if(read==-1)
                        break;
                    String request;
                    System.out.println(read);
                    if (read != -1) {
                        request = new String(buffer, 0, read);
                        String[] requestArray = request.split(",");
                        //creating new gamer
                        Gamer newGamer;
                        if (requestArray[0].equals("new") )
                            if(check(requestArray[1])){
                                newGamer = new Gamer(requestArray[1]);
                                gamers.add(newGamer);
                                for (Gamer gamer : gamers)
                                    System.out.println(gamer.getName());
                                out.write("done".getBytes());
                                }
                            else
                                out.write("we have this gamer ".getBytes());

                        //change name of gamer
                        if (requestArray[0].equals("change"))
                                if( check(requestArray[1]) && !check(requestArray[2]))
                                 {
                            findGamer(requestArray[2]).setName(requestArray[1]);
                            for (Gamer gamer : gamers)
                                System.out.println(gamer.getName());
                            out.write("done".getBytes());
                                }
                                else
                                    out.write("sorry!".getBytes());


                        //increase score
                        if (requestArray[0].equals("+")) {
                            findGamer(requestArray[1]).increaseScore(Integer.parseInt(requestArray[2]));
                            out.write("done".getBytes());
                        }
                        //reduce score
                        if (requestArray[0].equals("-")) {
                            findGamer(requestArray[1]).reduceScore(Integer.parseInt(requestArray[2]));
                            out.write("done".getBytes());
                        }
                        //ranking
                        StringBuilder ranking = new StringBuilder();
                        if (requestArray[0].equals("ranking")) {
                            ranking();
                            for (Gamer gamer : gamers)
                                ranking.append(gamer.getName() + " : " + gamer.getScore() + "\n");
                            String output = String.valueOf(ranking);
                            out.write(output.getBytes());
                        }
                        //checking
                        if(requestArray[0].equals("check"))
                            if(!check(requestArray[1]))
                                out.write("true".getBytes());
                            else
                                out.write("false".getBytes());
                        //finishing
                        if (requestArray[0].equals("finish")) {
                            out.write("bye".getBytes());
                        }


                        Thread.sleep(2000);
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }













class Gamer {
    //name of gamer
    private String name;
    //score of gamer
    private int score;

    /**
     * constructor
     * @param name name of gamer
     */
    public Gamer(String name){
        this.name = name;
        score = 0;
    }

    /**
     * this method reducing score of gamer
     * @param value value that must be reduced
     */
    public void reduceScore(int value){
        score-=value;
    }

    /**
     * this method increasing score of gamer
     * @param value value that must be increased
     */
    public void increaseScore(int value){
        score+=value;
    }

    ///////////////////////////setters and getters///////////////////////////


    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
