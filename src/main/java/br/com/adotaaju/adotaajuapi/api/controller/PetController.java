package br.com.adotaaju.adotaajuapi.api.controller;

import java.util.Optional;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/animal")
public class PetController {

   @Autowired
   private PetService petService;

   @Operation(summary = "Register pet", description = "Register a new pet")
   @PostMapping(value = "/register")
   public ResponseEntity<PetDTO.Response> create(@Valid @RequestBody PetDTO.Request petDTO) {
      PetDTO.Response responseDTO = petService.save(petDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
   }

   @PutMapping("/update/")
    public ResponseEntity<PetDTO.Response> update(
            @RequestParam Long id,
            @RequestBody PetDTO.Request petDTO) throws Exception {

      PetDTO.Response responseDTO = petService.update(id, petDTO);
      if (responseDTO == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

   @DeleteMapping("delete/")
   public ResponseEntity<Void> deleteById(@RequestParam Long id) {
      if (!petService.existsById(id)) {
         return ResponseEntity.notFound().build();
      }
      petService.deleteById(id);
      return ResponseEntity.noContent().build();
   }

   @GetMapping("/findGeneric")
   public ResponseEntity<Page<PetDTO.Response>> searchByCriteria(@Valid PetDTO.Response petDTO, Pageable pageable) {
      Page<PetDTO.Response> result = petService.searchByCriteria(petDTO, pageable);
      if (result.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(result);
   }

}
