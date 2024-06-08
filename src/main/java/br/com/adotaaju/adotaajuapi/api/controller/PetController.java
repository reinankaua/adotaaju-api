package br.com.adotaaju.adotaajuapi.api.controller;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.api.dto.PetType;
import br.com.adotaaju.adotaajuapi.core.GenericResponse;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
      public ResponseEntity<GenericResponse<PetDTO.Response>> createPet(
            @RequestParam("type") PetType type,
            @RequestParam("breed") String breed,
            @RequestParam("age") Integer age,
            @RequestParam("color") String color,
            @RequestParam("weight") Float weight,
            @RequestParam("flAdopted") Boolean flAdopted,
            @RequestParam("image") MultipartFile image) throws IOException {


            String base64Image = Base64.getEncoder().encodeToString(image.getBytes());

            PetDTO.Request petDTO = new PetDTO.Request();
            petDTO.setType(type);
            petDTO.setBreed(breed);
            petDTO.setAge(age);
            petDTO.setColor(color);
            petDTO.setWeight(weight);
            petDTO.setFlAdopted(flAdopted);
            petDTO.setImageBase64(base64Image);

            PetDTO.Response responseDTO = petService.save(petDTO);

            GenericResponse<PetDTO.Response> successResponse = new GenericResponse<>(
                        HttpStatus.CREATED.value(),
                        "Operação realizada com sucesso.",
                        responseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
      }

      // @Operation(summary = "Register a new pet", description = "Creates a new pet in the system with the information provided")
      // @ApiResponses(value = {
      //             @ApiResponse(responseCode = "201", description = "A pet has been successfully registered in the database"),
      //             @ApiResponse(responseCode = "400", description = "Invalid information provided")
      // })
      // @PostMapping(value = "/register")
      // public ResponseEntity<GenericResponse<PetDTO.Response>> create(@Valid @RequestBody PetDTO.Request petDTO) {
      //       PetDTO.Response responseDTO = petService.save(petDTO);

      //       GenericResponse<PetDTO.Response> successResponse = new GenericResponse<>(
      //                   HttpStatus.CREATED.value(),
      //                   "Operação realizada com sucesso.",
      //                   responseDTO);

      //       return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
      // }

      @Operation(summary = "Search for pets using filter", description = "Search for pets according to the filter passed")
      @ApiResponses(value = {
                  @ApiResponse(responseCode = "200", description = "The search was successful"),
                  @ApiResponse(responseCode = "204", description = "There are no registered pets in database")
      })
      @GetMapping("/findGeneric")
      public ResponseEntity<GenericResponse<Page<PetDTO.Response>>> searchByCriteria(@Valid PetDTO.Response petDTO,
                  Pageable pageable) {

            Page<PetDTO.Response> result = petService.searchByCriteria(petDTO, pageable);

            if (result.isEmpty()) {
                  GenericResponse<Page<PetDTO.Response>> errorResponse = new GenericResponse<>(
                              HttpStatus.NO_CONTENT.value(),
                              "Não há pets registrados no banco de dados.",
                              result);
                  return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
            }

            GenericResponse<Page<PetDTO.Response>> successResponse = new GenericResponse<>(
                        HttpStatus.OK.value(),
                        "Operação realizada com sucesso.",
                        result);
            return ResponseEntity.ok(successResponse);
      }

      @Operation(summary = "Update a pet", description = "Updates a pet in the system with the information provided")
      @ApiResponses(value = {
                  @ApiResponse(responseCode = "200", description = "Pet updated successfully"),
                  @ApiResponse(responseCode = "404", description = "Couldn't find this pet to update")
      })
      @PutMapping("/update/")
      public ResponseEntity<GenericResponse<PetDTO.Response>> update(@RequestParam Long id,
                  @RequestBody PetDTO.Request petDTO)
                  throws Exception {

            PetDTO.Response responseDTO = petService.update(id, petDTO);

            GenericResponse<PetDTO.Response> successResponse = new GenericResponse<>(
                        HttpStatus.OK.value(),
                        "Operação realizada com sucesso.",
                        responseDTO);

            if (responseDTO == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(successResponse);
      }

      @Operation(summary = "Delete a pet", description = "Deletes a pet in the system with the ID provided")
      @ApiResponses(value = {
                  @ApiResponse(responseCode = "200", description = "Pet deleted successfully"),
                  @ApiResponse(responseCode = "404", description = "Couldn't find this pet to delete")
      })
      @DeleteMapping("/delete/")
      public ResponseEntity<String> deleteById(@RequestParam Long id) {
            if (!petService.existsById(id)) {
                  return ResponseEntity.badRequest().body("Animal não encontrado!");
            }
            petService.deleteById(id);
            return ResponseEntity.ok("Animal de estimação apagado com sucesso!");
      }
}
