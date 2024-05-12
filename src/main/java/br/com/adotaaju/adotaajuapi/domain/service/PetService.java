package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.api.controller.PetController;
import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.api.dto.PetMapper;
import br.com.adotaaju.adotaajuapi.domain.repository.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetDTO save(PetDTO petDTO) {
        var pet = PetMapper.toPet(petDTO);
        var petSaved = petRepository.save(pet);
        return PetMapper.toPetDTO(petSaved);
    }

    public Page<Pet> findAll(int page, int itens) {
        return petRepository.findAll(PageRequest.of(page, itens));
    }

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
        var petConvertedToDTO = PetMapper.toPetDTO(petSaved);

        return petConvertedToDTO;
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
}