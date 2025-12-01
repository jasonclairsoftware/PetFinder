package dev.jcclair.PetFinderTest.models;

/**
 * Pet View Model
 *
 * @author Jason Clair
 */
public class PetModel {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    private long id;
    private long ownerId;
    private String name;
    private String breed;
    private String imageUrl;
    private String location;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    public PetModel() {
    }

    /**
     * Overloaded constructor
     * @param id - Id of pet
     * @param ownerId - ID of the owner
     * @param name - Name of pet
     * @param breed - Breed of pet
     * @param imageUrl - Image location of pet
     * @param location - Location of pet
     */
    public PetModel(long id, long ownerId, String name, String breed, String imageUrl, String location) {
        this.id = id;
        this.ownerId = ownerId;
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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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

    @Override
    public String toString() {
        return "PetModel{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    //------------------------------------------------------------------------------------
    // END METHODS
    //------------------------------------------------------------------------------------
}
