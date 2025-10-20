package dev.jcclair.PetFinderBack.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * This is the main model for how information will be kept in the database excluding the password
 *
 * @author Jason Clair
 */
@Entity
public class UserModel {

    //-------------------------------------------------------------------
    // START OF PROPERTIES
    //-------------------------------------------------------------------
    @Id
    private long id;
    private String email;
    private String hashedPassword; // When hashed, the plain text password will no longer be maintained
    private String salt;

    //-------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * Default CTOR - Will initialize all to blank values.
     */
    public UserModel() {
        this.id = -1;
        this.email = "";
        this.hashedPassword = "";
        this.salt = "";
    }

    /**
     * Overloaded CTOR - Will directly assign values.
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

    /**
     * Will retrieve ID.
     * @return - The ID of the User Entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Well set the User Entity ID
     * @param id - Will set the ID for the User Entity
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Will retrieve the email
     * @return - The users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Will set the User Entity email
     * @param email - Will set the User Entity email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Will retrieve the hashed password for the User Entity
     * @return - The hashed password for the User Entity
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Will set the hashed password for the User Entity
     * @param hashedPassword - The hashed password to be set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Will retrieve the password salt for the User Entity
     * @return - The User Entity password salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Will set the password salt for the User Entity
     * @param salt - The password salt to be assigned
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    //-------------------------------------------------------------------
    // END OF GETTERS AND SETTERS - START OF METHODS
    //-------------------------------------------------------------------

    /**
     * Standard toString Override
     * @return - String describing the model
     */
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }


    //-------------------------------------------------------------------
    // END OF METHODS
    //-------------------------------------------------------------------


}
