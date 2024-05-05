package br.com.adotaaju.adotaajuapi.domain.pet;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequest {
    private PetType type;
    private String breed;
    private Integer age;
    private String color;
    private Float weight;
    private Boolean flAdopted;
    private LocalDateTime createdAt;
}
