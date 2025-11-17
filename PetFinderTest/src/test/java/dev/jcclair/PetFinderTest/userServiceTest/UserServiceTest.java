package dev.jcclair.PetFinderTest.userServiceTest;

import dev.jcclair.PetFinderTest.daos.UserDao;
import dev.jcclair.PetFinderTest.models.UserEntity;
import dev.jcclair.PetFinderTest.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Testable
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void validateEmailTest() {
        // 1. One or more characters (letters, digits, or common symbols like ._%+-).
        // 2. An '@' symbol.
        // 3. A domain name (letters, digits, or hyphen).
        // 4. A dot (.).
        // 5. A top-level domain (2 to 6 letters).
        assertEquals(true, this.userService.validateEmail("test@test.dev"), "Validate Email failed: test@test.dev should pass");
        assertEquals(false, this.userService.validateEmail("test@test."), "Test@test. should have a top level domain");
        assertEquals(false, this.userService.validateEmail("test.dev"), "test.dev should have @ symbol");

    }

    @Test
    public void validatePasswordTest() {
        assertEquals(true, this.userService.validatePassword("Passw0rd!"), "Password not strong");
        assertEquals(false, this.userService.validatePassword("Pass!@#"));
    }

    @Test
    public void findUserByEmailTest() {
        // GIVEN
        when(userDao.findByEmail("test@mail.com")).thenReturn(new UserEntity(1L, "test@mail.com", "hashed"));

        assertNotNull(this.userService.findUserByEmail("test@mail.com"), "email should be there");
        assertNull(this.userService.findUserByEmail("test@nothere.dev"));
    }


}
