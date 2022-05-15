package src;

import java.io.*;
import java.net.ServerSocket;

/**
 * read obj from file
 */
public class ReadObj {

    private ObjectInputStream in;

    /**
     * constructor
     * @param fileAdress address of file you want to read

     */
    public ReadObj(String fileAdress) {

        try {
            File file= new File(fileAdress);
            in = new ObjectInputStream(new FileInputStream(file));
        }catch (Exception e){

        }

    }

    /**
     * closefile that you open it
     * @throws IOException ioexeption
     */
    public void closeFile() throws IOException {
        in.close();
    }
    /**
     * read object from addres
     * @return  in.readObject()  or null
     */
    public Object readFromFile() {
        try {
            return in.readObject();

        }catch (Exception e){
            return null;
        }


    }

}
