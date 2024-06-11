package br.com.adotaaju.adotaajuapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.multipart.MultipartFile;

public class TutorDTO {
    @Data
    public static class Request {
        private Long id;

        @NotBlank(message = "The field cpf is mandatory")
        @Schema(description = "Individual identifier", example = "64938451301")
        @CPF
        private String cpf;

        @NotBlank(message = "The field name is mandatory")
        @Schema(description = "Guardian's name", example = "JOAO")
        @Size(min= 1, max = 300)
        private String name;

        @NotNull(message = "The field age is mandatory")
        @Schema(description = "Guardian's age", example = "28")
        @Min(18) @Max(999)
        private Integer age;

        @NotBlank(message = "The field address is mandatory")
        @Schema(description = "Guardian's address", example = "RUA 10, BAIRRO: SOL")
        @Size(min= 1, max = 300)
        private String address;

        @NotBlank(message = "The field phone is mandatory")
        @Schema(description = "Guardian's phone", example = "79988001122")
        @Size(min = 8, max = 11)
        private String phone;

        @NotBlank(message = "The field email is mandatory")
        @Schema(description = "Guardian's e-mail", example = "exemple@exemple.com")
        @Email
        private String email;

        @NotNull(message = "The field flAlreadyAdopted is mandatory")
        @Schema(description = "Flag if already adopted", example = "true")
        private Boolean flAlreadyAdopted;

        private MultipartFile image;

    }

    @Data
    public static class Response {
        private Long id;

        @Schema(description = "Individual identifier", example = "64938451301")
        private String cpf;

        @Schema(description = "Guardian's name", example = "JOAO")
        private String name;

        @Schema(description = "Guardian's age", example = "28")
        private Integer age;

        @Schema(description = "Guardian's address", example = "RUA 10, BAIRRO: SOL")
        private String address;

        @Schema(description = "Guardian's phone", example = "79988001122")
        private String phone;

        @Schema(description = "Guardian's e-mail", example = "exemple@exemple.com")
        private String email;

        @Schema(description = "Flag if already adopted", example = "true")
        private Boolean flAlreadyAdopted;
    }
}
