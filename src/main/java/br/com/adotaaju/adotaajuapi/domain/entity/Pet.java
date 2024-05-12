package br.com.adotaaju.adotaajuapi.domain.entity;

import java.time.LocalDateTime;

import br.com.adotaaju.adotaajuapi.api.dto.PetDTO;
import br.com.adotaaju.adotaajuapi.api.dto.PetType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
@Table(name = "tb_animal")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends RepresentationModel<Pet> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String breed;
    private Integer age;
    private String color;
    private Float weight;
    private Boolean flAdopted;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public void initFromDTO(PetDTO petDTO) {
        this.id = petDTO.id();
        this.type = petDTO.type();
        this.breed = petDTO.breed();
        this.age = petDTO.age();
        this.color = petDTO.color();
        this.weight = petDTO.weight();
        this.flAdopted = petDTO.flAdopted();
        this.createdAt = petDTO.createdAt();
        this.updatedAt = petDTO.updatedAt();
    }
}