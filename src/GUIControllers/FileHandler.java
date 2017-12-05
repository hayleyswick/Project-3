package GUIControllers;

import java.io.*;

/**
 * Handles most of the file i/o for the entire program
 *
 * @author Liam Caudill
 */

public class FileHandler {

    /**     *
     * Writes the current values of the datamembers stored
     * in the arrays of Main to their appropriate files when
     * this method is called
     */

    public void writeFiles() {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = new FileOutputStream("src/Files/DBinventory.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(Main.mainDB.getDB());
            fout = new FileOutputStream("src/Files/fleet.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(Main.mainDB.getFleet());
            fout = new FileOutputStream("src/Files/users.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(Main.userList);


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Writes a serializable file for any given object that implements serializable.
     *
     * @param filename The filename to be written to.
     * @param o Object to be serialized and inserted to a file.
     */

    public void writeToFile(String filename, Object o) {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = new FileOutputStream(("src/Files/" + filename + ".ser"));
            oos = new ObjectOutputStream(fout);
            oos.writeObject(o);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * Reads in an object from a serializable file.
     * @param filename Filename that the object is being read in from.
     * @return Object read in from file.
     */

    public Object readFromFile(String filename) {

        ObjectInputStream objectinputstream = null;

        try {
            FileInputStream streamIn = new FileInputStream("src/Files/" + filename + ".ser");
            objectinputstream = new ObjectInputStream(streamIn);
            Object incomingObject = objectinputstream.readObject();
            return incomingObject;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }
}



