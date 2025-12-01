package dev.jcclair.PetFinderTest.daos;

import dev.jcclair.PetFinderTest.models.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for the Pets Entity
 *
 * @author Jason Clair
 */
@Repository
public interface PetDao extends JpaRepository<PetEntity, Long> {

    /**
     * Will get a Pet by the users ID
     * @param id - The owners ID
     * @return - Collection of pets
     */
    List<PetEntity> findByUserEntity_Id(long id);

    /**
     * Will Get all pets;
     * @return - Collection of pets in persistence
     */
    @Query("SELECT p FROM PetEntity p JOIN FETCH p.userEntity")
    List<PetEntity> findAllWithUser();

}
