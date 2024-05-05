package br.com.adotaaju.adotaajuapi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_tutor")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo name é obrigatório")
    private String name;

    @NotNull(message = "O campo age é obrigatório")
    private Integer age;

    @NotBlank(message = "O campo adress é obrigatório")
    private String adress;

    @NotBlank(message = "O campo phone é obrigatório")
    private String phone;

    @NotBlank(message = "O campo email é obrigatório")
    @Email
    private String email;

    @NotNull(message = "O campo flAlreadyAdopted é obrigatório")
    private Boolean flAlreadyAdopted;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
