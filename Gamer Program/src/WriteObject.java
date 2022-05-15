package src;

import java.io.*;

/**
 * write object to file
 */
public class WriteObject implements Serializable {

    private ObjectOutputStream out;
    private String address;

    /**
     * constractor
     * @param address address
     * @throws IOException ioexeption not find file
     */
    public WriteObject(String address) throws IOException {

        this.address=address;
        File adminFile=new File(address);
        if (adminFile.exists()){
            adminFile.delete(); }

        out=new ObjectOutputStream(new FileOutputStream(new File(address)));



    }

    /**
     * close
     * @throws IOException not find
     */
    public void closeFile() throws IOException {
        out.close();

    }

    /**
     * write to file
     * @param obj object
     * @throws IOException not find file
     */
    public void writeObjectToFile(Object obj) throws IOException {
        try {
            out.writeObject(obj);
        }catch (Exception e){

        }

    }
}
