package br.com.adotaaju.adotaajuapi.domain.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Page<Pet> findAll(int page, int itens) {
        return petRepository.findAll(PageRequest.of(page, itens));
    }

    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);
    }

    public Page<Pet> findAllByBreed(String breed, int page, int itens) {
        return petRepository.findAllByBreed(breed, PageRequest.of(page, itens));
    }

    // public Page<Pet> findAllByType(PetType type, int page, int itens){
    // return petRepository.findAllByType(type, PageRequest.of(page, itens));
    // }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return petRepository.existsById(id);
    }
}
