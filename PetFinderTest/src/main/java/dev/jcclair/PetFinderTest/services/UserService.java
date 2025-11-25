package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.daos.UserDao;
import dev.jcclair.PetFinderTest.models.UserEntity;
import dev.jcclair.PetFinderTest.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
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
        UserEntity result = this.userDao.findByEmail(email);

        return result != null ? this.userEntityToUserViewModel(result) : null;

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
        UserEntity userEntity;
        // Hashing password
        String hashed = this.passwordEncoder.encode(user.getPassword());
        userEntity = new UserEntity(0, user.getfName(), user.getlName(), user.getEmail(), hashed, user.getPhone());
        // Saving user
        userEntity = this.userDao.save(userEntity);
        if(userEntity.getId() == -1 || userEntity == null)
            return null;
        // Return result
        return this.userEntityToUserViewModel(userEntity);
    }


    private UserModel userEntityToUserViewModel(UserEntity user) {
        return user == null ? null : new UserModel(
                user.getId(),
                user.getfName(),
                user.getlName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone());
    }

    public UserModel findUserById(long ownerId) {
        Optional<UserEntity> user = this.userDao.findById(ownerId);

        return user.isPresent() ? this.userEntityToUserViewModel(user.get()) : null;
    }

    public String verify(UserModel user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(authentication.isAuthenticated()) {
            return this.jwtService.generateToken(user);
        }
        return "fail";

    }
}
