package dev.jcclair.PetFinderTest.models;

/**
 * User View Model
 *
 * @author Jason Clair
 */
public class UserModel {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------
    private long id;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String phone;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    public UserModel() {
    }

    /**
     * Overloaded Constructor
     * @param fName - User first name
     * @param lName - Last name
     * @param email - email
     * @param password - MVC password
     * @param phone - phone number
     */
    public UserModel(String fName, String lName, String email, String password, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    /**
     * Overlaoded constructor
     * @param id - Users ID
     * @param fName - First Name
     * @param lName - Last name
     * @param email - email
     * @param password - MVC password
     * @param phone - phone number
     */
    public UserModel(long id, String fName, String lName, String email, String password, String phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------
}
