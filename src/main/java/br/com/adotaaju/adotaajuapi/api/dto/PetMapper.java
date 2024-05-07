package br.com.adotaaju.adotaajuapi.api.dto;

import br.com.adotaaju.adotaajuapi.domain.entity.Pet;

public class PetMapper {

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
}
