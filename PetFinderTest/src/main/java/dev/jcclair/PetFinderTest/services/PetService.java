package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.daos.PetDao;
import dev.jcclair.PetFinderTest.daos.UserDao;
import dev.jcclair.PetFinderTest.models.PetEntity;
import dev.jcclair.PetFinderTest.models.PetModel;
import dev.jcclair.PetFinderTest.models.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class to handle Pets
 *
 * @author Jason Clair
 */
@Service
public class PetService {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    private PetDao petDao;
    private UserDao userDao;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Overloaded CTOR for Spring injections
     * @param petDao - Data access object for pets
     * @param userDao - Data access object for users
     */
    public PetService(PetDao petDao, UserDao userDao) {
        this.petDao = petDao;
        this.userDao = userDao;
    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------

    /**
     * Will attempt to register a new pet
     * @param pet - Pet object being saved
     * @return - Updated MVC Pet
     */
    public PetModel registerPet(PetModel pet) {
        PetEntity savedPet = this.petDao.save(this.petModelToPetEntity(pet));
        return this.petEntityToPetModel(savedPet);
    }

    /**
     * DTO method to convert from MVC to Entity
     * @param pet - Object being converted
     * @return - Entity object
     */
    private PetEntity petModelToPetEntity(PetModel pet) {
        // Get associated user
        Optional<UserEntity> user = this.userDao.findById(pet.getOwnerId());

        // See if user is there
        if(user.isEmpty()) return null;

        // Return the saved pet
        return new PetEntity(pet.getId(), user.get(), pet.getName(), pet.getBreed(), pet.getImageUrl(), pet.getLocation());
    }

    /**
     * DTO to convert from Entity to MVC
     * @param pet - Entity object
     * @return - MVC object
     */
    private PetModel petEntityToPetModel(PetEntity pet) {
        return new PetModel(pet.getId(), pet.getUserEntity().getId(), pet.getName(), pet.getBreed(), pet.getImageUrl(), pet.getLocation());
    }

    /**
     * Will get all pet based off user or owner ID
     * @param id - Owner ID
     * @return - Collection of pets
     */
    public List<PetModel> getPetsById(int id) {
        List<PetEntity> result = this.petDao.findByUserEntity_Id(id);
        List<PetModel> pets = new ArrayList<>();

        for(PetEntity pet: result) {
            pets.add(new PetModel(pet.getId(), pet.getUserEntity().getId(), pet.getName(), pet.getBreed(), pet.getImageUrl(), pet.getLocation()));
        }
        return pets;
    }

    /**
     * Will get all pets for the map
     * @return - Collection of Pets
     */
    public List<PetModel> getAllPets() {
        List<PetModel> pets = new ArrayList<>();
        List<PetEntity> result = this.petDao.findAllWithUser();

        for(PetEntity pet: result) {
            pets.add(new PetModel(pet.getId(), pet.getUserEntity().getId(), pet.getName(), pet.getBreed(), pet.getImageUrl(), pet.getLocation()));
        }

        return pets;
    }
    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------

}
