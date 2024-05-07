package br.com.adotaaju.adotaajuapi.api.dto;

import java.time.LocalDateTime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PetDTO(
        Long id,

        @NotNull(message = "O campo type é obrigatório") 
        PetType type,

        @NotBlank(message = "O campo breed é obrigatório") 
        String breed,
        
        @NotNull(message = "O campo age é obrigatório") 
        Integer age,
        
        @NotBlank(message = "O campo color é obrigatório") 
        String color,
        
        @NotNull(message = "O campo weight é obrigatório") 
        Float weight,
        
        @NotNull(message = "O campo flAdopted é obrigatório") 
        Boolean flAdopted,

        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
