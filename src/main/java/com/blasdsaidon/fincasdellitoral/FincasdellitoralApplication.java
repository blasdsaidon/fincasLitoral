package com.blasdsaidon.fincasdellitoral;

import com.blasdsaidon.fincasdellitoral.entidades.StartupListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FincasdellitoralApplication {

	public static void main(String[] args) {
		// Muestra el cartel de carga antes de iniciar
		StartupListener.showLoadingScreen();

		// Inicia Spring Boot
		SpringApplication.run(FincasdellitoralApplication.class, args);
	}

}

