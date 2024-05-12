package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.domain.repository.PetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetDTO.Response save(PetDTO.Request petDTO) {
        var pet = mapToEntity(petDTO);
        var savedPet = petRepository.save(pet);
        return mapToResponse(savedPet);
    }

    public PetDTO.Response update(Long id, PetDTO.Request petDTO) throws Exception {

        var existingPetOptional = petRepository.findById(id);

        if (existingPetOptional.isEmpty()) {
            throw new Exception("Id não encontrado na base de dados.");
        }

        var existingPet = existingPetOptional.get();

        mapToEntity(petDTO, existingPet);
        var updatedPet = petRepository.save(existingPet);
        return mapToResponse(updatedPet);
    }

    public void deleteById(long id) {
        petRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return petRepository.existsById(id);
    }

    public Page<PetDTO.Response> searchByCriteria(PetDTO.Response petDTO, Pageable pageable) {
        Specification<Pet> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(petDTO.getId())) {
                predicates.add(cb.equal(root.get("id"), petDTO.getId()));
            }
            if (Objects.nonNull(petDTO.getType())) {
                predicates.add(cb.equal(root.get("type"), petDTO.getType()));
            }
            if (Objects.nonNull(petDTO.getBreed())) {
                predicates.add(cb.like(root.get("breed"), petDTO.getBreed() + "%"));
            }
            if (Objects.nonNull(petDTO.getAge())) {
                predicates.add(cb.equal(root.get("age"), petDTO.getAge()));
            }
            if (Objects.nonNull(petDTO.getColor())) {
                predicates.add(cb.equal(root.get("color"), petDTO.getColor()));
            }
            if (Objects.nonNull(petDTO.getWeight())) {
                predicates.add(cb.equal(root.get("weight"), petDTO.getWeight()));
            }
            if (Objects.nonNull(petDTO.getFlAdopted())) {
                predicates.add(cb.equal(root.get("flAdopted"), petDTO.getFlAdopted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Page<Pet> pets = petRepository.findAll(spec, pageable);
        return pets.map(this::mapToResponse);
    }

    private Pet mapToEntity(PetDTO.Request petDTO) {
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setBreed(petDTO.getBreed());
        pet.setAge(petDTO.getAge());
        pet.setColor(petDTO.getColor());
        pet.setWeight(petDTO.getWeight());
        pet.setFlAdopted(petDTO.getFlAdopted());
        return pet;
    }

    private void mapToEntity(PetDTO.Request petDTO, Pet pet) {
        if (petDTO.getType() != null) {
            pet.setType(petDTO.getType());
        }
        if (petDTO.getBreed() != null) {
            pet.setBreed(petDTO.getBreed());
        }
        if (petDTO.getAge() != null) {
            pet.setAge(petDTO.getAge());
        }
        if (petDTO.getColor() != null) {
            pet.setColor(petDTO.getColor());
        }
        if (petDTO.getWeight() != null) {
            pet.setWeight(petDTO.getWeight());
        }
        if (petDTO.getFlAdopted() != null) {
            pet.setFlAdopted(petDTO.getFlAdopted());
        }
    }

    private PetDTO.Response mapToResponse(Pet pet) {
        PetDTO.Response responseDTO = new PetDTO.Response();
        responseDTO.setId(pet.getId());
        responseDTO.setType(pet.getType());
        responseDTO.setBreed(pet.getBreed());
        responseDTO.setAge(pet.getAge());
        responseDTO.setColor(pet.getColor());
        responseDTO.setWeight(pet.getWeight());
        responseDTO.setFlAdopted(pet.getFlAdopted());
        return responseDTO;
    }
}