package br.com.adotaaju.adotaajuapi.domain.pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResponse {
    private Long id;
    private PetType type;
    private String breed;
    private Integer age;
    private String color;
    private Float weight;
    private Boolean flAdopted;
}
