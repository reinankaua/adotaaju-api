package br.com.adotaaju.adotaajuapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class PetDTO {
        @Data
        public static class Request {
                private Long id;

                @NotNull(message = "The type field is mandatory")
                @Schema(description = "Type of pet", example = "GATO")
                private PetType type;

                @NotBlank(message = "The breed field is mandatory")
                @Schema(description = "Breed of pet", example = "Poodle")
                private String breed;

                @NotNull(message = "The age field is mandatory")
                @Schema(description = "Age of pet", example = "5")
                private Integer age;

                @NotBlank(message = "The color field is mandatory")
                @Schema(description = "Color of pet", example = "Black")
                private String color;

                @NotNull(message = "The weight field is mandatory")
                @Schema(description = "Weight of pet", example = "22.5")
                private Float weight;

                @NotNull(message = "The flAdopted field is mandatory")
                @Schema(description = "Adoption Flag", example = "true")
                private Boolean flAdopted;
        }

        @Data
        public static class Response {
                private Long id;

                @Schema(description = "Type of pet", example = "GATO")
                private PetType type;

                @Schema(description = "Breed of pet", example = "Poodle")
                private String breed;

                @Schema(description = "Age of pet", example = "5")
                private Integer age;

                @Schema(description = "Color of pet", example = "Black")
                private String color;

                @Schema(description = "Weight of pet", example = "22.5")
                private Float weight;

                @Schema(description = "Adoption Flag", example = "true")
                private Boolean flAdopted;
        }
}