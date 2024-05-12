package br.com.adotaaju.adotaajuapi.api.controller;

import java.util.Optional;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/animal")
public class PetController {

   @Autowired
   private PetService petService;

   @PostMapping(value = "/cadastrar")
   public ResponseEntity<PetDTO> create(@Valid @RequestBody PetDTO petDTO) {
      return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(petDTO));
   }

   @GetMapping(value = "/buscarTodos")
   public ResponseEntity<Page<Pet>> findAll(@RequestParam int pagina, @RequestParam int itens) {
      var petsResult = petService.findAll(pagina, itens);
      if (petsResult.getContent().isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(petsResult);
   }

   @GetMapping(value = "/buscarPorId/{id}")
   public ResponseEntity<Optional<Pet>> findById(@PathVariable Long id) {
      if (!petService.existsById(id)) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(petService.findById(id));
   }

   @GetMapping(value = "/buscarPorRaca")
   public ResponseEntity<Page<Pet>> findByBreed(
         @RequestParam String raca,
         @RequestParam int pagina,
         @RequestParam int itens) {

      var petsResult = petService.findByBreed(raca, pagina, itens);
      if (petsResult.getContent().isEmpty()) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(petsResult);
   }

   @PutMapping("/atualizar/{id}")
    public ResponseEntity<PetDTO> update(
            @PathVariable Long id,
            @RequestBody PetDTO petDTO) {
        
        var petChanged = petService.update(id, petDTO);

        if (petChanged == null) {
            return ResponseEntity.notFound().build();
        }
            
        return ResponseEntity.status(HttpStatus.OK).body(petChanged);
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
