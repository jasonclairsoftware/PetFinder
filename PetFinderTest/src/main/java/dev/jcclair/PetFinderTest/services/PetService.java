package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.daos.PetDao;
import dev.jcclair.PetFinderTest.models.PetEntity;
import dev.jcclair.PetFinderTest.models.PetModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetDao petDao;

    List<PetEntity> pets = new ArrayList<>(Arrays.asList(new PetEntity(1, "fluffy"), new PetEntity(2, "Neko"), new PetEntity(3, "Bowser")));

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public PetModel getPetById(long id) {
        Optional<PetEntity> pet = this.petDao.findById(id);

        return pet.map(this::petEntityToPetModel).orElse(null);
    }

    public List<PetModel> getPetsByUserId(long id) {
        return null;
    }

    public PetModel addPet(PetModel pet) {
        PetEntity entry = new PetEntity(pet.getId(), pet.getName());
        entry = this.petDao.save(entry);
        return this.petEntityToPetModel(entry);
    }

    private PetModel petEntityToPetModel(PetEntity pet) {
        return new PetModel(pet.getId(), pet.getName());
    }
}
