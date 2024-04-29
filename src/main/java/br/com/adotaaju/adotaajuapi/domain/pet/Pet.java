package br.com.adotaaju.adotaajuapi.domain.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tb_animal")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType type;

    @NotBlank(message = "A raça é obrigatória")
    private String breed;

    @NotNull
    private int age;

    @NotBlank(message = "A cor é obrigatória")
    private String color;
    @NotNull(message = "O peso é obrigatório")
    private Float weight;

    private Boolean adopted = false;
}
