package br.com.adotaaju.adotaajuapi.api.controller;

import br.com.adotaaju.adotaajuapi.api.dto.TutorDTO;
import br.com.adotaaju.adotaajuapi.domain.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @Operation(summary = "Register a new tutor", description = "Creates a new tutor in the system with the information provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A tutor has been successfully registered in the database"),
            @ApiResponse(responseCode = "400", description = "Invalid information provided")
    })
    @PostMapping(value = "/register")
    public ResponseEntity<TutorDTO.Response> create(@Valid @RequestBody TutorDTO.Request tutorDTO) {
        TutorDTO.Response responseDTO = tutorService.save(tutorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Search for tutor by ID", description = "Search for a tutor using the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The search was successful"),
            @ApiResponse(responseCode = "404", description = "Tutor not found")
    })
    @GetMapping("/findById/")
    public ResponseEntity<TutorDTO.Response> findById(@RequestParam Long id) {
        try {
            TutorDTO.Response responseDTO = tutorService.findById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Search for tutor by CPF", description = "Search for a tutor using the provided CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The search was successful"),
            @ApiResponse(responseCode = "404", description = "CPF not found")
    })
    @GetMapping("/findByCpf/{cpf}")
    public ResponseEntity<TutorDTO.Response> findByCpf(@PathVariable String cpf) {
        try {
            TutorDTO.Response responseDTO = tutorService.findByCpf(cpf);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Update a tutor", description = "Updates a tutor in the system with the information provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor updated successfully"),
            @ApiResponse(responseCode = "404", description = "Couldn't find this tutor to update")
    })
    @PutMapping("/update")
    public ResponseEntity<TutorDTO.Response> update(
            @RequestParam Long id,
            @RequestBody TutorDTO.Request tutorDTO) throws Exception {

        TutorDTO.Response responseDTO = tutorService.update(id, tutorDTO);
        if (responseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Delete a tutor", description = "Deletes a tutor in the system with the ID provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Couldn't find this tutor to delete")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (!tutorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tutorService.deleteById(id);
        return ResponseEntity.ok("Tutor deletado com sucesso!");
    }

    @Operation(summary = "Adopt a pet", description = "Assigns a pet to a tutor as adopted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet adopted successfully"),
            @ApiResponse(responseCode = "404", description = "Tutor or pet not found"),
            @ApiResponse(responseCode = "400", description = "Pet already adopted")
    })
    @PostMapping("/adopt")
    public ResponseEntity<String> adoptPet(
            @RequestParam Long tutorId,
            @RequestParam Long petId) {
        try {
            tutorService.adoptPet(tutorId, petId);
            return ResponseEntity.ok("Pet adotado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
