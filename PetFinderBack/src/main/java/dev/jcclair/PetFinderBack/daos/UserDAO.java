package dev.jcclair.PetFinderBack.daos;

import dev.jcclair.PetFinderBack.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is data access for all user information
 *
 * @author Jason Clair
 */
@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
