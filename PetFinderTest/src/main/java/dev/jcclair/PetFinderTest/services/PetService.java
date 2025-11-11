package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.daos.PetDao;
import dev.jcclair.PetFinderTest.daos.UserDao;
import dev.jcclair.PetFinderTest.models.PetEntity;
import dev.jcclair.PetFinderTest.models.PetModel;
import dev.jcclair.PetFinderTest.models.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PetService {

    private PetDao petDao;
    private UserDao userDao;

    public PetService(PetDao petDao, UserDao userDao) {
        this.petDao = petDao;
        this.userDao = userDao;
    }


    public PetModel registerPet(PetModel pet) {
        PetEntity savedPet = this.petDao.save(this.petModelToPetEntity(pet));
        return this.petEntityToPetModel(savedPet);
    }

    private PetEntity petModelToPetEntity(PetModel pet) {
        // Get associated user
        Optional<UserEntity> user = this.userDao.findById(pet.getOwnerId());

        // See if user is there
        if(user.isEmpty()) return null;

        // Return the saved pet
        return new PetEntity(pet.getId(), user.get(), pet.getName(), pet.getBreed(), pet.getImageUrl());
    }

    private PetModel petEntityToPetModel(PetEntity pet) {
        return new PetModel(pet.getId(), pet.getUserEntity().getId(), pet.getName(), pet.getBreed(), pet.getImageUrl());
    }
}
