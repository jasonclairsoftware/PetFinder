package dev.jcclair.PetFinderTest.daos;

import dev.jcclair.PetFinderTest.models.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDao extends JpaRepository<PetEntity, Long> {
}
