package dev.jcclair.PetFinderBack.services;

import dev.jcclair.PetFinderBack.daos.UserDAO;
import dev.jcclair.PetFinderBack.models.UserEntity;
import dev.jcclair.PetFinderBack.models.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the service class for registering a new user account to persistence.
 *
 * @author Jason Clair
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    //-------------------------------------------------------------------
    // START OF CONSTRUCTORS
    //-------------------------------------------------------------------

    /**
     * No args CTOR. Used for Spring annotations
     */
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {

        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    //-------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //-------------------------------------------------------------------


    // 1) Email Validation

    /**
     * Will validate if the password: String contains the correct formatting. The email must not exist in persistence as well.
     * @param email - The email the user wishes to register under
     * @return - Will return true if and only if the email is not null and contains one character, contains an '@' symbol, contains a domain name, a dot '.', and a top-level domain
     */
    public boolean validateEmail(String email) {
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
        if(email == null) return false;

        // Will return true if the email meets regex requirements
        return matcher.matches();

    }

    /**
     * This is the main handler for registering a new user. Will return 1 if and only if the registration was successful.
     * @param user - The user model that needs to be registered.
     * @return -
     */
    public UserViewModel registerUserRequest(UserViewModel user) {
        // Create working object
        UserEntity userEntity = new UserEntity();

        // Assign email
        userEntity.setEmail(user.getEmail());

        // Hash password
        userEntity.setHashedPassword(passwordEncoder.encode(user.getPassword()));

        // Saving user to persistence
        UserEntity result = this.userDAO.save(userEntity);

        // Returning result
        return this.userEntityToUserViewModel(result);
    }

    public UserViewModel findUserByEmail(String email) {
        if(email.isBlank()) return null;
        Optional<UserEntity> result = this.userDAO.findByEmail(email);

        return result.isPresent() ? this.userEntityToUserViewModel(result.get()) : null;

    }

    private UserViewModel userEntityToUserViewModel(UserEntity user) {
        return user == null ? null : new UserViewModel(user.getEmail(), user.getHashedPassword());
    }

    /**
     * Will verify if the password meets strength criteria.
     * @param password - The password being tested
     * @return - Will return true if and only if the password is a strong password
     */
    public boolean validatePassword(String password) {
        // Method scope properties
        String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{8,}$";
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

        // Checking if the password is valid with regex
        Matcher matcher = passwordPattern.matcher(password);

        // Returning result
        return matcher.matches();
    }

    //-------------------------------------------------------------------
    // END OF METHODS
    //-------------------------------------------------------------------
}
