package dev.jcclair.PetFinderTest.daos;

import dev.jcclair.PetFinderTest.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
