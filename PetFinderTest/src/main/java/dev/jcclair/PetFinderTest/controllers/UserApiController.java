package dev.jcclair.PetFinderTest.controllers;

import dev.jcclair.PetFinderTest.models.UserModel;
import dev.jcclair.PetFinderTest.services.JwtService;
import dev.jcclair.PetFinderTest.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserApiController {

    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    @Autowired
    private JwtService jwtService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserModel user) {

        String jwt = userService.verify(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("message", "Login Successful");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        UserModel result = this.userService.findUserByEmail(email);
        result.setPassword(""); // Removing the password
        return ResponseEntity.ok(result);
    }


}
