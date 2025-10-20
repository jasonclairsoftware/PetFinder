package dev.jcclair.PetFinderBack.services;

import dev.jcclair.PetFinderBack.daos.UserDAO;
import dev.jcclair.PetFinderBack.models.UserModel;
import dev.jcclair.PetFinderBack.models.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the service class for registering a new user account to persistence.
 *
 * @author Jason Clair
 */
@Service
public class UserRegistrationService {

    @Autowired
    private UserDAO userDAO;

    //-------------------------------------------------------------------
    // START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * No args CTOR. Used for Spring annotations
     */
    public UserRegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    //-------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //-------------------------------------------------------------------

    // TODO:: 1) Email Validation
    // TODO:: 2) Password Validation
    // TODO:: 3) Generate Salt
    // TODO:: 4) Hash Password with Salt
    // TODO:: 5) Store User

    // 1) Email Validation

    /**
     * Will validate if the password: String contains the correct formatting. The email must not exist in persistence as well.
     * @param email - The email the user wishes to register under
     * @return - Will return success code 1 if and only if the password meets all criteria. Will return -1 if the email parameter is null. Will return -2 if email does not meet formatting conditions. Will return -3 if the email already exists in persistence.
     */
    private int validateEmail(String email) {
        // This pattern checks for:
        // 1. One or more characters (letters, digits, or common symbols like ._%+-).
        // 2. An '@' symbol.
        // 3. A domain name (letters, digits, or hyphen).
        // 4. A dot (.).
        // 5. A top-level domain (2 to 6 letters).
        final String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Validating if email has been instantiated
        if(email == null) return -1;

        // Will return 1 if the email meets regex requirements
        boolean hasMatched = matcher.matches();

        // Will return error code -2 for not meeting required email format
        if(!hasMatched) return -2;

        // Validating if the email already exists
        if(this.userDAO.findByEmail(email).isPresent()) return -3;

        // All checks passed. Return all good
        return 1;
    }

    /**
     * This is the main handler for registering a new user. Will return 1 if and only if the registration was successful.
     * @param user - The user model that needs to be registered.
     * @return - Status code 1 inf and only if the registration was successful. -1 if the email was null. -2 if the email is not formatted correctly. -3 if the email already exists. -4 if the password does not meet formatting conditions.
     */
    public int createUserRequest(UserViewModel user) {
        // TODO:: 1) Validate Email
        // TODO:: 2) Validate Password
        // TODO:: 3) Hash Password
        // TODO:: 4) Store Registered User to Persistence
        
        // Local method properties
        UserModel userEntity;
        int statusCode = 0;
        BCryptPasswordEncoder encoder;
        
        // 1) Validate Email
        statusCode = this.validateEmail(user.getEmail());
        if(statusCode != 1) return statusCode; // Invalid email
        userEntity = new UserModel();
        userEntity.setEmail(user.getEmail());
        
        // 2) Validate Password
        statusCode = this.validatePassword(user.getPassword());
        if(statusCode != 1) return statusCode;

        // 3) Generating Hashed Password
        encoder = new BCryptPasswordEncoder();
        // Bcrypt includes the salt when hashing
        userEntity.setHashedPassword(encoder.encode(user.getPassword()));

        // 4) Storing Registered User
        this.userDAO.save(userEntity);
        userEntity = this.userDAO.findByEmail(userEntity.getEmail()).get();
        System.out.println(userEntity.toString());

        
        return 0; // TODO:: Remove this later
    }

    /**
     * Will verify if the password meets strength criteria.
     * @param password - The password being tested
     * @return - Will return 1 if and only if the password meets all security criteria. -4 otherwise
     */
    private int validatePassword(String password) {
        // Method scope properties
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

        // Checking if the password is valid with regex
        Matcher matcher = passwordPattern.matcher(password);

        // Returning result
        return matcher.matches() ? 1 : -4;
    }

    //-------------------------------------------------------------------
    // END OF METHODS
    //-------------------------------------------------------------------
}
