package GUIControllers;

import Application.Inventory;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {


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



