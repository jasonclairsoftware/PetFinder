package dev.jcclair.PetFinderBack.controllers;

import dev.jcclair.PetFinderBack.models.UserViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the main controller for handling CRUD requests for registering a new User
 *
 * @author Jason Clair
 */
@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
public class RegisterController {

    //-------------------------------------------------------------------
    // START OF METHODS
    //-------------------------------------------------------------------

    /**
     * The ResponseEntity will receive user registration requests and respond with acceptance codes.
     * @param user - The Users view model that will be validated for a new user.
     * @return - Will respond to registration request with success or failure codes
     */
    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserViewModel user) {
        System.out.println(user);
        //return ResponseEntity.ok("Registration success."); // Sample

        // TODO:: Process request properly.
        return ResponseEntity.noContent().build();
    }

    //-------------------------------------------------------------------
    // END OF PROPERTIES
    //-------------------------------------------------------------------

}
