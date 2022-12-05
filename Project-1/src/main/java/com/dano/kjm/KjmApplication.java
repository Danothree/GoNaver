package com.dano.kjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class KjmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KjmApplication.class, args);
	}

}
