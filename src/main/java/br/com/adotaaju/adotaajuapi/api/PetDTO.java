package br.com.adotaaju.adotaajuapi.api;

import br.com.adotaaju.adotaajuapi.domain.pet.PetType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

public enum PetDTO {
    ;

    private interface Id{
        @NotNull
        @Schema(description = "Identificador", example = "2")
        Long getId();
    }
    private interface Type {
        @Schema(description = "Tipo do pet, Valores permitidos: GATO ou CACHORRO", example = "GATO")
        void getType();
    }

    private interface Breed {
        @NotBlank
        @Size(max = 255)
        @Schema(description = "Raça", example = "Doberman")
        String getBreed();
    }

    private interface Age {
        @NotNull
        @Schema(description = "Idade", example = "5")
        Integer getAge();
    }

    private interface Color {
        @NotBlank
        @Schema(description = "Cor", example = "Preto")
        String getColor();
    }

    private interface Weight {
        @NotNull
        @Schema(description = "Peso", example = "12.5")
        Float getWeight();
    }

    private interface FlAdopted {
        @NotNull
        @Schema(description = "Flag Adotado")
        Boolean getFlAdopted();
    }

    private interface CreatedAt {
        @NotNull
        @Schema(description = "Data de criação do cadastro", example = "22/01/2024 15:00:00")
        LocalDateTime getCreatedAt();
    }

    public enum Request{
        ;

        @Data
        public static class Base implements Type, Breed, Age, Color, Weight, CreatedAt {
            private PetType type;
            private String breed;
            private Integer age;
            private String color;
            private Float weight;
            private Boolean adopted;
            private LocalDateTime createdAt;
        }
    }

    public enum Response {
        ;

    public interface PetForAdoption extends Id, Type, Breed, Age, Color, Weight, FlAdopted {

    }
    }

}
