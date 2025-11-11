package dev.jcclair.PetFinderTest.models;

import jakarta.persistence.Column;

public class PetModel {
    private long id;
    private long ownerId;
    private String name;
    private String breed;
    private String imageUrl;

    public PetModel() {
    }

    public PetModel(long id, long ownerId, String name, String breed, String imageUrl) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.breed = breed;
        this.imageUrl = imageUrl;
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
}
