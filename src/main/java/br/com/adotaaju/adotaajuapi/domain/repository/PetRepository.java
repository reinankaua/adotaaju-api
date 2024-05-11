package br.com.adotaaju.adotaajuapi.domain.repository;

import java.util.Optional;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    Page<Pet> findAll(Specification<Pet> spec, Pageable pageable);
    Optional<Pet> findById(long id);
    Page<Pet> findByBreed(String breed, Pageable pageable);
    void deleteById(long id);
    boolean existsById(long id);
    boolean existsByBreed(String breed);

}
