package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.domain.repository.PetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetDTO save(PetDTO petDTO) {
        var pet = PetDTO.toPet(petDTO);
        var petSaved = petRepository.save(pet);
        return PetDTO.toPetDTO(petSaved);
    }

//    public Page<Pet> findAll(int page, int itens) {
//        return petRepository.findAll(PageRequest.of(page, itens));
//    }

    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);
    }

    public Page<Pet> findByBreed(String breed, int page, int itens) {
        return petRepository.findByBreed(breed, PageRequest.of(page, itens));
    }

    public PetDTO update(Long id, PetDTO petDTO) {

        var existingPetOptional = petRepository.findById(id);

        if (existingPetOptional.isEmpty()) {
            return null;
        }

        var existingPet = existingPetOptional.get();

        existingPet.setType(petDTO.type() != null ? petDTO.type() : existingPet.getType());
        existingPet.setBreed(petDTO.breed() != null ? petDTO.breed() : existingPet.getBreed());
        existingPet.setAge(petDTO.age() != null ? petDTO.age() : existingPet.getAge());
        existingPet.setColor(petDTO.color() != null ? petDTO.color() : existingPet.getColor());
        existingPet.setWeight(petDTO.weight() != null ? petDTO.weight() : existingPet.getWeight());
        existingPet.setFlAdopted(petDTO.flAdopted() != null ? petDTO.flAdopted() : existingPet.getFlAdopted());

        var petSaved = petRepository.save(existingPet);

        return PetDTO.toPetDTO(petSaved);
    }

    public void deleteById(long id) {
        petRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return petRepository.existsById(id);
    }

    public boolean existsByBreed(String breed) {

        return petRepository.existsByBreed(breed);
    }

    public Page<Pet> searchByCriteria(PetDTO petDTO, Pageable pageable) {
        Specification<Pet> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(petDTO.id())) {
                predicates.add(cb.equal(root.get("id"), petDTO.id()));
            }
            if (Objects.nonNull(petDTO.type())) {
                predicates.add(cb.equal(root.get("type"), petDTO.type()));
            }
            if (Objects.nonNull(petDTO.breed())) {
                predicates.add(cb.like(root.get("breed"), petDTO.breed() + "%"));
            }
            if (Objects.nonNull(petDTO.age())) {
                predicates.add(cb.equal(root.get("age"), petDTO.age()));
            }
            if (Objects.nonNull(petDTO.color())) {
                predicates.add(cb.equal(root.get("color"), petDTO.color()));
            }
            if (Objects.nonNull(petDTO.weight())) {
                predicates.add(cb.equal(root.get("weight"), petDTO.weight()));
            }
            if (Objects.nonNull(petDTO.flAdopted())) {
                predicates.add(cb.equal(root.get("flAdopted"), petDTO.flAdopted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return petRepository.findAll(spec, pageable);
    }
}