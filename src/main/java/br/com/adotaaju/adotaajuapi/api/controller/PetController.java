package br.com.adotaaju.adotaajuapi.api.controller;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
@RequestMapping(value = "/pet")
public class PetController {

   @Autowired
   private PetService petService;

   @Operation(summary = "Register a new pet", description = "Creates a new pet in the system with the information provided")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "201", description = "A pet has been successfully registered in the database"),
         @ApiResponse(responseCode = "400", description = "Invalid information provided")
   })
   @PostMapping(value = "/register")
   public ResponseEntity<PetDTO.Response> create(@Valid @RequestBody PetDTO.Request petDTO) {
      PetDTO.Response responseDTO = petService.save(petDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
   }

   @Operation(summary = "Search for pets using filter", description = "Search for pets according to the filter passed")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "The search was successful"),
         @ApiResponse(responseCode = "204", description = "There are no registered pets in database")
   })
   @GetMapping("/findGeneric")
   public ResponseEntity<Page<PetDTO.Response>> searchByCriteria(@Valid PetDTO.Response petDTO, Pageable pageable) {
      Page<PetDTO.Response> result = petService.searchByCriteria(petDTO, pageable);
      if (result.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(result);
   }

   @Operation(summary = "Update a pet", description = "Updates a pet in the system with the information provided")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Pet updated successfully"),
         @ApiResponse(responseCode = "404", description = "Couldn't find this pet to update")
   })
   @PutMapping("/update")
   public ResponseEntity<PetDTO.Response> update(
         @RequestParam Long id,
         @RequestBody PetDTO.Request petDTO) throws Exception {

      PetDTO.Response responseDTO = petService.update(id, petDTO);
      if (responseDTO == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(responseDTO);
   }

   @Operation(summary = "Delete a pet", description = "Deletes a pet in the system with the ID provided")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Pet deleted successfully"),
         @ApiResponse(responseCode = "404", description = "Couldn't find this pet to delete")
   })
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteById(@PathVariable Long id) {
   @DeleteMapping("delete/")
   public ResponseEntity<Void> deleteById(@RequestParam Long id) {
      if (!petService.existsById(id)) {
         return ResponseEntity.notFound().build();
      }
      petService.deleteById(id);
      return ResponseEntity.ok("Animal de estimação apagado com sucesso!");
   }

}
