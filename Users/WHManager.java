package Users;

public class WHManager extends User {

    public WHManager(String username, String password, String email,
                     String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public WHManager(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());

    }
}
