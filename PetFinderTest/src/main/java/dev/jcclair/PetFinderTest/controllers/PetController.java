package dev.jcclair.PetFinderTest.controllers;

import dev.jcclair.PetFinderTest.models.PetModel;
import dev.jcclair.PetFinderTest.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Get Pet by id
    public ResponseEntity<?> getPetById(@RequestBody long id) {
        PetModel pet = this.petService.getPetById(id);
        if(pet == null) {
            // Logger
            return new ResponseEntity<>("Pet not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PetModel>(pet, HttpStatus.OK);
    }

    // Get Pets by user
    public ResponseEntity<?> getPetsByUserId(@RequestBody long id) {
        List<PetModel> pets = this.petService.getPetsByUserId(id);

        if(pets == null){
            // Logger
            return new ResponseEntity<>("User Id not valid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<PetModel>>(pets, HttpStatus.OK);

    }

    // Add Pet
    public ResponseEntity<?> addPet(@RequestBody PetModel pet) {
        pet = this.petService.addPet(pet);

        if(pet == null || pet.getId() == -1) {
            return new ResponseEntity<>("Add pet failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PetModel>(pet, HttpStatus.OK);
    }
}
