package br.com.adotaaju.adotaajuapi.domain.pet;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    Page<Pet> findAll(Pageable pageable);

    Optional<Pet> findById(long id);

    // Page<Pet> findAllByType(PetType type, Pageable pageable);
    Page<Pet> findAllByBreed(String breed, Pageable pageable);

    void deleteById(long id);

    boolean existsById(long id);
}
