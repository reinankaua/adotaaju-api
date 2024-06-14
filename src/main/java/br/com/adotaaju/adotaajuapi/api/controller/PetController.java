package br.com.adotaaju.adotaajuapi.api.controller;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.core.GenericResponse;
import br.com.adotaaju.adotaajuapi.core.configuration.ApiResponsesCreated;
import br.com.adotaaju.adotaajuapi.core.configuration.ApiResponsesOK;
import br.com.adotaaju.adotaajuapi.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/pet", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(name = "pet", description = "APIs de Pet")
public class PetController {

      @Autowired
      private PetService petService;

      @Operation(summary = "Register a new pet", description = "Creates a new pet in the system with the information provided")
      @ApiResponsesCreated
      @PostMapping(value = "/register")
      public ResponseEntity<GenericResponse<PetDTO.Response>> create(
              @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Contrato de entrada: <br>
                    - **name**: Nome do Animal. Deve ser uma string com no mínimo 3 caracteres e no máximo 99 caracteres.<br>
                    - **type**: Tipo do Animal. <br>Deve ser passado o tipo CAT para gatos ou DOG para cachorros.<br>
                    - **breed**: Raça. <br>Deve ser uma string com no mínimo 3 caracteres e no máximo 99 caracteres.<br>
                    - **age**: Idade. <br>Deve ser um inteiro com no mínimo 1 algarismo e no máximo 2 algarismos.<br>
                    - **color**: Cor. <br>Deve ser uma string com no mínimo 3 caracteres e no máximo 999 caracteres.<br>
                    - **weight**: Peso. <br>Deve ser um número flutuante com no mínimo 1 algarismo e no máximo 2 algarismos.<br>
                    - **flAdopted**: Flag que indica se o animal está disponivel para adoção. <br>Deve ser um boolean 'True' ou 'False.<br>
                    - **image**: Imagem do animal. <br> Deve ser passado uma foto que representa o animal.
                    """) @Valid @ModelAttribute PetDTO.Request petDTO) throws IOException {

            PetDTO.Response responseDTO = petService.save(petDTO);

            GenericResponse<PetDTO.Response> successResponse = new GenericResponse<>(
                        HttpStatus.CREATED.value(),
                        "Operação realizada com sucesso.",
                        responseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
      }

      @Operation(summary = "Search for pets using filter", description = "Search for pets according to the filter passed")
      @ApiResponsesOK
      @GetMapping(value = "/findGeneric", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<GenericResponse<Page<PetDTO.Response>>> searchByCriteria(@Valid @Parameter(description = "A busca generica pode ser realizada passando nenhum, um ou mais parametros") PetDTO.Response petDTO, Pageable pageable) {
            
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
      @ApiResponsesOK
      @PutMapping("/update")
      public ResponseEntity<GenericResponse<PetDTO.Response>> update(
                  @Parameter(description = "Identificador do animal.<br>" +
                    "Valor deve ser obtido passando o nome como parâmetro para a api: " +
                    "http://localhost:8080/v1/pet/findGeneric?name=Rex&page=0&size=1&sort=string" +
                    "O id deve ser passado como parâmetro, os outros atributos para ser atualizados serão passados no body." +
                    "<br>**Valor mínimo 1 algarismo**", example = "12")
                  @RequestParam Long id,
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Contrato de entrada: <br>
                    - **name**: Nome do Animal. Deve ser uma string com no mínimo 3 caracteres e no máximo 99 caracteres.<br>
                    - **type**: Tipo do Animal. <br>Deve ser passado o tipo CAT para gatos ou DOG para cachorros.<br>
                    - **breed**: Raça. <br>Deve ser uma string com no mínimo 3 caracteres e no máximo 99 caracteres.<br>
                    - **age**: Idade. <br>Deve ser um inteiro com no mínimo 1 algarismo e no máximo 2 algarismos.<br>
                    - **color**: Cor. <br>Deve ser uma string com no mínimo 3 caracteres e no máximo 999 caracteres.<br>
                    - **weight**: Peso. <br>Deve ser um número flutuante com no mínimo 1 algarismo e no máximo 2 algarismos.<br>
                    - **flAdopted**: Flag que indica se o animal está disponivel para adoção. <br>Deve ser um boolean 'True' ou 'False.<br>
                    - **image**: Imagem do animal. <br> Deve ser passado uma foto que representa o animal.
                    """)
                  @ModelAttribute PetDTO.Request petDTO)
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
      @ApiResponsesOK
      @DeleteMapping("/delete")
      public ResponseEntity<String> deleteById( @Parameter(description = "Identificador do animal.<br>" + 
      "Valor deve ser obtido passando o nome como parâmetro para a api: " + 
      "http://localhost:8080/v1/pet/findGeneric?name=Rex&page=0&size=1&sort=string<br>" +
      "**Valor mínimo 1 algarismo**", example = "12") @RequestParam Long id) {
      
            if (!petService.existsById(id)) {
                  return ResponseEntity.badRequest().body("Animal não encontrado!");
            }
            petService.deleteById(id);
            return ResponseEntity.ok("Animal de estimação apagado com sucesso!");
      }
}
