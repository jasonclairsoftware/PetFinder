package dev.jcclair.PetFinderTest.daos;

import dev.jcclair.PetFinderTest.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
