package dev.jcclair.PetFinderBack.models;

/**
 * This is the main model for how information will be kept in the database excluding the password
 *
 * @author Jason Clair
 */
public class UserModel {

    //-------------------------------------------------------------------
    // START OF PROPERTIES
    //-------------------------------------------------------------------
    private long id;
    private String email;
    private String hashedPassword;
    private String salt;

    //-------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * Default CTOR
     */
    public UserModel() {
        this.id = -1;
        this.email = "";
        this.hashedPassword = "";
        this.salt = "";
    }

    /**
     * Overlaoded CTOR
     * @param id - PK for the user
     * @param email - Unique email for the user
     * @param hashedPassword - Hashed Password
     * @param salt - Salt for password.
     */
    public UserModel(long id, String email, String hashedPassword, String salt) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    //-------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF GETTERS AND SETTERS
    //-------------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    //-------------------------------------------------------------------
    // END OF GETTERS AND SETTERS
    //-------------------------------------------------------------------


}
