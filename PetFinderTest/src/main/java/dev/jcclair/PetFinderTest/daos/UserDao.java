package dev.jcclair.PetFinderTest.daos;

import dev.jcclair.PetFinderTest.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for the User Entity
 *
 * @author Jason Clair
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    /**
     * Will get all users with given email
     * @param email - The email being compared
     * @return - User Entity object
     */
    UserEntity findByEmail(String email);
}
