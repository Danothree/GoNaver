package com.dano.kjm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class KjmApplication {

	public static void main(String[] args) {

		SpringApplication.run(KjmApplication.class, args);
		String property = System.getProperty("user.home");
		System.out.println("rwww"+property);
	}

}
