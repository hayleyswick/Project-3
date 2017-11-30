package Users;

import Application.SalesVanWarehouse;

public class SalesAssociate extends User {
    static final long serialVersionUID = 8;


    public SalesAssociate(String username, String password, String email,
                          String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public SalesAssociate(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());
    }


}
