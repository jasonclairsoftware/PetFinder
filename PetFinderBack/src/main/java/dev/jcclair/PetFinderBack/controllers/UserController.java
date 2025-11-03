package dev.jcclair.PetFinderBack.controllers;

import dev.jcclair.PetFinderBack.models.UserViewModel;
import dev.jcclair.PetFinderBack.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the main controller for handling CRUD requests for registering a new User
 *
 * @author Jason Clair
 */
@RestController
@RequestMapping("/users")
public class UserController {

    //-------------------------------------------------------------------
    // START OF PROPERTIES
    //-------------------------------------------------------------------

    private final UserService userRegistrationService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    //-------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * Overloaded CTOR used for Spring to autowire dependent properties.
     * @param userRegistrationService - Service used to process user registrations
     */
    public UserController(UserService userRegistrationService) {
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
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserViewModel user) {


        // Validate if there is an email
        if(user == null) {
            log.warn("Attempted user registration failed. User is null");
            return new ResponseEntity<>("There must be a user passed.", HttpStatus.BAD_REQUEST);
        }
        // Validate email resistance
        if(user.getEmail().isBlank()){
            log.warn("Attempted user registration failed. Email is blank");
            return new ResponseEntity<>("No email", HttpStatus.BAD_REQUEST);
        }
        // Validate if email is valid
        if(!this.userRegistrationService.validateEmail(user.getEmail())) {
            log.warn("Attempted user registration failed. Incorrect email format" + user.getEmail());
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }
        // Validate if email existences
        if(this.userRegistrationService.findUserByEmail(user.getEmail()) != null){
            log.warn("Attempted user registration failed. User email already exsists");
            return new ResponseEntity<>("Email already exsists", HttpStatus.BAD_REQUEST);
        }
        // Validate password existence
        if(user.getPassword().isBlank()) {
            log.warn("Attempted user registration failed. Password field is blank");
            return new ResponseEntity<>("No password", HttpStatus.BAD_REQUEST);
        }
        // Validate strong password
        if(!this.userRegistrationService.validatePassword(user.getPassword())) {
            log.warn("Attempted user registration failed. Password is weak" + user.getPassword());
            return new ResponseEntity<>("Invalid password strength", HttpStatus.BAD_REQUEST);
        }

        // Fields good, routing service request
        user = this.userRegistrationService.registerUserRequest(user);

        // All negative cases passed. Registration complete
        if(user != null) {
            log.info("User registration success. " + user.getEmail() + " has been registered");
            return ResponseEntity.ok("User Registration Successful");
        }
        else {
            log.warn("Attempted user registration failed. Unknown Error");
            return new ResponseEntity<>("Unknown error registering", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    } // End of registerUser method

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginForm";
    }



    //-------------------------------------------------------------------
    // END OF METHODS
    //-------------------------------------------------------------------

}
