package br.com.adotaaju.adotaajuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdotaajuApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotaajuApiApplication.class, args);
	}

}