package br.com.adotaaju.adotaajuapi.domain.pet;

import java.util.Optional;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "v1/animal")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet) {
        petService.save(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping
    public ResponseEntity<Page<Pet>> findAll(@RequestParam int pagina, @RequestParam int itens) {
        return ResponseEntity.ok(petService.findAll(pagina, itens));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Pet>> findById(@PathVariable Long id) {
        if (!petService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(petService.findById(id));
    }

    // @GetMapping(value = "/busca-tipo")
    // public ResponseEntity<Page<Pet>> findAllByType(@RequestParam("tipo") String
    // type, @RequestParam int pagina, @RequestParam int itens) {
    // return ResponseEntity.ok(petService.findAllByBreed(type, pagina, itens));
    // }

    @GetMapping(value = "/busca-raca")
    public ResponseEntity<Page<Pet>> findById(@RequestParam("raca") String breed, @RequestParam int pagina,
            @RequestParam int itens) {
        return ResponseEntity.ok(petService.findAllByBreed(breed, pagina, itens));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        if (!petService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pet.setId(id);
        return ResponseEntity.ok(petService.save(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!petService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
