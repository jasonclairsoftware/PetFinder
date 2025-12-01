package dev.jcclair.PetFinderTest.models;

import jakarta.persistence.*;

/**
 * Pet Entity for RDS
 */
@Entity
@Table(name = "pets")
public class PetEntity {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userEntity;
    private String name;
    private String breed;
    @Column(name = "imageUrl", nullable = true)
    private String imageUrl;
    private String location;


    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    public PetEntity() {
    }

    /**
     * Overloaded constructor
     * @param id - ID of Pet
     * @param userEntity - Joined User for owner
     * @param name - Name of pet
     * @param breed - Breed of pet
     * @param imageUrl - Location for pet Image
     * @param location - Location of pet
     */
    public PetEntity(long id, UserEntity userEntity, String name, String breed, String imageUrl, String location) {
        this.id = id;
        this.userEntity = userEntity;
        this.name = name;
        this.breed = breed;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------
}
