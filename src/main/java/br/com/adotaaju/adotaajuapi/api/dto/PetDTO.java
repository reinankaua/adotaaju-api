package br.com.adotaaju.adotaajuapi.api.dto;

import java.time.LocalDateTime;


import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


public record PetDTO(
        Long id,

        @NotNull(message = "The type field is mandatory")
        @Schema(description = "Type of pet", example = "GATO")
        PetType type,

        @NotBlank(message = "The breed field is mandatory")
        @Schema(description = "Breed of pet", example = "Poodle")
        String breed,
        
        @NotNull(message = "The age field is mandatory")
        @Schema(description = "Age of pet", example = "5")
        Integer age,
        
        @NotBlank(message = "The color field is mandatory")
        @Schema(description = "Color of pet", example = "Black")
        String color,
        
        @NotNull(message = "The weight field is mandatory")
        @Schema(description = "Weight of pet", example = "22.5")
        Float weight,
        
        @NotNull(message = "The flAdopted field is mandatory")
        @Schema(description = "Adoption Flag", example = "Sim")
        Boolean flAdopted,

        @Schema(description = "Creation date", example = "11/05/2024 19:00:00")
        LocalDateTime createdAt,

        @Schema(description = "Update date", example = "11/05/2024 19:00:00")
        LocalDateTime updatedAt) {

        public static PetDTO toPetDTO(Pet pet) {
                return new PetDTO(
                        pet.getId(),
                        pet.getType(),
                        pet.getBreed(),
                        pet.getAge(),
                        pet.getColor(),
                        pet.getWeight(),
                        pet.getFlAdopted(),
                        pet.getCreatedAt(),
                        pet.getUpdatedAt()
                );
        }

        public static Pet toPet(PetDTO petDTO) {
                return new Pet(
                        petDTO.id(),
                        petDTO.type(),
                        petDTO.breed(),
                        petDTO.age(),
                        petDTO.color(),
                        petDTO.weight(),
                        petDTO.flAdopted(),
                        petDTO.createdAt(),
                        petDTO.updatedAt()
                );
        }
}
