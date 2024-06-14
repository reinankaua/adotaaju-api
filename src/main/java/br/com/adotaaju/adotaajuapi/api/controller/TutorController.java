package br.com.adotaaju.adotaajuapi.api.controller;

import br.com.adotaaju.adotaajuapi.api.dto.TutorDTO;
import br.com.adotaaju.adotaajuapi.core.configuration.ApiResponsesCreated;
import br.com.adotaaju.adotaajuapi.core.configuration.ApiResponsesOK;
import br.com.adotaaju.adotaajuapi.core.exception.ResourceNotFoundException;
import br.com.adotaaju.adotaajuapi.domain.service.TutorService;
import br.com.adotaaju.adotaajuapi.core.ErrorResponse;
import br.com.adotaaju.adotaajuapi.core.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tutor", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "tutor", description = "APIs de Tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @Operation(summary = "Register a new tutor", description = "Creates a new tutor in the system with the information provided")
    @ApiResponsesCreated
    @PostMapping(value = "/register")
    public ResponseEntity<GenericResponse<TutorDTO.Response>> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Contrato de entrada: <br>
                    - **cpf**: Cadastro pessoa física. <br>Deve ser uma string com no mínimo 11 e no máximo 11 caracteres.<br>Precisa ser um CPF válido.<br>
                    - **name**: Nome. <br>Deve ser uma string com no mínimo 1 caractere e no máximo 300 caracteres.<br>
                    - **age**: Idade. <br>Deve ser um inteiro com no mínimo 1 algarismo e no máximo 3 algarismos.<br>
                    - **address**: Endereço. <br>Deve ser uma string com no mínimo 1 caractere e no máximo 300 caracteres.<br>
                    - **phone**: Telefone. <br>Deve ser uma string com no mínimo 8 caracteres e no máximo 11 caracteres.<br>
                    - **email**: E-mail. <br>Deve ser uma string e conter o caractere '@' para ser válido.<br>
                    - **flAlreadyAdopted**: Flag que indica se já adotou algum pet antes. <br>Deve ser um boolean 'True' ou 'False.
                    """) @Valid @ModelAttribute TutorDTO.Request tutorDTO) throws IOException {
        TutorDTO.Response responseDTO = tutorService.save(tutorDTO);
        GenericResponse<TutorDTO.Response> successResponse = new GenericResponse<>(
                HttpStatus.CREATED.value(),
                "Operação realizada com sucesso.",
                responseDTO
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "Search for tutor by ID", description = "Search for a tutor using the provided ID")
    @ApiResponsesOK
    @GetMapping("/findById/")
    public ResponseEntity<?> findById(
            @Parameter(description = "Identificador do tutor.<br>**Valor mínimo 1 algarismo**", example = "12")
            @RequestParam Long id) {
        try {
            TutorDTO.Response responseDTO = tutorService.findById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "An error occurred while searching for the tutor: "
                                + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Operation(summary = "Search for tutor by CPF", description = "Search for a tutor using the provided CPF")
    @ApiResponsesOK
    @GetMapping("/findByCpf/")
    public ResponseEntity<?> findByCpf(
            @Parameter(description = "CPF do tutor.<br>**Deve ser uma string com no mínimo 11 e no máximo 11 caracteres**" +
                    "<br>**Precisa ser um CPF válido**", example = "39534241490") @RequestParam String cpf) {
        try {
            TutorDTO.Response responseDTO = tutorService.findByCpf(cpf);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "An error occurred while searching for the tutor: "
                                + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @Operation(summary = "Update a tutor", description = "Updates a tutor in the system with the information provided")
    @ApiResponsesOK
    @PutMapping("/update")
    public ResponseEntity<GenericResponse<TutorDTO.Response>> update(
            @Parameter(description = "Identificador do tutor.<br>Valor deve ser obtido passando o cpf como parâmetro para a api: " +
                    "http://localhost:8080/v1/swagger-ui/index.html?urls.primaryName=pet#/tutor/findByCpf<br>" +
                    "O id obtido deve ser passado como parâmetro, os outros atributos para ser atualizados serão passados no body." +
                    "<br>**Valor mínimo 1 algarismo**", example = "12")
            @RequestParam Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Contrato de entrada: <br>
                    - **cpf**: Cadastro pessoa física. <br>Deve ser uma string com no mínimo 11 e no máximo 11 caracteres.<br>Precisa ser um CPF válido.<br>
                    - **name**: Nome. <br>Deve ser uma string com no mínimo 1 caractere e no máximo 300 caracteres.<br>
                    - **age**: Idade. <br>Deve ser um inteiro com no mínimo 1 algarismo e no máximo 3 algarismos.<br>
                    - **address**: Endereço. <br>Deve ser uma string com no mínimo 1 caractere e no máximo 300 caracteres.<br>
                    - **phone**: Telefone. <br>Deve ser uma string com no mínimo 8 caracteres e no máximo 11 caracteres.<br>
                    - **email**: E-mail. <br>Deve ser uma string e conter o caractere '@' para ser válido.<br>
                    - **flAlreadyAdopted**: Flag que indica se já adotou algum pet antes. <br>Deve ser um boolean 'True' ou 'False.
                    """)
            @ModelAttribute TutorDTO.Request tutorDTO) throws Exception {

        TutorDTO.Response responseDTO = tutorService.update(id, tutorDTO);
        GenericResponse<TutorDTO.Response> successResponse = new GenericResponse<>(
                HttpStatus.OK.value(),
                "Operação realizada com sucesso.",
                responseDTO
        );
        if (responseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(successResponse);
    }

    @Operation(summary = "Delete a tutor", description = "Deletes a tutor in the system with the ID provided")
    @ApiResponsesOK
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(
            @Parameter(description = "Identificador do tutor.<br>Valor deve ser obtido passando o cpf como parâmetro " +
                    "para a api: http://localhost:8080/v1/swagger-ui/index.html?urls.primaryName=pet#/tutor/findByCpf<br>" +
                    "**Valor mínimo 1 algarismo**", example = "12") @RequestParam Long id) {
        if (!tutorService.existsById(id)) {
            return ResponseEntity.badRequest().body("Tutor não encontrado!");
        }
        tutorService.deleteById(id);
        return ResponseEntity.ok("Tutor deletado com sucesso!");
    }

    @Operation(summary = "Adopt a pet", description = "Assigns a pet to a tutor as adopted")
    @ApiResponsesOK
    @PostMapping("/adopt/")
    public ResponseEntity<?> adoptPet(
            @Parameter(description = "Identificador do tutor.<br>Valor deve ser obtido passando o cpf como parâmetro " +
                    "para a api: http://localhost:8080/v1/swagger-ui/index.html?urls.primaryName=pet#/tutor/findByCpf<br>" +
                    "**Valor mínimo 1 algarismo**", example = "12") @RequestParam Long tutorId,
            @Parameter(description = "Identificador do pet.<br>Valor deve ser obtido passando o nome do pet como parâmetro" +
                    " para a api: http://localhost:8080/v1/swagger-ui/index.html?urls.primaryName=pet#/pet/searchByCriteria<br>" +
                    "**Valor mínimo 1 algarismo**", example = "12")@RequestParam Long petId) {

        if (tutorService.isPetAdopted(petId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Pet já foi adotado!"));
        }

        try {
            tutorService.adoptPet(tutorId, petId);
            return ResponseEntity.ok("Pet adotado com sucesso!");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "An error occurred while searching for the adoption: " + e.getMessage()));
        }
    }
}
