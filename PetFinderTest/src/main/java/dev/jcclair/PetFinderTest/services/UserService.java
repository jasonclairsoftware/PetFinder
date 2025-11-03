package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.daos.UserDao;
import dev.jcclair.PetFinderTest.models.UserEntity;
import dev.jcclair.PetFinderTest.models.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


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

    public UserModel findUserByEmail(String email) {
        if(email.isBlank()) return null;
        Optional<UserEntity> result = this.userDao.findByEmail(email);

        return result.isPresent() ? this.userEntityToUserViewModel(result.get()) : null;

    }

    public boolean validatePassword(String password) {
        // Method scope properties
        String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{8,}$";
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

        // Checking if the password is valid with regex
        Matcher matcher = passwordPattern.matcher(password);

        // Returning result
        return matcher.matches();
    }

    public UserModel registerUserRequest(UserModel user) {
        // Create working object
        UserEntity userEntity = new UserEntity();
        // Assign email
        userEntity.setEmail(user.getEmail());
        // Hashing password
        userEntity.setPassword(this.passwordEncoder.encode(user.getPassword()));
        // Saving user
        userEntity = this.userDao.save(userEntity);
        if(userEntity.getId() == -1 || userEntity == null)
            return null;
        // Return result
        return this.userEntityToUserViewModel(userEntity);
    }

    public boolean autherizeUser(UserModel user) {
        UserModel selectedUser = this.findUserByEmail(user.getEmail());
        if(selectedUser == null) return false;
        return this.passwordEncoder.matches(user.getPassword(), selectedUser.getPassword());
    }

    private UserModel userEntityToUserViewModel(UserEntity user) {
        return user == null ? null : new UserModel(user.getEmail(), user.getPassword());
    }
}
