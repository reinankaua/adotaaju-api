package br.com.adotaaju.adotaajuapi.api.dto;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;

import java.time.LocalDateTime;


public record PetDTO(

        Long id,
        PetType type,
        String breed,
        Integer age,
        String color,
        Float weight,
        Boolean isAdopted,

        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Pet toPet() {
        return new Pet(
                id,
                type,
                breed,
                age,
                color,
                weight,
                isAdopted,
                createdAt,
                updatedAt);
    }

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
                pet.getUpdatedAt());
    }
}
