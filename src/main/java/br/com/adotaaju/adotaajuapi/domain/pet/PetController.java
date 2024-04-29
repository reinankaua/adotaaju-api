package br.com.adotaaju.adotaajuapi.domain.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "v1/animal")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @PostMapping
    public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet) {
        petRepository.save(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }
}
