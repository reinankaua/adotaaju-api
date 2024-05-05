package br.com.adotaaju.adotaajuapi.api.dto;

import lombok.Data;

@Data
public class PetResponse {
    private Long id;
    private PetType type;
    private String breed;
    private Integer age;
    private String color;
    private Float weight;
    private Boolean flAdopted;
}
