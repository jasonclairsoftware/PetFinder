package dev.jcclair.PetFinderTest.controllers;

import dev.jcclair.PetFinderTest.models.UserModel;
import dev.jcclair.PetFinderTest.services.JwtService;
import dev.jcclair.PetFinderTest.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest Controller to handle users
 *
 * @author Jason Clair
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserApiController {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    @Autowired
    private JwtService jwtService;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    /**
     * Constructor for the userController to inject spring beans
     * @param userService - Injected user service to handle services
     */
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------

    /**
     * Will attempt to register a new user
     * @param user - Object to be saved
     * @return - The saved user
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
        // Validate if there is an email
        if (user == null) {
            log.warn("Attempted user registration failed. User is null");
            return new ResponseEntity<>("There must be a user passed.", HttpStatus.BAD_REQUEST);
        }
        // Validate email resistance
        if (user.getEmail().isBlank()) {
            log.warn("Attempted user registration failed. Email is blank");
            return new ResponseEntity<>("No email", HttpStatus.BAD_REQUEST);
        }
        // Validate if email is valid
        if (!this.userService.validateEmail(user.getEmail())) {
            log.warn("Attempted user registration failed. Incorrect email format" + user.getEmail());
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }
        // Validate if email existences
        if (this.userService.findUserByEmail(user.getEmail()) != null) {
            log.warn("Attempted user registration failed. User email already exsists");
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        // Validate password existence
        if (user.getPassword().isBlank()) {
            log.warn("Attempted user registration failed. Password field is blank");
            return new ResponseEntity<>("No password", HttpStatus.BAD_REQUEST);
        }
        // Validate strong password
        if (!this.userService.validatePassword(user.getPassword())) {
            log.warn("Attempted user registration failed. Password is weak" + user.getPassword());
            return new ResponseEntity<>("Invalid password strength", HttpStatus.BAD_REQUEST);
        }

        // Fields good, routing service request
        user = this.userService.registerUserRequest(user);

        // All negative cases passed. Registration complete
        if (user != null) {
            log.info("User registration success. " + user.getEmail() + " has been registered");
            return ResponseEntity.ok(user);
        }
        log.warn("Attempted user registration failed. Unknown Error");
        return new ResponseEntity<>("Unknown error registering", HttpStatus.INTERNAL_SERVER_ERROR);


    } // End of registerUser method

    /**
     * Will attempt to create a JWT Token
     * @param user - The object to be validated
     * @return - JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserModel user) {

        String jwt = userService.verify(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("message", "Login Successful");
        return ResponseEntity.ok(response);
    }

    /**
     * Will attempt to get a user by email
     * @param email - The email being compared
     * @return - User object
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        UserModel result = this.userService.findUserByEmail(email);
        result.setPassword(""); // Removing the password
        return ResponseEntity.ok(result);
    }

    /**
     * Will attempt to get user by ID
     * @param id - The ID of the user
     * @return - User Object
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserModel result = this.userService.findUserById(Integer.parseInt(id));
        return ResponseEntity.ok(result);
    }

    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------
}
