package br.com.adotaaju.adotaajuapi.domain.repository;

import br.com.adotaaju.adotaajuapi.domain.entity.Tutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Long> {
    Optional<Tutor> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
