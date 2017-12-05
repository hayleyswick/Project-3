package Users;

import Application.SalesVanWarehouse;
import GUIControllers.Main;

/**
 * A type of user that handles the creation and deletion of other users.
 *
 * @author Liam Caudill
 */
public class SysAdmin extends User {
    static final long serialVersionUID = 7;


    public SysAdmin(String username, String password, String email,
                    String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public SysAdmin(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());

    }


    /**
     * Checks if a user exists or not.
     *
     * @param user User that is being searched for.
     * @return True if the user exists, false if it does not.
     */
    public boolean checkUser(User user) {
        for (User u : Main.userList) {
            if (user.getUsername().equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user exists or not.
     *
     * @param user User that is being searched for.
     * @return Returns the user from the Main userlist if it does exist, and null if it doesn't.
     */
    public User findUser(User user) {
        for (User u : Main.userList) {
            if (user.getUsername().equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Creates a new Office Manager or upgrades a default user to Office Manager.
     *
     * @param user User to be turned into an Office Manager.
     */
    public void addOfficeManager(User user) {

        boolean replaced = false;
        OfficeManager temp = new OfficeManager(user);
        for (User u : Main.userList) {
            if (temp.getUsername().equals(u.getUsername())) {
                u = temp;
                replaced = true;
            }
        }
        if (!replaced) {
            Main.userList.add(temp);
        }

    }

    /**
     * Creates a new Warehouse Manager or upgrades a default user to Warehouse Manager.
     *
     * @param user User to be turned into a Warehouse Manager.
     */
    public void addWHManager(User user) {

        boolean replaced = false;
        //check if username exist, if it does, replace at the index
        WHManager temp = new WHManager(user);
        for (User u : Main.userList) {
            if (temp.getUsername().equals(u.getUsername())) {
                u = temp;
                replaced = true;
            }
        }
        if (!replaced) {
            Main.userList.add(temp);
        }
    }

    /**
     * Creates a new System Admin or upgrades a default user to System Admin .
     *
     * @param user User to be turned into a System Admin.
     */
    public void addSysAdmin(User user) {
        boolean replaced = false;
        //check if username exist, if it does, replace at the index
        SysAdmin temp = new SysAdmin(user);
        for (User u : Main.userList) {
            if (temp.getUsername().equals(u.getUsername())) {
                u = temp;
                replaced = true;
            }
        }
        if (!replaced) {
            Main.userList.add(temp);
        }
    }

    /**
     * Creates a new Sales Associate or upgrades a default user to a Sales Associate.
     *
     * @param user User to be turned into a Sales Associate.
     * @param s    Van to be assigned to the new Sales Associate.
     */
    public void addSalesAssociate(User user, SalesVanWarehouse s) {
        boolean replaced = false;
        //check if username exist, if it does, replace at the index
        SalesAssociate temp = new SalesAssociate(user, s);
        for (User u : Main.userList) {
            if (temp.getUsername().equals(u.getUsername())) {
                u = temp;
                replaced = true;
            }
        }
        if (!replaced) {
            Main.userList.add(temp);
        }
    }
}
