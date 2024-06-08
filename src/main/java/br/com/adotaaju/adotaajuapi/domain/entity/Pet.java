package br.com.adotaaju.adotaajuapi.domain.entity;

import java.time.LocalDateTime;

import br.com.adotaaju.adotaajuapi.api.dto.PetType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
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

@Data
@Entity
@Table(name = "tb_pet")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

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
    
    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
   
    
    public Pet(PetType type, String breed, Integer age, String color, Float weight, Boolean flAdopted,
            String imageBase64) {
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.weight = weight;
        this.flAdopted = flAdopted;
        this.imageBase64 = imageBase64;
    }
}