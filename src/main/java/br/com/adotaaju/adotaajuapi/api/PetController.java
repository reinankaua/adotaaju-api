package br.com.adotaaju.adotaajuapi.api;

import java.util.Optional;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.domain.pet.PetRequest;
import br.com.adotaaju.adotaajuapi.domain.pet.PetResponse;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/animal")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<PetResponse> create(@Valid @RequestBody PetRequest petRequest) {

        return petService.save(petRequest);

    }

    @GetMapping(value = "/buscarTodos")
    public ResponseEntity<Page<Pet>> findAll(@RequestParam int pagina, @RequestParam int itens) {

        return petService.findAll(pagina, itens);

    }

    @GetMapping(value = "/buscarPorId/{id}")
    public ResponseEntity<Optional<Pet>> findById(@PathVariable Long id) {

        return petService.findById(id);

    }

    @GetMapping(value = "/buscarPorRaca")
    public ResponseEntity<Page<Pet>> findByBreed(
            @RequestParam String raca,
            @RequestParam int pagina,
            @RequestParam int itens) {

        return petService.findByBreed(raca, pagina, itens);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PetResponse> update(
            @PathVariable Long id,
            @RequestBody PetRequest petRequest) {

        return petService.update(id, petRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        return petService.deleteById(id);

    }
}
