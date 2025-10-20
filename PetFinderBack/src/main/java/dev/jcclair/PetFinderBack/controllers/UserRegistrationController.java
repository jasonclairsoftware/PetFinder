package dev.jcclair.PetFinderBack.controllers;

import dev.jcclair.PetFinderBack.models.UserViewModel;
import dev.jcclair.PetFinderBack.services.UserRegistrationService;
import org.springframework.http.HttpStatus;
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
public class UserRegistrationController {

    //-------------------------------------------------------------------
    // START OF PROPERTIES
    //-------------------------------------------------------------------

    private final UserRegistrationService userRegistrationService;

    //-------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * Overloaded CTOR used for Spring to autowire dependent properties.
     * @param userRegistrationService - Service used to process user registrations
     */
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    //-------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //-------------------------------------------------------------------

    /**
     * The ResponseEntity will receive user registration requests and respond with acceptance codes.
     * @param user - The Users view model that will be validated for a new user.
     * @return - Will respond to registration request with success or failure codes
     */
    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserViewModel user) {
        // Method scope properties
        int creationCode = 0;

        System.out.println(user); // Use logger later

        // TODO:: Request User Creation
        creationCode = userRegistrationService.createUserRequest(user);

        switch(creationCode) {
            case -1:
                return new ResponseEntity<>("No email address provided", HttpStatus.BAD_REQUEST);
            case -2:
                return new ResponseEntity<>("Invalid email formatting", HttpStatus.BAD_REQUEST);
            case -3:
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            case -4:
                return new ResponseEntity<>("Bad password criteria", HttpStatus.BAD_REQUEST);
        }

        // All negative cases passed. Registration complete
        return ResponseEntity.ok("User Registration Successful");
    }

    //-------------------------------------------------------------------
    // END OF PROPERTIES
    //-------------------------------------------------------------------

}
