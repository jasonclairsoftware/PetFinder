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

/**
 * Main Rest controller for the application
 *
 * @author Jason Clair
 */
@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetApiController {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    private final PetService petService;
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Constructor to inject services for Spring
     * @param petService - Service to handle pets
     * @param userService - Service to handle users
     */
    public PetApiController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------


    /**
     * End point to register a new pet
     * @param pet - The object that we are saving to persistence
     * @return - The pet model that was saved with new ID
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerNewPet(@RequestBody PetModel pet) {

        UserModel owner;

        // Validating if pet is null
        if(pet == null) {
            return new ResponseEntity<>("Error: Body is null", HttpStatus.BAD_REQUEST);
        }

        // Seeing if there is an owner
        owner = this.userService.findUserById(pet.getOwnerId());
        if(owner == null) {
            return new ResponseEntity<>("Error: Owner ID does not exist", HttpStatus.BAD_REQUEST);
        }

        // Validating inputs
        if(pet.getName().isBlank() || pet.getBreed().isBlank()){
            return new ResponseEntity<>("Error: There must be a name and breed", HttpStatus.BAD_REQUEST);
        }

        // Registering the pet
        pet = this.petService.registerPet(pet);

        // Returning value
        return ResponseEntity.ok(pet);
    }

    /**
     * Returns all pets by given owner ID
     * @param id - ID of the pets owner
     * @return - Collection of all the pets
     */
    @GetMapping("/users")
    public ResponseEntity<?> getPetsByUserId(@RequestParam("ownerId") String id) {
        List<PetModel> pets = this.petService.getPetsById(Integer.parseInt(id));
        return ResponseEntity.ok(pets);
    }

    /**
     * Returns all pets for the map
     * @return - Collection of all the pets
     */
    @GetMapping()
    public ResponseEntity<?> getAllPets() {
        List<PetModel> pets = this.petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------


}
