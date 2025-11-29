package dev.jcclair.PetFinderTest.controllers;

import dev.jcclair.PetFinderTest.models.PetModel;
import dev.jcclair.PetFinderTest.models.UserModel;
import dev.jcclair.PetFinderTest.services.PetService;
import dev.jcclair.PetFinderTest.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetApiController {

    private final PetService petService;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    public PetApiController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewPet(@RequestBody PetModel pet) {

        UserModel owner;

        if(pet == null) {
            return new ResponseEntity<>("Error: Body is null", HttpStatus.BAD_REQUEST);
        }

        owner = this.userService.findUserById(pet.getOwnerId());
        if(owner == null) {
            return new ResponseEntity<>("Error: Owner ID does not exist", HttpStatus.BAD_REQUEST);
        }

        if(pet.getName().isBlank() || pet.getBreed().isBlank()){
            return new ResponseEntity<>("Error: There must be a name and breed", HttpStatus.BAD_REQUEST);
        }

        pet = this.petService.registerPet(pet);

        return ResponseEntity.ok(pet);
    }

    @GetMapping
    public ResponseEntity<?> getPetsByUserId(@RequestParam("ownerId") String id) {
        List<PetModel> pets = this.petService.getPetsById(Integer.parseInt(id));
        return ResponseEntity.ok(pets);
    }

    // TODO:: Get all pets


}
