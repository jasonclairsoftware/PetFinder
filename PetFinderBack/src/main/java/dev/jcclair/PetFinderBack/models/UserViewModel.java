package dev.jcclair.PetFinderBack.models;

/**
 * The UserViewModel is the view for the MVC. This model will restrict sensitive information of the users account and will for provide a response code or object.
 */
public class UserViewModel {

    //-------------------------------------------------------------------
    // START OF PROPERTIES
    //-------------------------------------------------------------------

    private String email;
    private String password; // This will be plain text until salted and hashed to the UserModel class


    //-------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * Overloaded CTOR. Will directly assign email and password.
     * @param email - A unique email for the user account
     * @param password - A strong password for the user account
     */
    public UserViewModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Default CTOR. Will auto assign values to empty properties
     */
    public UserViewModel() {
        this.email = "";
        this.password = "";
    }

    //-------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF SETTERS AND SETTERS
    //-------------------------------------------------------------------

    /**
     * Will retrieve the UserViewModel email.
     * @return - UserViewModel email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Will set the UserViewModel email
     * @param email - The UserViewModel email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Will retrieve the UserViewModel password. This is unsalted and unhashed.
     * @return - UserViewModel password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Will set the UserViewModel password. It should be unsalted and unhashed.
     * @param password - The UserViewModel password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //-------------------------------------------------------------------
    // END OF GETTERS AND SETTERS - START OF METHODS
    //-------------------------------------------------------------------

    @Override
    public String toString() {
        return "UserViewModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //-------------------------------------------------------------------
    // END OF METHODS
    //-------------------------------------------------------------------
}
