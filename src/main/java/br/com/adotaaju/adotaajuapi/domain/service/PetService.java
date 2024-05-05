package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.domain.pet.PetMapper;
import br.com.adotaaju.adotaajuapi.domain.pet.PetRequest;
import br.com.adotaaju.adotaajuapi.domain.pet.PetResponse;
import br.com.adotaaju.adotaajuapi.domain.repository.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public ResponseEntity<PetResponse> save(PetRequest petRequest) {

        var pet = PetMapper.toPet(petRequest);
        var petSaved = petRepository.save(pet);
        var petResponse = PetMapper.toPetResponse(petSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponse);
        
    }

    public ResponseEntity<Page<Pet>> findAll(int page, int itens) {

        var petsResult = petRepository.findAll(PageRequest.of(page, itens));
        if (petsResult.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(petsResult);

    }

    public ResponseEntity<Optional<Pet>> findById(long id) {

        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(petRepository.findById(id));

    }

    public ResponseEntity<Page<Pet>> findByBreed(String breed, int page, int itens) {

        var petsResult = petRepository.findByBreed(breed, PageRequest.of(page, itens));
        if (petsResult.getContent().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(petsResult);
    }

    public ResponseEntity<PetResponse> update(Long id, PetRequest petRequest) {

        var existingPetOptional = petRepository.findById(id);
        if (existingPetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var existingPet = existingPetOptional.get();

        existingPet.setType(petRequest.getType() != null ? petRequest.getType() : existingPet.getType());
        existingPet.setBreed(petRequest.getBreed() != null ? petRequest.getBreed() : existingPet.getBreed());
        existingPet.setAge(petRequest.getAge() != null ? petRequest.getAge() : existingPet.getAge());
        existingPet.setColor(petRequest.getColor() != null ? petRequest.getColor() : existingPet.getColor());
        existingPet.setWeight(petRequest.getWeight() != null ? petRequest.getWeight() : existingPet.getWeight());
        existingPet.setIsAdopted(petRequest.getIsAdopted() != null ? petRequest.getIsAdopted() : existingPet.getIsAdopted());

        var petSaved = petRepository.save(existingPet);
        var petResponse = PetMapper.toPetResponse(petSaved);

        return ResponseEntity.status(HttpStatus.OK).body(petResponse);

    }

    public ResponseEntity<Void> deleteById(long id) {

        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        petRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    public boolean existsById(long id) {

        return petRepository.existsById(id);

    }

    public boolean existsByBreed(String breed) {

        return petRepository.existsByBreed(breed);

    }
}