package br.com.adotaaju.adotaajuapi.domain.entity;

import java.time.LocalDateTime;

import br.com.adotaaju.adotaajuapi.domain.pet.PetType;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_animal")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType type;

    @NotBlank(message = "O campo breed é obrigatória")
    private String breed;

    @NotNull(message = "O campo age é obrigatória")
    private Integer age;

    @NotBlank(message = "O campo color é obrigatória")
    private String color;

    @NotNull(message = "O campo weight é obrigatório")
    private Float weight;
    
    @NotNull(message = "O campo flAdopted é obrigatório")
    private Boolean flAdopted;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    
}
