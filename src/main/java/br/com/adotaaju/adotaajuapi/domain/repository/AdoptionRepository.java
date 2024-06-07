package br.com.adotaaju.adotaajuapi.domain.repository;

import br.com.adotaaju.adotaajuapi.domain.entity.Adoption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Long> {
    List<Adoption> findByTutorId(Long tutorId);
    List<Adoption> findByPetId(Long petId);
}
