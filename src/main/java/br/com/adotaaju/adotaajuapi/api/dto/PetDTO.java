package br.com.adotaaju.adotaajuapi.api.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class PetDTO {

        @Data
        public static class Request {
                private Long id;

                @NotBlank(message = "The name field is mandatory")
                @Schema(description = "Name of pet", example = "Rex")
                @Size(min = 3, max = 99)
                private String name;

                @NotNull(message = "The type field is mandatory")
                @Schema(description = "Type of pet", example = "GATO")
                private PetType type;

                @NotBlank(message = "The breed field is mandatory")
                @Schema(description = "Breed of pet", example = "Poodle")
                @Size(min = 3, max = 99)
                private String breed;

                @NotNull(message = "The age field is mandatory")
                @Schema(description = "Age of pet", example = "5")
                @Min(value = 0)
                @Max(value = 99)
                private Integer age;

                @NotBlank(message = "The color field is mandatory")
                @Schema(description = "Color of pet", example = "Black")
                @Size(min = 3, max = 99)
                private String color;

                @NotNull(message = "The weight field is mandatory")
                @Schema(description = "Weight of pet", example = "22.5")
                @DecimalMin(value = "0.0")
                @DecimalMax(value = "99.0")
                private Float weight;

                @NotNull(message = "The flAdopted field is mandatory")
                @Schema(description = "Adoption Flag", example = "true")
                private Boolean flAdopted;

                private MultipartFile image;
        }

        @Data
        public static class Response {
                private Long id;

                @Schema(description = "Name of pet", example = "Rex")
                @Size(min = 3, max = 99)
                private String name;

                @Schema(description = "Type of pet", example = "GATO")
                private PetType type;

                @Schema(description = "Breed of pet", example = "Poodle")
                @Size(min = 3, max = 99)
                private String breed;

                @Schema(description = "Age of pet", example = "5")
                @Min(value = 0)
                @Max(value = 99)
                private Integer age;

                @Schema(description = "Color of pet", example = "Black")
                @Size(min = 3, max = 99)
                private String color;

                @Schema(description = "Weight of pet", example = "22.5")
                @DecimalMin(value = "0.0")
                @DecimalMax(value = "99.0")
                private Float weight;

                @Schema(description = "Adoption Flag", example = "true")
                private Boolean flAdopted;
        }
}