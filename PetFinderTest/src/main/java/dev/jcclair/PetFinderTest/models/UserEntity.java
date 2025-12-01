package dev.jcclair.PetFinderTest.models;

import jakarta.persistence.*;

/**
 * User Entity for RDS
 *
 * @author Jason Clair
 */
@Entity
@Table(name = "users")
public class UserEntity {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fName;
    private String lName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    private String phone;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Default CTOR
     */
    public UserEntity() {
    }

    /**
     * Overlaoded CTOR
     * @param id - ID of User
     * @param fName - First name
     * @param lName - Last Name
     * @param email - Users Email
     * @param password - Hashed Password
     * @param phone - Phone number
     */
    public UserEntity(long id, String fName, String lName, String email, String password, String phone) {
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
    // END METHODS
    //------------------------------------------------------------------------------------
}
